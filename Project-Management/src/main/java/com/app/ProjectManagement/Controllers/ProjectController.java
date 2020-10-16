package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired  //gives responsibility to the spring container to  inject bean into it
    ProjectRepository proRepo;

    @GetMapping("/new")
    public String displayProjectForm(Model model)
    {
        Project aProject=new Project();
        model.addAttribute("project",aProject);
        return "projects/new-project";   //thymeLeaf is smart enough to know you are talking about the html file
    }
    @PostMapping("/save")
    public String createProjectForm(Project project,Model model)
    {
        //this should handle saving to the database...
        proRepo.save(project);

        //use a redirect to prevent duplicate submissions
        return  "redirect:/projects/new";
    }

}
