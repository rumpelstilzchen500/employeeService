package com.api.employee.controller;

import com.api.employee.model.Employee;
import com.api.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.api.employee.repository.EmployeeRepository;

import static java.util.Objects.isNull;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/api"})
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json")
    public List<Employee> getUsersList() {
        return employeeRepository.findAll(Sort.unsorted());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.deleteEmployee(id);
        if (isNull(employee))
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        if(isNull (employee))
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeRepository.save(employee));
    }
}
