package com.project.CourseSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="expertInfo")
public class ExpertInfo {

    @Id
    @Column(name="expertID", nullable = false)
    private Integer expertID;

    @Column(name="expertName", nullable = false, length = 20)
    private String expertName;

    @Column(name="aboutMe", nullable = false, length = 200)
    private String aboutMe;

    @Column(name="location", nullable = false, length = 100)
    private String location;

    @Column(name="dob", nullable = false)
    private String phoneNums;

    @Column(name="avatar", nullable = false, length = 255)
    private String avatar;

    @OneToOne
    @JoinColumn(name = "accountID", nullable = false)
    private SystemAccount accountID;
}
