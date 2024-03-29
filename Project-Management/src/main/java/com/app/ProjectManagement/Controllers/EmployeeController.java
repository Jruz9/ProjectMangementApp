package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.dao.EmployeeRepository;
import com.app.ProjectManagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.RequestWrapper;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

//    @Autowired  //injects instance of employee repo
//    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayEmployees(Model model){
        List<Employee>employeeList=empService.getAll();
        model.addAttribute("employees",employeeList);
        return "employees/employees-home";
    }

    @GetMapping("/new") //displays employee form
    public String displayEmployeeForm(Model model)
    {
        Employee newEmployee=new Employee();
        model.addAttribute("employee",newEmployee); //this allows us to link a key with our model for thymeleaf to reference
        return "employees/new-employee";  //html page for thymeleaf
    }
    @PostMapping("/save") //save button save the inputs
    public String createEmployeeForm(Model model, @Valid Employee employee, Errors errors)
    {
        if(errors.hasErrors()){
            return "employee/new-employee";
        }
        //This handles saving the employee into the  database
        empService.save(employee);

        //this will redirect you to the project management page
        return "redirect:/employee/new";
    }



    @GetMapping("/update")
    public String displayEmployeeForm(@RequestParam("id" )long theid,Model model){
        Employee theEmp=empService.findByEmployee(theid);
        model.addAttribute("employee",theEmp);
        return "employees/new-employee";
    }

    @GetMapping("delete")
    public String deleteEmployee(@RequestParam("id" )long theid,Model model){
        Employee theEmp= empService.findByEmployee(theid);
        empService.delete(theEmp);
        return "redirect:/employee/new";
    }

}
