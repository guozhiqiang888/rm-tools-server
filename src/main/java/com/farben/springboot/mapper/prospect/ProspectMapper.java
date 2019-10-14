package com.farben.springboot.mapper.prospect;

import com.farben.springboot.bean.prospect.Prospect;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * prospect mapper
 *
 * @author guozhiqiang
 * @date 2019/10/11
 */
public interface ProspectMapper {

    @Select("select * from prospect")
    public List<Prospect> getProspectList();
}
