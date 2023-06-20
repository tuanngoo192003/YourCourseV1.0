package com.project.CourseSystem.controller;

import com.project.CourseSystem.dto.CategoryDTO;
import com.project.CourseSystem.dto.CourseDTO;
import com.project.CourseSystem.entity.Course;
import com.project.CourseSystem.service.CategoryService;
import com.project.CourseSystem.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    private CourseService courseService;

    private CategoryService categoryService;

    @Autowired
    public CourseController(CourseService courseService,  CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping("/course")
    public String getCourse(Model model, HttpServletRequest request, HttpServletResponse response){
        //pagination
        return getPaginated(1, "courseID", "asc", model, request, response);
    }

    @GetMapping("/course/page/{pageNo}")
    public String getPaginated(@PathVariable (value = "pageNo") int pageNo,
                               @RequestParam("sortField") String sortField,
                               @RequestParam("sortDir") String sortDir,
                               Model model, HttpServletRequest request, HttpServletResponse response){
        int pageSize = 18;
        //pagination attribute
        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> courseList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        model.addAttribute("courseList", courseList);

        //nav bar attribute
        CategoryDTO cDto = new CategoryDTO();
        CourseDTO courseDTO = new CourseDTO();
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("categoryDTO", cDto);
        model.addAttribute("category", categoryService.getAllCategories());
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

    @PostMapping("/filter")
    public String filter(@ModelAttribute("category") CategoryDTO categoryDTO, Model model,
                         HttpServletRequest request, HttpServletResponse response) {
        CourseDTO courseDTO = new CourseDTO();
        model.addAttribute("courseDTO", courseDTO);
        CategoryDTO temp = new CategoryDTO();
        model.addAttribute("categoryDTO", temp);
        model.addAttribute("category", categoryService.getAllCategories());
        temp = categoryService.getCategoryByName(categoryDTO.getCategoryName());
        int categoryID = temp.getCategoryID();
        model.addAttribute("courseList", courseService.getAllCoursesByCategoryID(categoryID));
        return "list";
    }

    @GetMapping("/getCourseByCategoryID")
    public String getCourseByCategoryID(@RequestParam Integer categoryID, Model model,
                         HttpServletRequest request, HttpServletResponse response){
        CourseDTO courseDTO = new CourseDTO();
        model.addAttribute("courseDTO", courseDTO);
        CategoryDTO temp = new CategoryDTO();
        model.addAttribute("categoryDTO", temp);
        model.addAttribute("category", categoryService.getAllCategories());
        model.addAttribute("courseList", courseService.getAllCoursesByCategoryID(categoryID));
        return "list";
    }
}
