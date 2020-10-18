package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.dao.EmployeeRepository;
import com.app.ProjectManagement.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired  //gives responsibility to the spring container to  inject bean into it
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String displayProjects(Model model){
        List<Project> projectList=proRepo.findAll();
        model.addAttribute("projects",projectList);
        return "projects/project-page";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model)
    {
        Project aProject=new Project();
        List<Employee> employees=employeeRepository.findAll();
        model.addAttribute("project",aProject);
        model.addAttribute("allEmployees",employees);
        return "projects/new-project";   //thymeLeaf is smart enough to know you are talking about the html file
    }
    @PostMapping("/save")
    public String createProjectForm(Project project, @RequestParam List<Long> employeeList, Model model)//employee list is not random it is the list name of the varriable in the projects class
    {
        //this should handle saving to the database...
        proRepo.save(project);

        Iterable<Employee> chosenEmployee=employeeRepository.findAllById(employeeList);
        for(Employee emp:chosenEmployee)
        {
            emp.setProject(project);
            employeeRepository.save(emp);
        }

        //use a redirect to prevent duplicate submissions
        return  "redirect:/projects/new";
    }

}
