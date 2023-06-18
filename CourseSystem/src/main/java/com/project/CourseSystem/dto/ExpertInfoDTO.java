package com.project.CourseSystem.dto;

import com.project.CourseSystem.entity.SystemAccount;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpertInfoDTO {

    private Integer expertID;

    private String expertName;

    private String aboutMe;

    private String location;

    private String phoneNums;

    private String avatar;

    private SystemAccount accountID;
}
