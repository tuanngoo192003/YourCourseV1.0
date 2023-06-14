package com.project.CourseSystem.service;

import com.project.CourseSystem.dto.CourseDTO;
import com.project.CourseSystem.entity.Course;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses();

    List<CourseDTO> getAllCoursesByCapstoneID(int capstoneID);

    CourseDTO getCourseByID(int id);

    List<CourseDTO> getAllCoursesByCategoryID(int categoryID);
}
