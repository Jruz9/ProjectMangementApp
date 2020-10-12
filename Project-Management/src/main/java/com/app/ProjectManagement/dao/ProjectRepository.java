package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long> {


}
