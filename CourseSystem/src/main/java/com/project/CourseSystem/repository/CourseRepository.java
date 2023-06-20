package com.project.CourseSystem.repository;

import com.project.CourseSystem.dto.CourseDTO;
import com.project.CourseSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "SELECT * FROM Course WHERE capstoneID = ?1", nativeQuery = true)
    List<Course> getAllByCapstoneID(int capstoneID);

    @Query(value = "SELECT * FROM Course WHERE categoryID = ?1", nativeQuery = true)
    List<Course> getAllByCategoryID(int categoryID);

    @Query(value = "SELECT * FROM Course WHERE course_name LIKE %?1%", nativeQuery = true)
    List<Course> getAllByCourseNameContaining(String courseName);
}
