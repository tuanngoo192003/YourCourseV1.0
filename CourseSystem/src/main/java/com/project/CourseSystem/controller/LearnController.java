package com.project.CourseSystem.controller;

import com.project.CourseSystem.converter.EnrolledConverter;
import com.project.CourseSystem.dto.*;
import com.project.CourseSystem.entity.Course;
import com.project.CourseSystem.entity.Enrolled;
import com.project.CourseSystem.entity.Lesson;
import com.project.CourseSystem.entity.Quiz;
import com.project.CourseSystem.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LearnController {

    private CourseService courseService;

    private QuizService quizService;

    private LessonService lessonService;

    private CategoryService categoryService;;

    private EnrolledService enrolledService;

    private EnrolledConverter enrolledConverter;

    private LearnController(LessonService lessonService, QuizService quizService, CourseService courseService
    , CategoryService categoryService, EnrolledService enrolledService, EnrolledConverter enrolledConverter) {
        this.lessonService = lessonService;
        this.quizService = quizService;
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.enrolledService = enrolledService;
        this.enrolledConverter = enrolledConverter;
    }

    @GetMapping("/learn")
    public String learnPage(@RequestParam Integer courseID, Model model,
                            HttpServletRequest request, HttpServletResponse response) {
        if(courseID == null) {
            return "redirect:/course";
        }
        else {
            int id = courseID.intValue();
            CourseDTO course = courseService.getCourseByID(id);

            List<LessonDTO> lessonList = lessonService.getAllByCourseID(id);

            List<QuizDTO> quizList = new ArrayList<>();
            for (LessonDTO lesson : lessonList) {
                quizList.add(quizService.getAllByLessonID(lesson.getLessonID()));
            }
            CategoryDTO cDto = new CategoryDTO();
            model.addAttribute("categoryDTO", cDto);
            CourseDTO courseDTO = new CourseDTO();
            model.addAttribute("courseDTO", courseDTO);
            model.addAttribute("category", categoryService.getAllCategories());
            model.addAttribute("course", course);
            model.addAttribute("lessonList", lessonList);
            model.addAttribute("quizList", quizList);

            HttpSession session = request.getSession();
            if(session.getAttribute("CSys")!=null){
                SystemAccountDTO accountDTO = (SystemAccountDTO) session.getAttribute("CSys");
                EnrolledDTO enrolledDTO = enrolledConverter.convertEntityToDTO(enrolledService.findByAccountIdAndCourseID(accountDTO.getAccountID(), courseID));
                model.addAttribute("enrolledDTO", enrolledDTO);
            }
            return "learn";
        }
    }
}
