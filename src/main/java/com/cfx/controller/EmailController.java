package com.cfx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenfuxian
 * @Date: 2022/1/14 20:45
 */
@RestController //返回json数据
public class EmailController {


    @GetMapping("/list")
//    @RequestParam(required = false) String password, @RequestParam(required = false) String account
    public String loadEmailMessage(@RequestParam(required = false) String account, @RequestParam(required = false) String password){
      return "hello";
    };
}
