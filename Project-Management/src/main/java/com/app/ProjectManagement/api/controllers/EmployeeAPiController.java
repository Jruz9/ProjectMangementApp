package com.app.ProjectManagement.api.controllers;

import com.app.ProjectManagement.Entities.Employee;
import com.app.ProjectManagement.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/app-api/employees")
public class EmployeeAPiController {


    @Autowired
            //to save time we are using repos but they should be in services.
    EmployeeRepository empRepo;

    @GetMapping
    public Iterable<Employee> getEmployees(){
        return empRepo.findAll();
    }

    //gets e mployyee by id using rest
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
    return empRepo.findById(id).get();
    }


    //post api for creating employee
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee){
        return empRepo.save(employee);
    }


    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody Employee  employee){
        return empRepo.save(employee);
    }


    @PatchMapping(path = "/{id}",consumes ="application/json" )
    public Employee partialUpdate(@PathVariable("id") long id, @RequestBody Employee patchEmployee){
       Employee  emp=empRepo.findById(id).get();

       if (patchEmployee.getEmail() != null){
           emp.setEmail(patchEmployee.getEmail());
       }

        if (patchEmployee.getFirstName() != null){
            emp.setFirstName(patchEmployee.getFirstName());
        }
        if (patchEmployee.getLastName() != null){
            emp.setLastName(patchEmployee.getLastName());
        }
        return  emp;
    }

    @DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        try{
            empRepo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
        }
    }


    //add pagination to our api
    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployee(@RequestParam("page") int page,
                                                    @RequestParam("size")int size){
        Pageable pageAndSize= PageRequest.of(page,size);
        return empRepo.findAll(pageAndSize);
    }
}
