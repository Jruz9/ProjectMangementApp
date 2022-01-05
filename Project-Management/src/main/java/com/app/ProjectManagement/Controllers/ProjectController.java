package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.dao.EmployeeRepository;
import com.app.ProjectManagement.dao.ProjectRepository;
import com.app.ProjectManagement.services.EmployeeService;
import com.app.ProjectManagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired  //gives responsibility to the spring container to  inject bean into it
    ProjectService proService;

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayProjects(Model model){
        List<Project> projectList=proService.getAll();
        model.addAttribute("projects",projectList);
        return "projects/project-page";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model)
    {
        Project aProject=new Project();
        List<Employee> employees=empService.getAll();
        model.addAttribute("project",aProject);
        model.addAttribute("allEmployees",employees);
        return "projects/new-project";   //thymeLeaf is smart enough to know you are talking about the html file
    }
    @PostMapping("/save")
    public String createProjectForm(@Valid Project project, Model model, Errors errors)//employee list is not random it is the list name of the varriable in the projects class
    {
        //checks for errors in the front of our application:
        if(errors.hasErrors()){
            return "projects/new-project";
        }
        //this should handle saving to the database...
        proService.save(project);


        //use a redirect to prevent duplicate submissions
        return  "redirect:/projects";
    }

    @GetMapping("/update")
    public String displayProjectForm(@RequestParam("id")long projectId,Model model){
        Project project =proService.findByProject(projectId);
        model.addAttribute("project",project);
        return "projects/new-project";
    }
    @GetMapping("delete")
    public String deleteEmployee(@RequestParam("id") long projectId,Model model){
        Project theProject = proService.findByProject((projectId));
        proService.delete(theProject);
        return "redirect:/projects";
    }

}
