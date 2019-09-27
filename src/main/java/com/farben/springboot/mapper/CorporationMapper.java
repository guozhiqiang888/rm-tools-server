package com.farben.springboot.mapper;

import com.farben.springboot.bean.Corporation;
import org.apache.ibatis.annotations.*;

import java.util.List;


//指定这是一个操作数据库的mapper
//@Mapper
public interface CorporationMapper {

    @Select("select * from corporation")
    public List<Corporation> getAllCorp();
}
