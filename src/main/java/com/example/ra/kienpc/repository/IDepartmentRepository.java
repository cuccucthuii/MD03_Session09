package com.example.ra.kienpc.repository;

import com.example.ra.kienpc.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    Department getDepartmentById(Long id);
}
