package com.farben.springboot.controller;


import com.farben.springboot.bean.SysOtp;
import com.farben.springboot.service.SysOtpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/sysOtp")
@Api(tags = "SysOtpController", description = "SysOtpController | System one time password模块")
public class SysOtpController {

    @Resource
    public SysOtpService sysOtpService;

    @ApiOperation(value="根据userId获取SysOtp", notes="根据userId获取SysOtp")
    @RequestMapping(value={"/get"}, method= RequestMethod.GET)
    public SysOtp getSysOtp(@RequestParam("id") String id){
        return sysOtpService.getSysOtpById(id);
    }

    @ApiOperation(value="根据userId获取SysOtp", notes="根据userId获取SysOtp")
    @RequestMapping(value={"/save"}, method= RequestMethod.POST)
    public int saveSysOtp(@RequestBody SysOtp sysOtp){
        return sysOtpService.insertSysOtp(sysOtp);
    }

}
