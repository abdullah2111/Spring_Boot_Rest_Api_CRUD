package com.example.studentsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @OneToOne
    private Doctor headDocotr;


    @ManyToMany
    @JoinTable(
            name = "docotr_dept_Tbl",
            joinColumns =  @JoinColumn(name = "dpt_id"),
            inverseJoinColumns = @JoinColumn(name = "docotr_id")
    )
    private Set<Doctor> doctors = new HashSet<>();

}
