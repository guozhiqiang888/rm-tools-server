package com.farben.springboot.service;

import com.farben.springboot.bean.SysOtp;

public interface SysOtpService {

    public SysOtp getSysOtpById(String id);

    public int insertSysOtp(SysOtp sysOtp);

}
