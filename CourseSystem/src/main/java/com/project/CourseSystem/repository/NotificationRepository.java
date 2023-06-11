package com.project.CourseSystem.repository;

import com.project.CourseSystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
