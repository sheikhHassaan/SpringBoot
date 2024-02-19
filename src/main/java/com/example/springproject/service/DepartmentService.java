package com.example.springproject.service;

import com.example.springproject.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> fetchDepartmentList();
    Department updateDepartment(Department department   );
    void deleteDepartment(String departmentId);
}
