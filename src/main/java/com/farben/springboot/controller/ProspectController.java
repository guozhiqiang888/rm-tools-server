package com.farben.springboot.controller;

import com.farben.springboot.bean.prospect.Prospect;
import com.farben.springboot.service.ProspectService;
import com.farben.springboot.util.JsonResourceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
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

    @ApiOperation(value="获取Prospect明细", notes="获取Prospect明细")
    @RequestMapping(value={"/getDetailTest/{id}"}, method= RequestMethod.GET)
    public Object getProspectDetailTest(@PathVariable("id") String id){
        String filename = "/static/json/Prospect_Detail_Sample_1.json";
        if("P1034328431".equals(id)){
            filename = "/static/json/Prospect_Detail_Sample_1.json";
        } else if("P1034328432".equals(id)){
            filename = "/static/json/Prospect_Detail_Sample_2.json";
        } else if("P1034328433".equals(id)){
            filename = "/static/json/Prospect_Detail_Sample_3.json";
        }
        return JsonResourceUtil.getJsonObjFromResource(filename);
    }
}
