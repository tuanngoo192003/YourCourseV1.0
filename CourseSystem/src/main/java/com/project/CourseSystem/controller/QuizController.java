package com.project.CourseSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {

    @GetMapping("quiz")
    public String getQuiz(){
        return "quiz";
    }
}
