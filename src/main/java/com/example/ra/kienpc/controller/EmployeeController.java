package com.example.ra.kienpc.controller;

import com.example.ra.kienpc.model.ApiResponse;
import com.example.ra.kienpc.model.dto.EmployeeCreateDTO;
import com.example.ra.kienpc.model.dto.EmployeeUpdateAvatar;
import com.example.ra.kienpc.model.entity.Employee;
import com.example.ra.kienpc.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@Valid @RequestBody EmployeeCreateDTO request){
        Employee emp = employeeService.createEmployee(request);
        ApiResponse<Employee> apiResponse = new ApiResponse<>("201","Create success",emp);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/avatar")
    public ResponseEntity<ApiResponse<Employee>> updateAvatar(@Valid @ModelAttribute EmployeeUpdateAvatar request, @PathVariable("id") long id) throws IOException {
        Employee emp = employeeService.updateAvatarEmployee(request, id);
        ApiResponse<Employee> apiResponse = new ApiResponse<>("200","Update success",emp);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
