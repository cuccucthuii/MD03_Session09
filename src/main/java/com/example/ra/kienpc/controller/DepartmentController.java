package com.example.ra.kienpc.controller;

import com.example.ra.kienpc.model.dto.DepartmentRequestDto;
import com.example.ra.kienpc.model.entity.Department;
import com.example.ra.kienpc.service.IDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentRequestDto requestDto){
        departmentService.createDepartment(requestDto);
        return new ResponseEntity<>("Success",HttpStatus.CREATED); //201
    }

}
