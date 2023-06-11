package com.project.CourseSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="courseID", nullable = false)
    private Integer courseID;

    @Column(name="courseName", nullable = false, length = 50)
    private String courseName;

    @Column(name="courseImage", nullable = false, length = 200)
    private String courseImage;

    @Column(name="courseDes", nullable = false, length = 200)
    private String courseDes;

    @Column(name="createdDate", nullable = false)
    private Date createdDate;

    @Column(name="price", nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn(name="categoryID", nullable = false)
    private Category categoryID;

    @ManyToOne
    @JoinColumn(name="capstoneID", nullable = true)
    private Capstone capstoneID;

    @ManyToMany
    @JoinTable(
            name = "courseAccount",
            joinColumns = @JoinColumn(name = "courseID"),
            inverseJoinColumns = @JoinColumn(name = "accountID"))
    private Set<SystemAccount> accountID;
}
