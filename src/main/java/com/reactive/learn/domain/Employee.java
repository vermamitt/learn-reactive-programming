package com.reactive.learn.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String role;


    public static final class EmployeeBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String gender;
        private int age;
        private String role;

        public EmployeeBuilder() {
        }

        public static EmployeeBuilder anEmployee() {
            return new EmployeeBuilder();
        }

        public EmployeeBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public EmployeeBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder withRole(String role) {
            this.role = role;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.age = this.age;
            employee.firstName = this.firstName;
            employee.role = this.role;
            employee.lastName = this.lastName;
            employee.id = this.id;
            employee.gender = this.gender;
            return employee;
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                '}';
    }
}
