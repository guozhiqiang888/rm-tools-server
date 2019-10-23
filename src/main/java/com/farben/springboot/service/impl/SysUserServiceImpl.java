package com.farben.springboot.service.impl;

import com.farben.springboot.bean.SysUser;
import com.farben.springboot.mapper.SysUserMapper;
import com.farben.springboot.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getAllSysUser() {
        return sysUserMapper.getAllSysUser();
    }

    @Override
    public SysUser getSysUserByUserId(String userId) {
        return sysUserMapper.getSysUserByUserId(userId);
    }

    @Override
    public int deleteSysUserByUserId(String userId) {
        return sysUserMapper.deleteSysUserByUserId(userId);
    }

    @Override
    public int insertSysUser(SysUser sysUser) {
        return sysUserMapper.insertSysUser(sysUser);
    }

    @Override
    public int updateSysUser(SysUser sysUser) {
        return sysUserMapper.updateSysUser(sysUser);
    }
}
