package com.zsxk.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hellocontroller {
    @ResponseBody
    @RequestMapping("hello")
    public String hello() {

        return "hello";
    }


}
