package com.reactive.learn.api;

import com.reactive.learn.domain.Employee;
import com.reactive.learn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/upload")
    public List<Employee> uploadEmployees() throws Exception {

        employeeService.saveEmployee();

        return employeeService.getEmployee();
    }
}
