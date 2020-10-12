package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @GetMapping("/new")
    public String displayProjectForm(Model model)
    {
        Project aProject=new Project();
        model.addAttribute("project",aProject);
        return "new-project";   //thymeLeaf is smart enough to know you are talking about the html file
    }
    @PostMapping("/save")
    public String createProjectForm(Project project,Model model)
    {
        //this should handle saving to the database...
        return  null;
    }

}
