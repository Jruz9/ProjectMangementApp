package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
