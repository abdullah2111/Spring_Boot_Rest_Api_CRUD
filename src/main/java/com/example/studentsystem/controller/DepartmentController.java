package com.example.studentsystem.controller;


import com.example.studentsystem.model.Department;
import com.example.studentsystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/departments")
public class DepartmentController {

    private final DepartmentService departmentService;



    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department saved = departmentService.createDepartment(department);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/{departmentId}/head/{doctorId}")
    public ResponseEntity<Department> addHead(@PathVariable Long departmentId,
                                              @PathVariable Long doctorId) {

        Department updated = departmentService.addHeadDoctor(departmentId, doctorId);
        return ResponseEntity.ok(updated);
    }


    @PutMapping("/{departmentId}/head/{doctorId}")
    public ResponseEntity<Department> changeHeadDoctor(@PathVariable Long departmentId,
                                                    @PathVariable Long doctorId) {
        Department updated = departmentService.changeHeadDoctor(departmentId, doctorId);
        return ResponseEntity.ok(updated);
    }





    @DeleteMapping("/{departmentId}/head")
    public ResponseEntity<Department> removeHeadDoctor(@PathVariable Long departmentId) {
        Department updated = departmentService.removeHeadDoctor(departmentId);
        return ResponseEntity.ok(updated);
    }








}



