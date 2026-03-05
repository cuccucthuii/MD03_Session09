package com.example.ra.kienpc.model.dto;

import com.example.ra.kienpc.model.entity.Department;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeCreateDTO {
    @NotBlank(message = "Tên không được để trống")
    String fullName;
    @Email(message = "Email không hợp lệ")
    String email;
    @Pattern(regexp = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b$", message = "Số điện thoại không hợp lệ!")
    String phone;
    @DecimalMin(value = "5000000.00", message = "Lương không được ít hơn 5 triệu")
    BigDecimal salary;
    @NotNull(message = "Phòng ban không được để trống")
    Long departmentId;
}
