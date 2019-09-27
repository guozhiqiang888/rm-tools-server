package com.farben.springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo class
 *
 * @author guozhiqiang
 * @date 2019/09/24
 */
@RestController
public class IndexController {

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        return "hello," + name;
    }

}
