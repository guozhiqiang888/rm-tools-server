package com.farben.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/people")
@Api(tags = "PeopleController", description = "PeopleController | People模块")
public class PeopleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "获取People列表", notes = "获取People列表")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public Object getPeopleList() {
        logger.info("=================getPeopleList===============>begin: ");
        logger.info("=================getPeopleList===============>end: ");
        return null;
    }

}
