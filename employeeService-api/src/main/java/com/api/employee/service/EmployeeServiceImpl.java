package com.api.employee.service;

import com.api.employee.model.Employee;
import com.api.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee deleteEmployee(Long id){
        Employee delEmployeeById = employeeRepository.getOne(id);
        if (isNull(delEmployeeById))
            return null;
        employeeRepository.delete(delEmployeeById);
        return delEmployeeById;
    }
}
