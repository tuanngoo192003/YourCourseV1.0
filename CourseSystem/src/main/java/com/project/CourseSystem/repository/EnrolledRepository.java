package com.project.CourseSystem.repository;

import com.project.CourseSystem.entity.Enrolled;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolledRepository extends JpaRepository<Enrolled, Integer> {
}
