package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.Project;
import com.app.ProjectManagement.ProjectManagementApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = {"classpath:schema.sql","classpath:data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = {"classpath:drop.sql"})
})
public class ProjectRepositoryIntegrationTest {
    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void ifNewProjectSave_ThenSuccess()
    {
        Project newProject = new Project("New Test Project","Complete","Test Description");
        projectRepository.save(newProject);
        assertEquals(5,projectRepository.findAll().size());
    }

}
