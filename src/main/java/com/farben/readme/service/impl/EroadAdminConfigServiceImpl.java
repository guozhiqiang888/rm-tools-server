//package com.farben.readme.service.impl;
//
//import com.alibaba.druid.util.StringUtils;
//import com.farben.readme.bean.EroadAdminConfig;
//import com.farben.readme.constant.Constant;
//import com.farben.readme.mapper.EroadAdminConfigMapper;
//import com.farben.readme.service.IEroadAdminConfigService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class EroadAdminConfigServiceImpl implements IEroadAdminConfigService {
//
//    private final static Logger logger = LoggerFactory.getLogger(EroadAdminConfigServiceImpl.class);
//
//    @Autowired
//    private EroadAdminConfigMapper eroadAdminConfigMapper;
//
//    @Override
//    public EroadAdminConfig getLastEroadAdminConfig(String staffId) {
//        return eroadAdminConfigMapper.getLast(staffId);
//    }
//
//    @Transactional
//    @Override
//    public int saveAndUpdateEroadAdminConfig(EroadAdminConfig eroadAdminConfig) {
//        String version = eroadAdminConfigMapper.queryLastVersion();
//        if (StringUtils.isEmpty(version)) {
//            eroadAdminConfig.setVersion(0);
//        } else {
//            eroadAdminConfig.setVersion(Long.parseLong(version) + 1);
//        }
//        EroadAdminConfig updateEroadAdminConfig = new EroadAdminConfig();
//        updateEroadAdminConfig.setUpdatedBy(eroadAdminConfig.getCreatedBy());
//        updateEroadAdminConfig.setUpdatedByName(eroadAdminConfig.getCreatedByName());
//        updateEroadAdminConfig.setUpdatedTime(eroadAdminConfig.getCreatedTime());
//        int i = eroadAdminConfigMapper.updateConfigStatus(updateEroadAdminConfig);
//        int i2 = eroadAdminConfigMapper.saveEroadAdminConfig(eroadAdminConfig);
//        return i + i2;
//    }
//
//    @Transactional
//    @Override
//    public int publish(EroadAdminConfig eroadAdminConfig, String id) {
//        String version = eroadAdminConfigMapper.queryLastPublishVersion();
//        if (StringUtils.isEmpty(version)) {
//            eroadAdminConfig.setVersion(0);
//        } else {
//            eroadAdminConfig.setVersion(Long.parseLong(version) + 1);
//        }
//        EroadAdminConfig updateEroadAdminConfig = new EroadAdminConfig();
//        updateEroadAdminConfig.setUpdatedBy(eroadAdminConfig.getCreatedBy());
//        updateEroadAdminConfig.setUpdatedByName(eroadAdminConfig.getCreatedByName());
//        updateEroadAdminConfig.setUpdatedTime(eroadAdminConfig.getCreatedTime());
//        int i = eroadAdminConfigMapper.updatePublishStatus(updateEroadAdminConfig);
//        int i2 = eroadAdminConfigMapper.saveEroadAdminConfigPublish(eroadAdminConfig);
//
//        EroadAdminConfig update2 = new EroadAdminConfig();
//        update2.setId(id);
//        update2.setIsPublished(Constant.IS_PUBLISHED_YES);
//        update2.setPublishedId(eroadAdminConfig.getId());
//        int i3 = eroadAdminConfigMapper.updateEroadAdminConfigById(update2);
//        return i + i2 + i3;
//    }
//
//    @Override
//    public EroadAdminConfig getLastPublish() {
//        return eroadAdminConfigMapper.getLastPublish();
//    }
//}
