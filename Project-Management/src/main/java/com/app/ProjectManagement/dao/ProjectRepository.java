package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,Long> {

    @Override
    public List<Project> findAll();
}
