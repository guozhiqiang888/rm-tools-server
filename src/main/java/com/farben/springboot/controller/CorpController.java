package com.farben.springboot.controller;


import com.farben.springboot.bean.Corporation;
import com.farben.springboot.mapper.CorporationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CorpController {

    @Autowired
    CorporationMapper corporationMapper;

    @GetMapping("/corp")
    public List<Corporation> getAllCorp(){
        return corporationMapper.getAllCorp();
    }


}
