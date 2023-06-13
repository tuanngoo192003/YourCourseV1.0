package com.project.CourseSystem.dto;

import com.project.CourseSystem.entity.Course;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDetailsDTO {
    private Integer courseDetailsID;

    private String courseDetailsContent;

    private Course courseID;
}
