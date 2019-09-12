package com.bdd.demo.repository;

import org.springframework.stereotype.Repository;

import com.bdd.demo.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private static List<Employee> list = new ArrayList<>();

    static {
        list.add(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
        list.add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
    }

    public List<Employee> getAllEmployees() {
        return list;
    }

    public Employee getEmployee(Integer id) {
        for (Employee employee : list) {
            if (employee.getId() == id)
                return employee;
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        list.add(employee);
    }
}
