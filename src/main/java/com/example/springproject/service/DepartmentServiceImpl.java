package com.example.springproject.service;

import com.example.springproject.entity.Department;
import com.example.springproject.repository.DepartmentRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department) {

        if (departmentRepository.existsById(department.getDepartmentId())){
            return departmentRepository.save(department);
        }
        throw new ObjectNotFoundException("The record you tried to update, does not exist!", department);
    }

    @Override
    public void deleteDepartment(String departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
