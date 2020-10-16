package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.dao.EmployeeRepository;
import com.app.ProjectManagement.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String displayHome(Model model)
    {
        //query datavae for projects
        List<Project> projects= projectRepository.findAll();
        model.addAttribute("projectList",projects);
    //query database for employees
        List<Employee>employees=employeeRepository.findAll();
        model.addAttribute("employeeList",employees);
        return "home";
    }
}
