package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/new")
    public String displayEmployeeForm(Model model)
    {
        Employee newEmployee=new Employee();
        model.addAttribute("employee",newEmployee); //this connects the employee model and our new instance of em
        return "new-employee";  //html page for thymeleaf
    }
    @PostMapping("/save")
    public String createEmployeeForm(Model model, Employee employee)
    {
        //This handles saving the employee into the  database
        employeeRepository.save(employee);

        //this will redirect you to the project management page
        return "redirect:/projects/new";
    }




}
