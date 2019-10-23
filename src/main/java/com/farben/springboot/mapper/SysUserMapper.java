package com.farben.springboot.mapper;

import com.farben.springboot.bean.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

//指定这是一个操作数据库的mapper
//@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user")
    public List<SysUser> getAllSysUser();

    @Select("select * from sys_user where user_id=#{userId}")
    public SysUser getSysUserByUserId(String userId);

    @Delete("delete from sys_user where user_id=#{userId}")
    public int deleteSysUserByUserId(String userId);

//    @Options(useGeneratedKeys = true,keyProperty = "user_id")
    @Insert("insert into sys_user(user_id,password,user_name,phone,number,email,group_id) " +
            "values(#{userId},#{password},#{userName},#{phone},#{number},#{email},#{groupId})")
    public int insertSysUser(SysUser sysUser);

    @Update("update sys_user set password=#{password} where user_id=#{userId}")
    public int updateSysUser(SysUser sysUser);
}
