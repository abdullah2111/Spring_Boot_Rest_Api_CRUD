package com.example.studentsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @OneToOne
    @JsonIgnore
    private Doctor headDoctor;


    @ManyToMany
    @JoinTable(
            name = "docotr_dept_Tbl",
            joinColumns =  @JoinColumn(name = "dpt_id"),
            inverseJoinColumns = @JoinColumn(name = "docotr_id")
    )
    @JsonIgnore
    private Set<Doctor> doctors = new HashSet<>();

}
