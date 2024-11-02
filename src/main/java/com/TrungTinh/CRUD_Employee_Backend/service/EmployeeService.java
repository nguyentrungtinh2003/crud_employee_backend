package com.TrungTinh.CRUD_Employee_Backend.service;

import com.TrungTinh.CRUD_Employee_Backend.entity.Employee;
import com.TrungTinh.CRUD_Employee_Backend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Configuration
public interface EmployeeService {

    List<Employee> getAllUser();
    Employee getUserById(Long id);
    Employee createUser(String name, String email, String department,
                        String phone, MultipartFile img) throws IOException;
    Employee updateUser(Long id, String name, String email, String phone,
                        String department, MultipartFile img);
    Employee deleteUser(Long id);

}
