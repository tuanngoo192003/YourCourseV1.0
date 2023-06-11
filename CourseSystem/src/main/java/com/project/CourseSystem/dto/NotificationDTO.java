package com.project.CourseSystem.dto;

import com.project.CourseSystem.entity.SystemAccount;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

    private Integer notificationID;

    private String content;

    private SystemAccount accountID;
}
