package com.farben.readme.mongodb.controller;

import com.farben.readme.constant.Constant;
import com.farben.readme.mongodb.bean.New;
import com.farben.readme.mongodb.bean.User;
import com.farben.readme.util.response.Response;
import com.farben.readme.util.response.ResponseCode;
import com.farben.readme.util.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongodbController {

    private final static Logger logger = LoggerFactory.getLogger(MongodbController.class);

    @Autowired
    @Qualifier(value = "testdbMongoTemplate")
    protected MongoTemplate testdbMongoTemplate;

    @RequestMapping("/test")
    public String test() {
        //第一个数据库源testdb
        Query query = new Query();
        query.addCriteria(Criteria.where("account").is("admin"));
        User user = testdbMongoTemplate.findOne(query, User.class);
        return "来自第一个数据库源 " + user.toString();
    }

    @PostMapping(path = "/news", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getNewList(@RequestHeader(value = Constant.HEADER_REQUEST_ID) final String requestId,
                                       @RequestHeader(value = Constant.HEADER_TOKNE) final String token) {
        logger.info("==>getNewList requestId: {}", requestId);
        try {
            List<New> newList = testdbMongoTemplate.findAll(New.class);
            return ResponseUtil.success(newList);
        } catch (Exception e) {
            logger.error("==>getNewList requestId: {}, exception:", requestId, e);
            return ResponseUtil.error(ResponseCode.INTERNAL_ERROR);
        }
    }
}

