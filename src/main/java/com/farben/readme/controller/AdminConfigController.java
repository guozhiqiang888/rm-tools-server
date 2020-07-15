//package com.farben.readme.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.farben.readme.annotation.Auth;
//import com.farben.readme.annotation.EditAuth;
//import com.farben.readme.bean.EroadAdminConfig;
//import com.farben.readme.bean.config.EroadConfig;
//import com.farben.readme.bean.config.EroadConfigReq;
//import com.farben.readme.bean.config.LastEroadConfigResp;
//import com.farben.readme.constant.Constant;
//import com.farben.readme.service.IEroadAdminConfigService;
//import com.farben.readme.service.ILoginService;
//import com.farben.readme.util.CommonUtils;
//import com.farben.readme.util.JsonResourceUtil;
//import com.farben.readme.util.TimeUtil;
//import com.farben.readme.util.response.Response;
//import com.farben.readme.util.response.ResponseCode;
//import com.farben.readme.util.response.ResponseUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//
//@RestController
////@RequestMapping("/config/eroadHomePage")
//public class AdminConfigController {
//
//    private final static Logger logger = LoggerFactory.getLogger(AdminConfigController.class);
//
//    private final static String KEY = "home_page";
//
//    private final static String TYPE = "config";
//
//    @Autowired
//    private IEroadAdminConfigService eroadAdminConfigService;
//
//    @Autowired
//    private ILoginService loginService;
//
//    @Auth
//    @EditAuth
//    @PostMapping(path = "/config/eroadHomePage/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Response saveEroadConfig(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
//                                    @RequestHeader(value = Constant.HEADER_TOKNE) final String token,
//                                    @RequestBody EroadConfig eroadConfig) {
//        logger.info("==>saveEroadConfig requestId: {}", requestId);
//        try {
//            byte[] bytes = JSONObject.toJSONBytes(eroadConfig);
//            String staffId = loginService.getStaffIdFromLogonToken(token);
//            String staffName = loginService.getStaffNameFromLogonToken(token);
//
//            EroadAdminConfig eroadAdminConfig = new EroadAdminConfig();
//            eroadAdminConfig.setId(CommonUtils.uuid());
//            eroadAdminConfig.setKey(KEY);
//            eroadAdminConfig.setType(TYPE);
//            eroadAdminConfig.setCreatedBy(staffId);
//            eroadAdminConfig.setCreatedByName(staffName);
//            eroadAdminConfig.setCreatedTime(new Date());
//            eroadAdminConfig.setStatus(Constant.STATUS_TRUE);
//            eroadAdminConfig.setValue(bytes);
//            int i = eroadAdminConfigService.saveAndUpdateEroadAdminConfig(eroadAdminConfig);
//
//            return ResponseUtil.success(i);
//        } catch (Exception e) {
//            logger.error("==>saveEroadConfig requestId: {}, exception:", requestId, e);
//            return ResponseUtil.error(ResponseCode.INSERT_ERROR);
//        }
//    }
//
//    @Auth
//    @PostMapping(path = "/config/eroadHomePage/getLast", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Response getLastEroadConfig(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
//                                       @RequestHeader(value = Constant.HEADER_TOKNE) final String token) {
//        logger.info("==>getLastEroadConfig requestId: {}", requestId);
//        try {
//            String staffId = loginService.getStaffIdFromLogonToken(token);
//            EroadAdminConfig eroadAdminConfig = eroadAdminConfigService.getLastEroadAdminConfig(staffId);
//            if (eroadAdminConfig == null) {
//                logger.info("==>getLastEroadConfig requestId: {}, eroadAdminConfig: {}", requestId, null);
//                String filename = "/static/json/home_page_temp.json";
//                return ResponseUtil.success(JsonResourceUtil.getJsonObjFromResource(filename));
//            }
//            byte[] value = eroadAdminConfig.getValue();
//            if (value == null || value.length == 0) {
//                logger.info("==>getLastEroadConfig requestId: {}, eroadAdminConfig.getValue(): {}", requestId, null);
//                return ResponseUtil.error(ResponseCode.NOT_FOUND);
//            }
//            LastEroadConfigResp lastEroadConfigResp = JSONObject.parseObject(value, LastEroadConfigResp.class);
//            if (lastEroadConfigResp == null) {
//                logger.info("==>getLastEroadConfig requestId: {}, lastEroadConfigResp: {}", requestId, null);
//                return ResponseUtil.error(ResponseCode.NOT_FOUND);
//            }
//            lastEroadConfigResp.setId(eroadAdminConfig.getId());
//            lastEroadConfigResp.setPublishedId(eroadAdminConfig.getPublishedId());
//            lastEroadConfigResp.setIsPublished(eroadAdminConfig.getIsPublished());
//            lastEroadConfigResp.setSavedby(eroadAdminConfig.getCreatedByName());
//            String time = TimeUtil.getTime(eroadAdminConfig.getCreatedTime().getTime(), Constant.PATTERN_YYYY_MM_DD_HH_MM_SS);
//            lastEroadConfigResp.setSavedTime(time);
//            eroadAdminConfig = eroadAdminConfigService.getLastPublish();
//            if (eroadAdminConfig != null) {
//                lastEroadConfigResp.setPublishedBy(eroadAdminConfig.getCreatedByName());
//                time = TimeUtil.getTime(eroadAdminConfig.getCreatedTime().getTime(), Constant.PATTERN_YYYY_MM_DD_HH_MM_SS);
//                lastEroadConfigResp.setPublishedTime(time);
//            }
//            return ResponseUtil.success(lastEroadConfigResp);
//        } catch (Exception e) {
//            logger.error("==>getLastEroadConfig requestId: {}, exception:", requestId, e);
//            return ResponseUtil.error(ResponseCode.INTERNAL_ERROR);
//        }
//    }
//
//    @Auth
//    @EditAuth
//    @PostMapping(path = "/config/eroadHomePage/publish", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Response publishEroadConfig(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
//                                       @RequestHeader(value = Constant.HEADER_TOKNE) final String token,
//                                       @RequestBody EroadConfigReq eroadConfigReq) {
//        logger.info("==>publishEroadConfig requestId: {}", requestId);
//        try {
//            EroadConfig eroadConfig = new EroadConfig();
//            eroadConfig.setSection1(eroadConfigReq.getSection1());
//            eroadConfig.setSection2(eroadConfigReq.getSection2());
//            eroadConfig.setButtons(eroadConfigReq.getButtons());
//            byte[] bytes = JSONObject.toJSONBytes(eroadConfig);
//
//            String staffId = loginService.getStaffIdFromLogonToken(token);
//            String staffName = loginService.getStaffNameFromLogonToken(token);
//
//            EroadAdminConfig eroadAdminConfig = new EroadAdminConfig();
//            eroadAdminConfig.setId(CommonUtils.uuid());
//            eroadAdminConfig.setKey(KEY);
//            eroadAdminConfig.setType(TYPE);
//            eroadAdminConfig.setCreatedBy(staffId);
//            eroadAdminConfig.setCreatedByName(staffName);
//            eroadAdminConfig.setCreatedTime(new Date());
//            eroadAdminConfig.setStatus(Constant.STATUS_TRUE);
//            eroadAdminConfig.setValue(bytes);
//            int i = eroadAdminConfigService.publish(eroadAdminConfig, eroadConfigReq.getId());
//
//            return ResponseUtil.success(i);
//        } catch (Exception e) {
//            logger.error("==>publishEroadConfig requestId: {}, exception:", requestId, e);
//            return ResponseUtil.error(ResponseCode.PUBLISH_ERROR);
//        }
//    }
//
//    @PostMapping(path = "/v1/config/eroadHomePage/getLastPublish", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Response getLastPublish(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
//                                   @RequestHeader(value = Constant.HEADER_TOKNE) final String token) {
//        logger.info("==>getLastPublish requestId: {}", requestId);
//        try {
////            String staffId = loginService.getStaffIdFromLogonToken(token);
//            EroadAdminConfig eroadAdminConfig = eroadAdminConfigService.getLastPublish();
//            if (eroadAdminConfig == null) {
//                logger.info("==>getLastPublish requestId: {}, eroadAdminConfig: {}", requestId, null);
//                String filename = "/static/json/home_page_temp.json";
//                return ResponseUtil.success(JsonResourceUtil.getJsonObjFromResource(filename));
//            }
//            byte[] value = eroadAdminConfig.getValue();
//            if (value == null || value.length == 0) {
//                logger.info("==>getLastPublish requestId: {}, eroadAdminConfig.getValue(): {}", requestId, null);
//                return ResponseUtil.error(ResponseCode.NOT_FOUND);
//            }
//            EroadConfig eroadConfig = JSONObject.parseObject(value, EroadConfig.class);
//            if (eroadConfig == null) {
//                logger.info("==>getLastPublish requestId: {}, eroadConfig: {}", requestId, null);
//                return ResponseUtil.error(ResponseCode.NOT_FOUND);
//            }
//            return ResponseUtil.success(eroadConfig);
//        } catch (Exception e) {
//            logger.error("==>getLastPublish requestId: {}, exception:", requestId, e);
//            return ResponseUtil.error(ResponseCode.INTERNAL_ERROR);
//        }
//    }
//
//}
