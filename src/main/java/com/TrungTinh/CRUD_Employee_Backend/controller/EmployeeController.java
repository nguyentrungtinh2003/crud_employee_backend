package com.TrungTinh.CRUD_Employee_Backend.controller;

import com.TrungTinh.CRUD_Employee_Backend.entity.Employee;
import com.TrungTinh.CRUD_Employee_Backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAllUser() {
        return employeeService.getAllUser();
    }

    @GetMapping("/{id}")
    public Employee getUserById(@PathVariable Long id) {
        return employeeService.getUserById(id);
    }

    @PostMapping("/add")
    public Employee createUser(@RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("department") String department,
                               @RequestParam("phone") String phone,
                               @RequestParam("img")MultipartFile img) throws IOException {
        return employeeService.createUser(name,email,department,phone,img);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateUser(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("department") String department,
            @RequestParam(value = "img", required = false) MultipartFile img) {

        Employee updatedEmployee = employeeService.updateUser(id, name, email, phone, department, img);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/delete/{id}")
    public Employee deleteuser(@PathVariable Long id) {
        return employeeService.deleteUser(id);
    }
}
