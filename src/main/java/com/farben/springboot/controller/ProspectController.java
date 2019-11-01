package com.farben.springboot.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farben.springboot.bean.prospect.Prospect;
import com.farben.springboot.bean.request.ProspectAssignBatchEditRequest;
import com.farben.springboot.service.ProspectService;
import com.farben.springboot.util.JsonResourceUtil;
import com.farben.springboot.util.response.Response;
import com.farben.springboot.util.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/prospects")
@Api(tags = "ProspectController", description = "ProspectController | Prospect模块")
public class ProspectController {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Resource
//    private ProspectService prospectService;

//    @ApiOperation(value = "获取Prospect列表", notes = "获取Prospect列表")
//    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
//    public List<Prospect> getProspectList() {
//        return prospectService.getProspectList();
//    }


    @ApiOperation(value = "获取Prospect列表", notes = "获取Prospect列表")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public Object getProspectsList(@RequestParam("ProspectStatus") String ProspectStatus, @RequestParam("AssigneeID") String AssigneeID,
                                   Boolean IncludeSubordinates, Integer PageCount, @RequestParam("PageIndex") Integer PageIndex) {
        logger.info("=================getProspectsList===============>begin: " + ProspectStatus);
        String filename = "/static/json/list/Prospect_List_Active.json";
        switch (ProspectStatus) {
            case "Active":
                break;
            case "Closed":
                filename = "/static/json/list/Prospect_List_Closed.json";
                break;
            case "Cold":
                filename = "/static/json/list/Prospect_List_Cold.json";
                break;
            default:
                break;
        }
        logger.info("=================getProspectsList===============>end: " + filename);
        return JsonResourceUtil.getJsonObjFromResource(filename);
    }

    @ApiOperation(value = "获取Prospect明细", notes = "获取Prospect明细")
    @RequestMapping(value = {"/{ProspectID}"}, method = RequestMethod.GET)
    public Object getProspectDetail(@PathVariable("ProspectID") String ProspectID) {
        String filename = "/static/json/detail/Prospect_Detail_1.json";
        switch (ProspectID) {
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
            default:
                break;
        }
        return JsonResourceUtil.getJsonObjFromResource(filename);
    }

    @ApiOperation(value = "更新Prospect", notes = "更新Prospect")
    @RequestMapping(value = {"/{ProspectID}"}, method = RequestMethod.PATCH)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PersonID", value = "用户标识", required = true, paramType = "header", dataType = "String")
    })
    public Response updateProspect(@PathVariable("ProspectID") String ProspectID, String PersonID,
                                   String ClientVisionName, String ContactPerson,
                                   String ConfirmedOnBoardingName, String Status, Date DueDate,
                                   String Priority, String AssigneeID, boolean HasViewed,
                                   String HUBCustomerCode, Date ExpectedConversionDate) {
        if (StringUtils.isEmpty(PersonID)) {
            return ResponseUtil.error("HSBC-E001", "E001", "Error: Update Error!", null);
        }
        //***********调用 Jason提供的update prospect api*********(start)****//
        //
        //***********调用 Jason提供的update prospect api*********(end)****//
        JSONObject jsonObj = null;
        try {
            String filename = "/static/json/list/Prospect_List_Active.json";
            jsonObj = JsonResourceUtil.getJsonObjFromResource(filename);
            JSONArray prospects = jsonObj.getJSONArray("Prospects");
            //JSON文件中的第一个prospect
            JSONObject jsonObject = prospects.getJSONObject(0);
            prospects.remove(jsonObject);
            if (!StringUtils.isEmpty(Status)) {
                jsonObject.fluentPut("Status", Status);
            }
            if (!StringUtils.isEmpty(Priority)) {
                jsonObject.fluentPut("Priority", Priority);
            }
            if (!StringUtils.isEmpty(AssigneeID)) {
                jsonObject.fluentPut("AssigneeID", AssigneeID);
            }
            prospects.fluentAdd(0, jsonObject);
            if (!StringUtils.isEmpty(Status) || !StringUtils.isEmpty(Priority)) {
                URL url = ProspectController.class.getResource(filename);
                String path = url.getPath();
                File file = new File(path);
                if (file.exists()) {
                    String jsonStr = jsonObj.toJSONString();
                    FileUtils.writeStringToFile(file, jsonStr, "UTF-8", false);
                }
            }
        } catch (Exception e) {
            return ResponseUtil.error("HSBC-E001", "E001", "Error: Update Error!", null);
        }
        return ResponseUtil.success(jsonObj.toJSONString());
    }

    @ApiOperation(value = "获取Prospect Contact List", notes = "获取Prospect Contact List")
    @RequestMapping(value = {"/{ProspectID}/contacts"}, method = RequestMethod.GET)
    public Object getProspectContactList(@PathVariable("ProspectID") String ProspectID) {
        logger.info("=================getProspectContactList===============>begin: ");
        JSONObject jsonObject = JsonResourceUtil.getJsonObjFromResource("/static/json/list/Prospect_Contact_List.json");
        logger.info("=================getProspectContactList===============>end: ");
        return jsonObject;
    }

    @ApiOperation(value = "获取Prospect Comments List", notes = "获取Prospect Comments List")
    @RequestMapping(value = {"/{ProspectID}/comments"}, method = RequestMethod.GET)
    public Object getProspectCommentsList(@PathVariable("ProspectID") String ProspectID) {
        logger.info("=================getProspectCommentsList===============>begin: ");
        JSONObject jsonObject = JsonResourceUtil.getJsonObjFromResource("/static/json/list/Prospect_Comment_List_API_Data.json");
        logger.info("=================getProspectCommentsList===============>end: ");
        return jsonObject;
    }

    @ApiOperation(value = "创建Prospect Comments", notes = "创建Prospect Comments")
    @RequestMapping(value = {"/{ProspectID}/comments"}, method = RequestMethod.POST)
    public Object createProspectComments(@PathVariable("ProspectID") String ProspectID, @RequestParam("Commentary") String Commentary) {
        logger.info("=================createProspectComments===============>begin: ");
        logger.info("=================createProspectComments===============>end: ");
        return null;
    }

    @ApiOperation(value = "批量更新assign", notes = "批量更新assign")
    @RequestMapping(value = {"/assign/batch"}, method = RequestMethod.PATCH)
    public Response updateProspect(@RequestBody ProspectAssignBatchEditRequest request) {
        System.out.println(request.toString());
        //TODO: batch edit prospect assignID
        return ResponseUtil.success();

    }


}
