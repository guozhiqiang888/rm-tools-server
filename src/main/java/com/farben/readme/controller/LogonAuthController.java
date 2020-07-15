package com.farben.readme.controller;

import com.alibaba.druid.util.StringUtils;
import com.farben.readme.bean.logon.AuthenError;
import com.farben.readme.bean.logon.LogonRequest;
import com.farben.readme.bean.logon.LogonResponse;
import com.farben.readme.bean.request.AuthRequest;
import com.farben.readme.config.EroadConstantsConfig;
import com.farben.readme.config.LdapConfig;
import com.farben.readme.constant.Constant;
//import com.farben.readme.service.IAuthService;
import com.farben.readme.service.ICacheService;
import com.farben.readme.service.ILoginService;
import com.farben.readme.service.ITokenService;
import com.farben.readme.util.JasyptUtil;
import com.farben.readme.util.LdapValidationUtil;
import com.farben.readme.util.response.Response;
import com.farben.readme.util.response.ResponseCode;
import com.farben.readme.util.response.ResponseUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auth")
@Api(tags = "LogonAuthController", description = "LogonAuthController | Logon Authentication Module")
public class LogonAuthController {

    private final static Logger logger = LoggerFactory.getLogger(LogonAuthController.class);

    @Autowired
    private LdapConfig ldapConfig;

    @Autowired
    private EroadConstantsConfig eroadConstantsConfig;

//    @Autowired
//    private IAuthService authService;

    @Autowired
    private ILoginService loginService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ICacheService cacheService;

