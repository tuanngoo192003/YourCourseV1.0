package com.project.CourseSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="courseDetails")
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="courseDetailsID", nullable = false)
    private Integer courseDetailsID;

    @Column(name="courseDetailsContent", nullable = false, length = 10000)
    private String courseDetailsContent;

    @OneToOne
    @JoinColumn(name="courseID", nullable = false)
    private Course courseID;
}
