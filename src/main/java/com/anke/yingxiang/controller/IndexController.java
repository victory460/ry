package com.anke.yingxiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by qingxiangzhang on 2017/11/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ResourceLoader resourceLoader;


//    @GetMapping({"/"})
//    public String home(Map<String, Object> model) {
//        return "index";
//    }

}
