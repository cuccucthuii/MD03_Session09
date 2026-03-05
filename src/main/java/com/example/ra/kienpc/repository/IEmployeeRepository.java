package com.example.ra.kienpc.repository;

import com.example.ra.kienpc.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Employee getEmployeeByEmail(String email);

    Employee getEmployeeById(Long id);
}
