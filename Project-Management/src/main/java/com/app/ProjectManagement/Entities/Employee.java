package com.app.ProjectManagement.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "employee_seq") //identity will rely on a auto incremented database, batch is gone its find for now.
   // @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq")
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;


    //remove is not included because it would remove the children  as well.
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
        fetch = FetchType.LAZY)//if coming happens to the parent all the children are affected (delete project and employees will be deleted as well
    //Fetch eager is  slow and lazy is fast.eager gets the parent and child while lazy  only gets the parent.lazy loading is a deisgn pattern for performance improvements

    @JoinTable(name = "project_employee",joinColumns = @JoinColumn(name = "employee_id"),inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
