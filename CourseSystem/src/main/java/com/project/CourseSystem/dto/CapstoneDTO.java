package com.project.CourseSystem.dto;

import com.project.CourseSystem.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapstoneDTO {

    private Integer capstoneID;

    private String capstoneName;

    private String capstoneImage;

    private String capstoneDes;

    private Date createdDate;

    private Category categoryID;
}
