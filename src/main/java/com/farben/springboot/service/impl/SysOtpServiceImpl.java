package com.farben.springboot.service.impl;

import com.farben.springboot.bean.SysOtp;
import com.farben.springboot.mapper.SysOtpMapper;
import com.farben.springboot.service.SysOtpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysOtpServiceImpl implements SysOtpService {

    @Resource
    private SysOtpMapper sysOtpMapper;

    @Override
    public SysOtp getSysOtpById(String id) {
        return sysOtpMapper.getSysOtpById(id);
    }

    @Override
    public int insertSysOtp(SysOtp sysOtp) {
        return sysOtpMapper.insertSysOtp(sysOtp);
    }
}
