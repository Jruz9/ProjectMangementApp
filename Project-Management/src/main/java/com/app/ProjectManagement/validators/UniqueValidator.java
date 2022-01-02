package com.app.ProjectManagement.validators;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        System.out.println("Entered validation Method...");
        Employee emp = empRepo.findByEmail(value);

        if(emp !=null) {
            return false;
        }
        else
            return true;

    }
}
