package com.project.CourseSystem.dto;

import com.project.CourseSystem.entity.Payment;
import com.project.CourseSystem.entity.UserInfo;
import lombok.*;

import java.sql.Date;
import java.util.Set;

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

    private Set<Payment> paymentID;

    private UserInfo userID;

}
