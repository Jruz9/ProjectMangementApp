package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.dto.chartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {

    @Override
    public List<Project> findAll();

    //show counts of project status complete incomplete etc...
    @Query(nativeQuery = true, value = "select stage as label, count (*) as value " +
            "from project " +
            "group by stage")

    public List<chartData>getProjectStatus();
}
