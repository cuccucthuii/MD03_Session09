package com.example.ra.kienpc.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ra.kienpc.exception.DuplicateResourceException;
import com.example.ra.kienpc.exception.ResourceNotFoundException;
import com.example.ra.kienpc.model.dto.EmployeeCreateDTO;
import com.example.ra.kienpc.model.dto.EmployeeUpdateAvatar;
import com.example.ra.kienpc.model.entity.Department;
import com.example.ra.kienpc.model.entity.Employee;
import com.example.ra.kienpc.repository.IDepartmentRepository;
import com.example.ra.kienpc.repository.IEmployeeRepository;
import com.example.ra.kienpc.service.IDepartmentService;
import com.example.ra.kienpc.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Employee createEmployee(EmployeeCreateDTO employee) {

        Department departmentId = departmentRepository.findById(employee.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng ban với ID = " + employee.getDepartmentId()));

        Employee emailEmp = employeeRepository.getEmployeeByEmail(employee.getEmail());
        if (emailEmp != null) {
            throw new DuplicateResourceException("Email nhân viên đã tồn tại trong hệ thống!");
        }


        Employee emp = Employee.builder()
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .salary(employee.getSalary())
                .departmentId(departmentId)
                .build();
        return employeeRepository.save(emp);
    }

    @Override
    public Employee updateAvatarEmployee(EmployeeUpdateAvatar request, Long id) throws IOException {

        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Không tìm thấy Id để cập nhật!");
        }

        MultipartFile avatarUrl = request.getAvatarUrl();
        String filename = avatarUrl.getOriginalFilename();
        if (avatarUrl == null || avatarUrl.isEmpty()) {
            throw new RuntimeException("Avatar không được để trống");
        }
        //Validate kích thước file < 2MB.
        if (avatarUrl.getSize() > 2 * 1024 * 1024) {
            throw new RuntimeException("File phải nhỏ hơn 2MB");
        }
        //Validate đuôi file (chỉ chấp nhận .jpg, .png, .jpeg).
        // Nếu sai -> Ném ngoại lệ.
        if (!filename.endsWith(".jpg")
            && !filename.endsWith(".png")
            && !filename.endsWith(".jpeg")) {
            throw new RuntimeException("Chỉ nhận các hình ảnh có đuôi .jpg, .png, .jpeg");
        }
        //Lưu file vào thư mục uploads/ hoặc Cloudinary -> Lấy URL update vào DB.
        Map uploadFile = cloudinary.uploader().upload(
                avatarUrl.getBytes(),
                ObjectUtils.emptyMap()
        );
        //Lấy kết quả trả về là một Map, trích xuất lấy url (String).
        String url = (String) uploadFile.get("url");
        // Set -> Entity
        employee.setAvatarUrl(url);
        return employeeRepository.save(employee);
    }
}
