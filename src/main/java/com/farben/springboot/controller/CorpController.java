package com.farben.springboot.controller;


import com.farben.springboot.bean.Corporation;
import com.farben.springboot.mapper.CorporationMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/corp")
@Api(tags = "CorpController", description = "CorpController | 公司测试模块")
public class CorpController {

    @Resource
    CorporationMapper corporationMapper;

    @ApiOperation(value="获取公司列表", notes="获取公司列表")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    public List<Corporation> getAllCorp(){
        return corporationMapper.getAllCorp();
    }

}
