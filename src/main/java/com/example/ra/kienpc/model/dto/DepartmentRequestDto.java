package com.example.ra.kienpc.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDto {
    @NotBlank
    @Size(min = 5, max = 50, message = "The length of the name rangs from 5 ro 50!")
    String name;
    @Size(max = 100, message = "The length of the description rangs max 100!")
    String description;
}
