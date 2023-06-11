package com.project.CourseSystem.repository;

import com.project.CourseSystem.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
