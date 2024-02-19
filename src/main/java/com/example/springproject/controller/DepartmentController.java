package com.example.springproject.controller;

import com.example.springproject.entity.Department;
import com.example.springproject.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
@Validated      // ensuring the validation at class level. @Validated
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public List<Department> listDepartments(){
        return departmentService.fetchDepartmentList();
    }

    @PostMapping("/add")
    public Department insertDepartment(
            @Valid @RequestBody Department department){                 // @Valid annotation makes sure that any constraints set in the Bean of Department are satisfied in the Object received.
        department.setDepartmentId(String.valueOf(UUID.randomUUID()));
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/update/{departmentId}")
    public Department updateDepartment(
            @Valid @RequestBody Department department,
            @PathVariable("departmentId") String departmentId){

        department.setDepartmentId(departmentId);
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/delete/{departmentId}")
    public String deleteDepartment(
            @PathVariable("departmentId") String departmentId){

        departmentService.deleteDepartment(departmentId);
        return "Deleted successfully!";
    }
}
