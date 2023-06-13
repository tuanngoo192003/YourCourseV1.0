package com.project.CourseSystem.controller;

import com.project.CourseSystem.dto.CategoryDTO;
import com.project.CourseSystem.service.CapstoneService;
import com.project.CourseSystem.service.CategoryService;
import com.project.CourseSystem.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

    private CourseService courseService;

    private CapstoneService capstoneService;

    private CategoryService categoryService;

    @Autowired
    public CourseController(CourseService courseService, CapstoneService capstoneService, CategoryService categoryService) {
        this.courseService = courseService;
        this.capstoneService = capstoneService;
        this.categoryService = categoryService;
    }

    @GetMapping("/course")
    public String getCourse(Model model, HttpServletRequest request, HttpServletResponse response) {
        CategoryDTO cDto = new CategoryDTO();
        model.addAttribute("categoryDTO", cDto);
        model.addAttribute("category", categoryService.getAllCategories());
        model.addAttribute("courseList", courseService.getAllCourses());
        return "list";
    }

    @GetMapping("/courseDetails")
    public String getCourseDetails(@RequestParam Integer courseID, Model model,
                                   HttpServletRequest request, HttpServletResponse response) {
        if(courseID == null) {
            return "redirect:/course";
        }

        int id = courseID.intValue();
        model.addAttribute("course", courseService.getCourseByID(id));
        return "courseDetails";
    }

    @GetMapping("/capstone")
    public String getCapstone(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("category", categoryService.getAllCategories());
        model.addAttribute("capstoneList", capstoneService.getAllCapstones());
        return "list";
    }

    @GetMapping("/capstoneDetails")
    public String getCapstoneDetails(@RequestParam Integer capstoneID, Model model,
                                     HttpServletRequest request, HttpServletResponse response) {
        if(capstoneID == null) {
            return "redirect:/capstone";
        }
        int id = capstoneID.intValue();
        model.addAttribute("courseBelongToCapstone", courseService.getAllCoursesByCapstoneID(id));
        return "capstoneDetails";
    }

    @PostMapping("/filter")
    public String filter(@ModelAttribute("category") CategoryDTO categoryDTO, Model model,
                         HttpServletRequest request, HttpServletResponse response) {
        CategoryDTO temp = new CategoryDTO();
        model.addAttribute("categoryDTO", temp);
        model.addAttribute("category", categoryService.getAllCategories());
        temp = categoryService.getCategoryByName(categoryDTO.getCategoryName());
        int categoryID = temp.getCategoryID();
        model.addAttribute("courseList", courseService.getAllCoursesByCategoryID(categoryID));
        return "list";
    }
}
