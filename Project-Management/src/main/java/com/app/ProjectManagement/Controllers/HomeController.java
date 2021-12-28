package com.app.ProjectManagement.Controllers;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.dao.EmployeeRepository;
import com.app.ProjectManagement.dao.ProjectRepository;
import com.app.ProjectManagement.dto.EmployeeProject;
import com.app.ProjectManagement.dto.chartData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Value(value = "${version:default value}") //replaces the version to let spring profiles run
    private  String ver;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber",ver);
        Map<String,Object> map =new HashMap<>();
        //query database for projects
        List<Project> projects= projectRepository.findAll();
        model.addAttribute("projectList",projects);


        List<chartData> projectData= projectRepository.getProjectStatus();
        //lets convert obbject into a json structure for use in javascript
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonString=objectMapper.writeValueAsString(projectData);

        model.addAttribute("ProjectStatusCnt",jsonString);

        //query database for employees
        List<EmployeeProject> employeeProjectCnt=employeeRepository.employeeProjects();
        model.addAttribute("employeeListProjectCnt",employeeProjectCnt);
        return "main/home";
    }
}
