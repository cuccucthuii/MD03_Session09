package com.example.ra.kienpc.mapper;


import com.example.ra.kienpc.model.dto.DepartmentRequestDto;
import com.example.ra.kienpc.model.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDepartmentMapper {
    // To Entity
    Department toEntity(DepartmentRequestDto requestDto);
}