    /**
     * @param staffId
     * @return tencent staff
     */
    private ResponseEntity<LogonResponse> tencentStaff(String staffId, String pwd) {
        LogonResponse ldapStaff = new LogonResponse();
        if (eroadConstantsConfig.getTencentStaffId().equals(staffId)
                && JasyptUtil.decrypt(eroadConstantsConfig.getTencentStaffPwd()).equals(pwd)) {
            ldapStaff.setGivenName("Staff");
            ldapStaff.setLastName("Tencent");
            ldapStaff.setMemberOf(eroadConstantsConfig.getTencentStaffMemberOf());
            String token = loginService.generateLogonToken(staffId, "Staff Tencent");
            String reacquireToken = loginService.generateReacquireToken(staffId, "Staff Tencent", JasyptUtil.encrypt(pwd), token);
            ldapStaff.setToken(token);
            ldapStaff.setReacquireToken(reacquireToken);
            //put ldapStaff to cache
            cacheService.put(Constant.USER_AUTH_CACHE_NAME, staffId, ldapStaff);
            return new ResponseEntity<>(ldapStaff, HttpStatus.OK);
        } else {
            ldapStaff.setAuthenError(new AuthenError(ResponseCode.TENCENT_LOGON_ERROR.getCode(), ResponseCode.TENCENT_LOGON_ERROR.getMsg()));
            return new ResponseEntity<>(ldapStaff, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * verify that is it in ldap groups
     *
     * @param ldapStaff
     * @return boolean
     */
    private boolean isMemberOf(LogonResponse ldapStaff) {
        List<String> ldapGroups = ldapConfig.getLdapGroups();
        if (!CollectionUtils.isEmpty(ldapGroups)) {
            for (String group : ldapGroups) {
                if (ldapStaff != null && !StringUtils.isEmpty(ldapStaff.getMemberOf()) && ldapStaff.getMemberOf().indexOf(group) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    @ApiOperation(value = "Logon API", notes = "Logon API")
    @RequestMapping(value = {"/logon"}, method = RequestMethod.POST)
    public ResponseEntity<LogonResponse> staffLogon(@RequestBody final LogonRequest logonRequest) {
        logger.info("Logon start....");
        String staffId = logonRequest.getStaffId();
        String pwd = logonRequest.getPwd();
        logger.info("==>StaffId: {}", staffId);

        ResponseEntity<LogonResponse> repEntity = null;
        try {
            // this is tencent staff id
            if (eroadConstantsConfig.getTencentStaffId().equals(staffId)) {
                return tencentStaff(staffId, pwd);
            }

            repEntity = LdapValidationUtil.staffValidation(logonRequest, ldapConfig);
            // success
            if (repEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
                LogonResponse ldapStaff = repEntity.getBody();
                boolean isMemberOf = isMemberOf(ldapStaff);
                // not in ldap groups
                if (!isMemberOf) {
                    ldapStaff = new LogonResponse();
                    AuthenError authenError = new AuthenError(ResponseCode.IS_MEMBER_OF_ERROR.getCode(), ResponseCode.IS_MEMBER_OF_ERROR.getMsg());
                    ldapStaff.setAuthenError(authenError);
                    return new ResponseEntity<>(ldapStaff, HttpStatus.OK);
                }
                StringBuffer staffName = new StringBuffer(ldapStaff.getGivenName()).append(" ").append(ldapStaff.getLastName());
                String token = loginService.generateLogonToken(staffId, staffName.toString());
                String reacquireToken = loginService.generateReacquireToken(staffId, staffName.toString(), JasyptUtil.encrypt(pwd), token);
                ldapStaff.setToken(token);
                ldapStaff.setReacquireToken(reacquireToken);
                //put ldapStaff to cache
                cacheService.put(Constant.USER_AUTH_CACHE_NAME, staffId, ldapStaff);
                return new ResponseEntity<>(ldapStaff, HttpStatus.OK);

            } else if (repEntity.getStatusCodeValue() == HttpStatus.UNAUTHORIZED.value()) {
                LogonResponse ldapStaff = repEntity.getBody();
                AuthenError authenError = ldapStaff.getAuthenError();
                String errorCode = authenError.getErrorCode();
                boolean isLdapErrorFlag = false;
                if (errorCode.equals("52e")) {
                    isLdapErrorFlag = true;
                    authenError = new AuthenError(ResponseCode.LDAP_STAFF_OR_PWD_ERROR.getCode(), ResponseCode.LDAP_STAFF_OR_PWD_ERROR.getMsg());
                } else if (errorCode.equals("775")) {
                    isLdapErrorFlag = true;
                    authenError = new AuthenError(ResponseCode.LDAP_ACCOUNT_LOCKED_ERROR.getCode(), ResponseCode.LDAP_ACCOUNT_LOCKED_ERROR.getMsg());
                } else if (errorCode.equals("533")) {
                    isLdapErrorFlag = true;
                    authenError = new AuthenError(ResponseCode.LDAP_ACCOUNT_DISABLED_ERROR.getCode(), ResponseCode.LDAP_ACCOUNT_DISABLED_ERROR.getMsg());
                } else if (errorCode.equals("701")) {
                    isLdapErrorFlag = true;
                    authenError = new AuthenError(ResponseCode.LDAP_ACCOUNT_EXPIRED_ERROR.getCode(), ResponseCode.LDAP_ACCOUNT_EXPIRED_ERROR.getMsg());
                } else if (errorCode.equals("532")) {
                    isLdapErrorFlag = true;
                    authenError = new AuthenError(ResponseCode.LDAP_PWD_EXPIRED_ERROR.getCode(), ResponseCode.LDAP_PWD_EXPIRED_ERROR.getMsg());
                } else if (errorCode.equals("2030")) {
                    isLdapErrorFlag = true;
                    authenError = new AuthenError(ResponseCode.LDAP_USERNAME_NOT_FOUND_ERROR.getCode(), ResponseCode.LDAP_USERNAME_NOT_FOUND_ERROR.getMsg());
                }
                if (isLdapErrorFlag) {
                    ldapStaff = new LogonResponse();
                    ldapStaff.setAuthenError(authenError);
                    return new ResponseEntity<>(ldapStaff, HttpStatus.OK);
                }
            }

            // set staff into session
            //RequestContextUtil.getRequest().getSession().setAttribute(Constant.SESSIONKEY, staffInfo);
        } catch (Exception e) {
            logger.info("==>staffLogon exception: {}", e);
            repEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return repEntity;
    }


//    @PostMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Response authCheck(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
//                              @RequestHeader(value = Constant.HEADER_TOKNE) final String token,
//                              @RequestBody AuthRequest authRequest) {
//        try {
//            String staffId = authRequest.getStaffId();
//            logger.info("======request id is {},staffId :{}=====", requestId, staffId);
//            String obj = authService.authCheck(staffId);
//            if (null == obj) {
//                throw new Exception("the user auth is null");
//            }
//            Response success = ResponseUtil.success(obj);
//            logger.info("==>backEnd response : {}<===", success);
//            return success;
//        } catch (Exception e) {
//            logger.warn("==>auth api has some exception:", e);
//            return ResponseUtil.error(ResponseCode.INTERNAL_ERROR);
//        }
//    }


    @ApiOperation(value = "Reacquire token API", notes = "Reacquire token API")
    @RequestMapping(value = {"/reacquire"}, method = RequestMethod.POST)
    public ResponseEntity<?> reacquireToken(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
                                            @RequestHeader(value = Constant.HEADER_TOKNE) final String token,
                                            @RequestHeader(value = Constant.HEADER_REACQUIRE_TOKEN) final String reacquireToken) {
        try {
            logger.info("reacquire token start....request id:{}", requestId);
            logger.info("reacquireToken param[{}], token param[{}]", reacquireToken, token);
            Claims claims = tokenService.parseNotExpiredToken(reacquireToken, token);
            Object staffId = claims.get(Constant.STAFF_ID_KEY);
            Object staffName = claims.get(Constant.STAFF_NAME_KEY);
            Object pwd = claims.get(Constant.STAFF_PWD_KEY);
            logger.info("reacquire token staffId={}, staffName={}, staffPwd={}", staffId, staffName, pwd);
            if (staffId == null || staffName == null || pwd == null) {
                logger.warn("reacquire token end....failed, staffId is null or staffName is null or pwd is null");
                return new ResponseEntity<>(ResponseUtil.error(ResponseCode.REACQUIRE_TOKEN_PARSE_ERROR), HttpStatus.OK);
            }
            LogonResponse ldapStaffCache = (LogonResponse) cacheService.get(Constant.USER_AUTH_CACHE_NAME, staffId.toString());
            if (ldapStaffCache == null) {
                logger.warn("reacquire token end....failed, ldapStaffCache is null");
                return new ResponseEntity<>(ResponseUtil.error(ResponseCode.STAFF_CACHE_ERROR), HttpStatus.OK);
            }

            String newToken = loginService.generateLogonToken(staffId.toString(), staffName.toString());
            String newReacquireToken = loginService.generateReacquireToken(staffId.toString(), staffName.toString(), pwd.toString(), newToken);
            ldapStaffCache.setToken(newToken);
            ldapStaffCache.setReacquireToken(newReacquireToken);
            //put ldapStaff to cache
            cacheService.put(Constant.USER_AUTH_CACHE_NAME, staffId.toString(), ldapStaffCache);
            return new ResponseEntity<>(ldapStaffCache, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("==>reacquireToken api has some exception:", e);
            return new ResponseEntity<>(ResponseUtil.error(ResponseCode.INTERNAL_ERROR), HttpStatus.OK);
        }
    }

}
