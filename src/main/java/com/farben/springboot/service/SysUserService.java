package com.farben.springboot.service;

import com.farben.springboot.bean.SysUser;
import java.util.List;

public interface SysUserService {

    public List<SysUser> getAllSysUser();

    public SysUser getSysUserByUserId(String userId);

    public int deleteSysUserByUserId(String userId);

    public int insertSysUser(SysUser sysUser);

    public int updateSysUser(SysUser sysUser);

}
