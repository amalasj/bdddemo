package com.bdd.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bdd.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

   Employee findByEmail(String email);

   Employee findById(Integer id);
}
