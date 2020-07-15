//package com.farben.readme.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.farben.readme.bean.logon.LogonResponse;
//import com.farben.readme.config.EroadConstantsConfig;
//import com.farben.readme.config.LdapConfig;
//import com.farben.readme.constant.Constant;
//import com.farben.readme.mapper.WechatAccountAuthMapper;
//import com.farben.readme.service.IAuthService;
//import com.farben.readme.service.ICacheService;
//import org.apache.ibatis.session.SqlSession;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class AuthServiceImpl implements IAuthService {
//
//    private final static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
//
//    @Autowired
//    private LdapConfig ldapConfig;
//
//    @Autowired
//    private EroadConstantsConfig eroadConstantsConfig;
//
//    @Autowired
//    private SqlSession sqlSession;
//
//    @Autowired
//    private ICacheService cacheService;
//
//
//    public String authCheck(String staffId) {
//        //1.query group
//        LogonResponse ldapStaff = (LogonResponse) cacheService.get(Constant.USER_AUTH_CACHE_NAME, staffId);
//        logger.info("==>cache key is:{}<===", staffId);
//        if (null == ldapStaff) {
//            return null;
//        }
//        if (eroadConstantsConfig.getTencentStaffId().equals(staffId)
//                && eroadConstantsConfig.getTencentStaffMemberOf().equals(ldapStaff.getMemberOf())) {
//            logger.info("==>tencentStaffTabAuth is: {}<===", eroadConstantsConfig.getTencentStaffTabAuth());
//            return eroadConstantsConfig.getTencentStaffTabAuth();
//        }
//        String groups = ldapStaff.getMemberOf();
//        List<String> ldapGroups = ldapConfig.getLdapGroups();
//
//        List<String> userGroups = new ArrayList<>();
//        for (String userGroup : ldapGroups) {
//            if (groups.contains(userGroup)) {
//                userGroups.add(userGroup);
//            }
//        }
//        //2.query tabName
//        String userAuth = sqlSession.getMapper(WechatAccountAuthMapper.class).getUserAuth();
//        logger.info("==>user auth tabName:{}<===", userAuth);
//        //3.deal with tabName
//        String tabName = dealWithTabName(userAuth, userGroups);
//        if (null == tabName) {
//            return null;
//        }
//        return tabName;
//    }
//
//
//    /**
//     * deal with tabName
//     *
//     * @return
//     */
//    private String dealWithTabName(String userAuth, List<String> ldapGruops) {
//
//        Set<String> setList = new HashSet<>();
//
//        if (null == userAuth || "".equals(userAuth)) {
//            return null;
//        }
//
//        JSONArray objects = JSON.parseArray(userAuth);
//
//        if (objects.size() > 0) {
//            logger.info("==>user auth is:{}<==", objects);
//
//            Map<String, String> map = new HashMap<>();
//
//            for (Object object : objects) {
//                Map tabMap = JSON.parseObject(JSON.toJSONString(object));
//                for (Object o : tabMap.keySet()) {
//                    map.put(o.toString(), tabMap.get(o).toString());
//                }
//            }
//
//            for (String ldapGruop : ldapGruops) {
//                for (Map.Entry<String, String> groupTabName : map.entrySet()) {
//                    if (ldapGruop.equals(groupTabName.getKey())) {
//                        String tabName = groupTabName.getValue();
//                        if (tabName.contains(",")) {
//                            String[] split = tabName.split(",");
//                            List<String> responseTabNames = Arrays.asList(split);
//                            setList.addAll(responseTabNames);
//                        } else {
//                            setList.add(tabName);
//                        }
//                    }
//                }
//            }
//            StringBuilder stringBuilder = new StringBuilder();
//            int size = setList.size();
//            if (size > 0) {
//                for (String str : setList) {
//                    stringBuilder.append(str).append(",");
//                }
//
//                String responseTabName = stringBuilder.toString();
//
//                String substring = responseTabName.substring(0, responseTabName.length() - 1);
//                logger.info("==============>backEnd response tanName :{}<========", substring);
//                return substring;
//            }
//        }
//        return null;
//    }
//}
