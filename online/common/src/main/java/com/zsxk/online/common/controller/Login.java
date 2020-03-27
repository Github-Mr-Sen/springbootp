package com.zsxk.online.common.controller;

import com.zsxk.online.common.response.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class Login {

    @PostMapping("/login")
    public Result login() {

        return Result.ok().code(20000).data("token", "admin");
    }

    @GetMapping("/info")
    public Result info() {

        return Result.ok().code(20000).
                data("roles", "[admin]").
                data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif").
                data("name","admin");
    }
}
