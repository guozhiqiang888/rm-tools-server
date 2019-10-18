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
    @RequestMapping(value={"/getTest/{status}"}, method= RequestMethod.GET)
    public Object getProspectTest(@PathVariable("status") String status){
        String filename = "/static/json/list/Prospect_List_Active.json";
        switch(status){
            case "active":
                break;
            case "closed":
                filename = "/static/json/list/Prospect_List_Closed.json";
                break;
            case "cold":
                filename = "/static/json/list/Prospect_List_Cold.json";
                break;
            default :
                break;
        }
        return JsonResourceUtil.getJsonObjFromResource(filename);
    }

    @ApiOperation(value="获取Prospect明细", notes="获取Prospect明细")
    @RequestMapping(value={"/getDetailTest/{id}"}, method= RequestMethod.GET)
    public Object getProspectDetailTest(@PathVariable("id") String id){
        String filename = "/static/json/detail/Prospect_Detail_1.json";
        switch(id){
            case "P1034328431":
                break;
            case "P1034328432":
                filename = "/static/json/detail/Prospect_Detail_2.json";
                break;
            case "P1034328433":
                filename = "/static/json/detail/Prospect_Detail_3.json";
                break;
            case "P1034328434":
                filename = "/static/json/detail/Prospect_Detail_4.json";
                break;
            case "P1034328435":
                filename = "/static/json/detail/Prospect_Detail_5.json";
                break;
            case "P1034328436":
                filename = "/static/json/detail/Prospect_Detail_6.json";
                break;
            case "P1034328437":
                filename = "/static/json/detail/Prospect_Detail_Closed_1.json";
                break;
            case "P1034328438":
                filename = "/static/json/detail/Prospect_Detail_Closed_2.json";
                break;
            case "P1034328439":
                filename = "/static/json/detail/Prospect_Detail_Cold_1.json";
                break;
            default :
                break;
        }
        return JsonResourceUtil.getJsonObjFromResource(filename);
    }
}
