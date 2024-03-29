package com.app.ProjectManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "project_seq")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "project_seq")
    private Long projectId;
    @NotBlank(message = "Enter the name of your project.")

    private String name;
    @NotBlank(message = "*Must pick 1 stage from selection.")

    private String stage; // Not started, completed, in progress

    @NotBlank(message = "*Enter a description of your project")
    private String description;



    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))

    @JsonIgnore
    private List<Employee> employeeList;

    public Project() {  //default constructor
    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    //convince method:
    public void addEmployee(Employee emp) {
        if (employeeList==null){
            employeeList= new ArrayList<>();
        }
        employeeList.add(emp);
    }
}
