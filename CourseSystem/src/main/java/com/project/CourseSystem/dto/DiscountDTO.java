package com.project.CourseSystem.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountDTO {

    private Integer discountID;

    private Date startDate;

    private Date endDate;

    private Integer discountPercent;

}
