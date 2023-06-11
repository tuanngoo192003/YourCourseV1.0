package com.project.CourseSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="capstone")
public class Capstone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="capstoneID", nullable = false)
    private Integer capstoneID;

    @Column(name="capstoneName", nullable = false, length = 60)
    private String capstoneName;

    @Column(name="capstoneImage", nullable = false, length = 200)
    private String capstoneImage;

    @Column(name="capstoneDes", nullable = false, length = 200)
    private String capstoneDes;

    @Column(name="createdDate", nullable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="categoryID", nullable = false)
    private Category categoryID;
}
