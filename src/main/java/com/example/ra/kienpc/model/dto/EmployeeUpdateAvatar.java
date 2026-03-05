package com.example.ra.kienpc.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EmployeeUpdateAvatar {
    private MultipartFile avatarUrl;
}
