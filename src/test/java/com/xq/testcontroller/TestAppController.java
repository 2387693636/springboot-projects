package com.xq.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @DATE : 2020/3/19
 * @USER : xq2580z
 * @DESCRIPTION :
 **/
@Controller
public class TestAppController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/index")
    public String index() {
        return "/system/main/index";
    }
    @RequestMapping("/d")
    public String indx() {
        return "/system/main/index";
    }
}
