package com.project.CourseSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class nhapController {

    @GetMapping("/header")
    public String header(){
        return "Header";
    }

    @GetMapping("/footer")
    public String footer(){
        return "footer";
    }
}
