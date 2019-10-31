package com.bdd.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bdd.demo.repository.EmployeeRepository;
import com.bdd.demo.model.Employee;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController 
{
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @GetMapping(path="/", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployees()
    {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id)
    {
        Employee employee = employeeRepository.findById(id);
        if(employee !=null)
            return ResponseEntity.ok(employee);
        else
            return ResponseEntity.notFound().build();
    }

    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Employee>> addEmployee(@RequestBody Employee employee)
                 throws Exception 
    {
        employeeRepository.save(employee);

        //Send location in response
        return ResponseEntity.ok(employeeRepository.findAll());
    }
}
