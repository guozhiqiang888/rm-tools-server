package com.farben.readme.util;

import com.alibaba.druid.util.StringUtils;
import com.farben.readme.bean.logon.AuthenError;
import com.farben.readme.bean.logon.LogonRequest;
import com.farben.readme.bean.logon.LogonResponse;
import com.farben.readme.config.LdapConfig;
import com.farben.readme.constant.Constant;
import com.farben.readme.util.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class LdapValidationUtil {

    private final static Logger logger = LoggerFactory.getLogger(LdapValidationUtil.class);

    private static LdapConfig ldapConfig;

    /**
     * real validate staff function
     * Logon will call this function
     *
     * @param logonRequest
     * @return
     */
    public static ResponseEntity<LogonResponse> staffValidation(final LogonRequest logonRequest, final LdapConfig ldapConfig) {
        logger.debug("=================logon validate===============>begin: " + logonRequest.toString());
        LdapValidationUtil.ldapConfig = ldapConfig;
        String staffId = logonRequest.getStaffId();
        String pwd = logonRequest.getPwd();

        logger.debug("Staff id: {}, password: {}", staffId, pwd);

        // response entity
        ResponseEntity<LogonResponse> repEntity = null;

        // if staffInfo is null or empty return 400, Bad request!
        if (StringUtils.isEmpty(staffId) || StringUtils.isEmpty(pwd)) {
            logger.warn("staff info not correct.");
            repEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return repEntity;
        }

        try {
            // Authentication start.
            LdapContext ldapCxt = authenProcess(staffId, pwd);
            // Search staff info
            repEntity = searchStaffInfo(ldapCxt, staffId);
        } catch (AuthenticationException e) {
            // handle error code
            AuthenError authError = handleError(e);
            // Authentication is faild, return 401.
            LogonResponse logonResponse = new LogonResponse();
            logonResponse.setAuthenError(authError);
            repEntity = new ResponseEntity<>(logonResponse, HttpStatus.UNAUTHORIZED);
        } catch (IOException e) {
            logger.error("IOException: {}", e);
            repEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException e) {
            logger.error("NoSuch Cert KeyStore: {}", e);
            AuthenError authError = new AuthenError(HttpStatus.UNAUTHORIZED.toString(), "Authen Error");
            LogonResponse logonResponse = new LogonResponse();
            logonResponse.setAuthenError(authError);
            repEntity = new ResponseEntity<>(logonResponse, HttpStatus.UNAUTHORIZED);
        }
        logger.debug("=================logon validate===============>end");
        return repEntity;
    }

    /**
     * <p>
     * <b> Verify the staffid and pwd</b>
     * </p>
     *
     * @param staffId
     * @param pwd
     * @throws IOException
     * @throws KeyStoreException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     */
    private static LdapContext authenProcess(final String staffId, final String pwd)
            throws AuthenticationException, IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        // create a Hastable and set the properties

        try {
            // get the ldap attr
            ConcurrentHashMap<String, String> attr = ldapConfig.getLdapAttr();

            java.security.Security.setProperty(attr.get(Constant.SSL_SOCKETFAC_PROVIDER), attr.get(Constant.IBM_SSLIMP));
            java.security.Security.setProperty(attr.get(Constant.SSL_SERVERSOCKETFAC_PROVIDER),
                    attr.get(Constant.IBM_SSLSERVERIMP));

            ClassPathResource clpathRecource = new ClassPathResource(attr.get(Constant.CA_PATH));

            logger.debug("JKS Name: {}", clpathRecource.getFilename());

            if (clpathRecource.exists()) {
                String canonicalPath = clpathRecource.getFile().getCanonicalPath();

                System.setProperty(attr.get(Constant.TRUST_STORE), canonicalPath);

                InputStream input = clpathRecource.getInputStream();

                try {
                    KeyStore.getInstance(KeyStore.getDefaultType()).load(input, JasyptUtil.decrypt(attr.get(Constant.CA_PWD)).toCharArray());
                } catch (Exception e) {
                    logger.error("The decryption of CA_PWD is fail:{} ", e);
                    throw new AuthenticationException(e.getMessage());
                }
                Hashtable<String, String> ldapCtxHashTable = ldapConfig.getLdapCtxHashTable();

                ldapCtxHashTable.put(javax.naming.Context.SECURITY_PRINCIPAL,
                        new StringBuilder(staffId).append(ldapConfig.getStaffSuffix()).toString());
                ldapCtxHashTable.put(javax.naming.Context.SECURITY_CREDENTIALS, pwd);

                logger.debug("************LdapValidation.HashTable Start**********");

                Set<Map.Entry<String, String>> keySet = ldapCtxHashTable.entrySet();
                for (Map.Entry<String, String> entry : keySet) {
                    logger.debug("LdapAuthen HashTable Key={}, value={}", entry.getKey(), entry.getValue());
                }
                logger.debug("************LdapValidation.HashTable End*************");
                logger.debug("com.ibm.mq.cfg.useIBMCipherMappings===================>" + System.getProperty("com.ibm.mq.cfg.useIBMCipherMappings"));
                return new InitialLdapContext(ldapCtxHashTable, null);
            } else {
                throw new IOException("KeyStore file not found!!!");
            }

        } catch (NamingException e) {
            logger.warn("Authentication is fail:{}", e);
            throw new AuthenticationException(e.getMessage());
        }
    }

    /**
     * <p>
     * <b> Searche staff information from ldap </b>
     * </p>
     *
     * @param ldapCxt
     */
    private static ResponseEntity<LogonResponse> searchStaffInfo(final LdapContext ldapCxt, final String staffId) throws AuthenticationException {

        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        List<String> ldapReturnedAtts = ldapConfig.getLdapReturnedAtts();

        String[] returnedAtts = ldapReturnedAtts.toArray(new String[ldapReturnedAtts.size()]);
        searchCtls.setReturningAttributes(returnedAtts);

        // Replace the {} with staffid
        String localLdapAuthFilter = ldapConfig.getLdapAuthFilter();
        localLdapAuthFilter = localLdapAuthFilter.replaceAll("\\{}", staffId);

        try {
            NamingEnumeration<SearchResult> result = ldapCxt.search(ldapConfig.getLdapSearchDC(), localLdapAuthFilter, searchCtls);
            return wrapStaffInfo(result, staffId);
        } catch (NamingException e) {
            logger.warn("Search staff infor error:{} ", e);
            throw new AuthenticationException(e.getMessage());
        }
    }

    /**
     * <p>
     * <b> Wrap staff to LdapStaffInfo </b>
     * </p>
     *
     * @param result
     * @return
     * @throws NamingException
     */
    private static ResponseEntity<LogonResponse> wrapStaffInfo(final NamingEnumeration<SearchResult> result, final String staffId) throws NamingException {
        if (result.hasMoreElements()) {
            SearchResult sr = result.next();
            Attributes attrs = sr.getAttributes();
            NamingEnumeration<String> ids = attrs.getIDs();
            LogonResponse ldapStaff = new LogonResponse();
            while (ids.hasMoreElements()) {
                String id = ids.next();
                Attribute attribute = attrs.get(id);
                String content = attribute.get(0).toString();
                switch (id) {
                    case Constant.LDAP_MEMBER_OF:
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < attribute.size(); i++) {
                            sb.append(attribute.get(i).toString()).append(",");
                        }
                        ldapStaff.setMemberOf(sb.toString());
                        break;
                    case Constant.LDAP_MAIL:
                        ldapStaff.setEmail(content);
                        break;
                    case Constant.LDAP_GIVEN_NAME:
                        ldapStaff.setGivenName(content);
                        break;
                    case Constant.LDAP_LAST_NAME:
                        ldapStaff.setLastName(content);
                        break;
                }
            }
            logger.debug("Search result: {}", ldapStaff);
            return new ResponseEntity<>(ldapStaff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * <p>
     * <b> </b>
     * </p>
     *
     * @param e
     */
    private static AuthenError handleError(final NamingException e) {
        String errorMsg = e.getMessage();
        String errorCode = ResponseCode.LDAP_COMMON_ERROR.getCode();
        int startIndex = errorMsg.indexOf("data ");
        int endIndex = errorMsg.indexOf(",", startIndex);
        if (startIndex != -1 || endIndex != -1) {
            errorCode = errorMsg.substring(startIndex + "data ".length(), endIndex);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(ResponseCode.LDAP_COMMON_ERROR.getMsg()).append("--").append(e.getClass().getName()).append(": ").append(errorMsg);
        logger.info("error code: {}", errorCode);
        logger.info("error msg: {}", sb);
        return new AuthenError(errorCode, sb.toString());
    }

}
