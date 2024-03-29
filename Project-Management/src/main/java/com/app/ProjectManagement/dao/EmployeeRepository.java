package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.dto.EmployeeProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


import java.util.List;

@RepositoryRestResource(collectionResourceRel = "apiemployees",path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {

//    @Override
//    public List<Employee> findAll();

    @Query(nativeQuery = true,value = "select e.first_name as firstName , e.last_name as lastName," +
            "COUNT (pe.EMPLOYEE_ID) as projectCount  " +
            "FROM employee e LEFT JOIN PROJECT_EMPLOYEE pe ON pe.EMPLOYEE_ID= e.EMPLOYEE_ID " +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 desc")
    public List<EmployeeProject> employeeProjects();

    public  Employee findByEmail(String value); //give name we dont have to specifiy to much information using the by format

    Employee findByEmployeeId(long theid);

//    public Employee findEmployeeByEmployeeId(long id);
}
