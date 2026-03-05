package com.example.ra.kienpc.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateApplyDto {
    @NotBlank(message = "Tên không được để trống!")
    String name;
    @Email(message = "Email không đúng định dạng!")
    String email;
    MultipartFile cvFile;
}
