package com.app.ProjectManagement.services;

import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.dao.ProjectRepository;
import com.app.ProjectManagement.dto.chartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository proRepo;
    //save, getall. get project status

    public Project save(Project project){
        return proRepo.save(project);
    }

    public List<Project> getAll(){
        return proRepo.findAll();
    }

    public List<chartData>getProjectStatus(){
        return  proRepo.getProjectStatus();
    }

    public Project findByProject(long projectId) {
        return proRepo.findByProjectId(projectId);
    }

    public void delete(Project theProject) {
        proRepo.delete(theProject);
    }
}
