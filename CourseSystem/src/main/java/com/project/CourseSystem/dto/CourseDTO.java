package com.project.CourseSystem.dto;

import com.project.CourseSystem.entity.Capstone;
import com.project.CourseSystem.entity.Category;
import com.project.CourseSystem.entity.SystemAccount;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Integer courseID;

    private String courseName;

    private String courseImage;

    private String courseDes;

    private Date createdDate;

    private Float price;

    private Category categoryID;

    private Capstone capstoneID;
}
