package com.project.CourseSystem.repository;

import com.project.CourseSystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
