package com.project.CourseSystem.service.impl;

import com.project.CourseSystem.converter.CourseConverter;
import com.project.CourseSystem.dto.CourseDTO;
import com.project.CourseSystem.entity.Course;
import com.project.CourseSystem.repository.CourseRepository;
import com.project.CourseSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;

    CourseConverter courseConverter;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseConverter courseConverter) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courseList = new ArrayList<>();
        courseRepository.findAll().forEach(course ->
                courseList.add(courseConverter.convertEntityToDTO(course)));
        return courseList;
    }

    @Override
    public List<CourseDTO> getAllCoursesByCapstoneID(int capstoneID) {
        List<CourseDTO> courseList = new ArrayList<>();
        courseRepository.getAllByCapstoneID(capstoneID).forEach(course ->
                courseList.add(courseConverter.convertEntityToDTO(course)));
        return courseList;
    }

    @Override
    public CourseDTO getCourseByID(int id) {
        CourseDTO courseDTO = courseConverter.convertEntityToDTO(courseRepository.findById(id).get());
        return courseDTO;
    }
}
