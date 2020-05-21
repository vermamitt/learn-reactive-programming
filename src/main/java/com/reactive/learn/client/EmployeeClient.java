package com.reactive.learn.client;

import com.reactive.learn.domain.Employee;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeClient {

    public List<Employee> getBulKEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:employee.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                employees.add(new Employee.EmployeeBuilder()
                        .withFirstName(values[0])
                        .withLastName(values[1])
                        .withGender(values[2])
                        .withAge(Integer.parseInt(values[3]))
                        .withRole(values[4])
                        .build());
            }
        }
        System.out.println(employees);
        return employees;
    }
}
