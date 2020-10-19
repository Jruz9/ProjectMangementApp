package com.app.ProjectManagement.dto;

public interface EmployeeProject {

    // need to have the property name with get so spring data knows
    public String getFirstName();
    public String getLastName();
    public String getProjectCount();

}
