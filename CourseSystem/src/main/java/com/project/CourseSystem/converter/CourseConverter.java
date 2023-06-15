package com.project.CourseSystem.converter;

import com.project.CourseSystem.dto.CourseDTO;
import com.project.CourseSystem.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {

    public CourseDTO convertEntityToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseID(course.getCourseID());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseDes(course.getCourseDes());
        courseDTO.setCreatedDate(course.getCreatedDate());
        courseDTO.setCourseImage(course.getCourseImage());
        courseDTO.setPrice(course.getPrice());
        courseDTO.setCategoryID(course.getCategoryID());
        courseDTO.setCapstoneID(course.getCapstoneID());

        return courseDTO;
    }

    public Course convertDtoToEtity(CourseDTO courseDTO){
        Course course = new Course();
        course.setCourseID(courseDTO.getCourseID());
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseDes(courseDTO.getCourseDes());
        course.setCreatedDate(courseDTO.getCreatedDate());
        course.setCourseImage(courseDTO.getCourseImage());
        course.setPrice(courseDTO.getPrice());
        course.setCategoryID(courseDTO.getCategoryID());
        course.setCapstoneID(courseDTO.getCapstoneID());

        return course;
    }
}
