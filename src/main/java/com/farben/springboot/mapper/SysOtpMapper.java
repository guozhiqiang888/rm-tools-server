package com.farben.springboot.mapper;

import com.farben.springboot.bean.SysOtp;

//@Mapper或者@MapperScan将接口扫描装配到容器中
public interface SysOtpMapper {

    public SysOtp getSysOtpById(String id);

    public int insertSysOtp(SysOtp sysOtp);
}
