package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.Entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    @Override
    public List<Employee> findAll();
}
