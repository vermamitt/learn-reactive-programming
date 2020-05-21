package com.reactive.learn.service;

import com.reactive.learn.client.EmployeeClient;
import com.reactive.learn.domain.Employee;
import com.reactive.learn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeClient employeeClient;

    public void saveEmployee() throws Exception {
        employeeRepository.saveAll(employeeClient.getBulKEmployees());
    }

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }
}
