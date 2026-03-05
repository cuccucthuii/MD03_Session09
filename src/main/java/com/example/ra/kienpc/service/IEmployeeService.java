package com.example.ra.kienpc.service;

import com.example.ra.kienpc.model.dto.EmployeeCreateDTO;
import com.example.ra.kienpc.model.dto.EmployeeUpdateAvatar;
import com.example.ra.kienpc.model.entity.Employee;

import java.io.IOException;

public interface IEmployeeService {
    Employee createEmployee(EmployeeCreateDTO employee);
    Employee updateAvatarEmployee(EmployeeUpdateAvatar request, Long id) throws IOException;
}
