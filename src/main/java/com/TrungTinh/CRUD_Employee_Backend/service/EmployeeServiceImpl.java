package com.TrungTinh.CRUD_Employee_Backend.service;

import com.TrungTinh.CRUD_Employee_Backend.entity.Employee;
import com.TrungTinh.CRUD_Employee_Backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllUser() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getUserById(Long id) {
        return employeeRepository.findById(id).orElseThrow(null);
    }

    private static final String UPLOAD_DIR = "uploads/";
    @Override
    public Employee createUser(String name, String email, String department, String phone, MultipartFile img) throws IOException {

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Xử lý tệp hình ảnh
        String fileName = img.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, img.getBytes());

        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmail(email);
        emp.setDepartment(department);
        emp.setPhone(phone);
        emp.setImg(fileName);
        return employeeRepository.save(emp);
    }

    public Employee updateUser(Long id, String name, String email, String phone, String department, MultipartFile img) {
        Employee emp = employeeRepository.findById(id).orElseThrow(null);

        emp.setName(name);
        emp.setEmail(email);
        emp.setPhone(phone);
        emp.setDepartment(department);

        if (img != null && !img.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
            try {
                emp.setName(name);
                emp.setEmail(email);
                emp.setPhone(phone);
                emp.setDepartment(department);
                emp.setImg(fileName);
                Path path = Paths.get("uploads/" + fileName);
                Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file " + fileName, e);
            }
        }

        return employeeRepository.save(emp);
    }

    @Override
    public Employee deleteUser(Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(null);
        employeeRepository.deleteById(id);
        return emp;
        }
}
