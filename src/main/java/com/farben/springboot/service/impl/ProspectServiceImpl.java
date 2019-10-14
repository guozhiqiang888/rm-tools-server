package com.farben.springboot.service.impl;

import com.farben.springboot.bean.prospect.Prospect;
import com.farben.springboot.mapper.prospect.ProspectMapper;
import com.farben.springboot.service.ProspectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProspectServiceImpl implements ProspectService {

    @Resource
    private ProspectMapper prospectMapper;

    @Override
    public List<Prospect> getProspectList() {
        return prospectMapper.getProspectList();
    }
}
