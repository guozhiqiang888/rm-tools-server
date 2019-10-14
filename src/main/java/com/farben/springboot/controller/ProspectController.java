package com.farben.springboot.controller;

import com.farben.springboot.bean.prospect.Prospect;
import com.farben.springboot.service.ProspectService;
import com.farben.springboot.util.JsonResourceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/prospect")
@Api(tags = "ProspectController", description = "ProspectController | Prospect测试模块")
public class ProspectController {

    @Resource
    ProspectService prospectService;

    @ApiOperation(value="获取Prospect列表", notes="获取Prospect列表")
    @RequestMapping(value={"/get"}, method= RequestMethod.GET)
    public List<Prospect> getProspectList(){
        return prospectService.getProspectList();
    }


    @ApiOperation(value="获取Prospect列表", notes="获取Prospect列表")
    @RequestMapping(value={"/getTest"}, method= RequestMethod.GET)
    public Object getProspectTest(){
        return JsonResourceUtil.getJsonObjFromResource("/static/json/Prospect_List_Reponse_Sample.json");
    }
}
