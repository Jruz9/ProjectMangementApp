package com.app.ProjectManagement.services;
import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.dao.EmployeeRepository;
import com.app.ProjectManagement.dto.EmployeeProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepo;
    public Employee save(Employee employee){
        return empRepo.save(employee);
    }
    public List<Employee>getAll(){
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects(){
        return  empRepo.employeeProjects();
    }




}
