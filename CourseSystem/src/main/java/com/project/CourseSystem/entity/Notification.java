package com.project.CourseSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notificationID", nullable = false)
    private Integer notificationID;

    @Column(name="title", nullable = false, length = 200)
    private String content;

    @OneToOne
    @JoinColumn(name="accountID", nullable = false)
    private SystemAccount accountID;
}
