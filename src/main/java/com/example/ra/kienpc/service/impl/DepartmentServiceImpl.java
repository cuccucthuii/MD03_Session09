package com.example.ra.kienpc.service.impl;

import com.example.ra.kienpc.mapper.IDepartmentMapper;
import com.example.ra.kienpc.model.dto.DepartmentRequestDto;
import com.example.ra.kienpc.model.entity.Department;
import com.example.ra.kienpc.repository.IDepartmentRepository;
import com.example.ra.kienpc.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentRepository iDepartmentRepository;
    private final IDepartmentMapper iDepartmentMapper;

    @Override
    public void createDepartment(DepartmentRequestDto request) {
        Department department = iDepartmentMapper.toEntity(request);
        iDepartmentRepository.save(department);
    }
}
