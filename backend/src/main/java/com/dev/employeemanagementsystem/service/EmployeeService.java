package com.dev.employeemanagementsystem.service;

import com.dev.employeemanagementsystem.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto dto);
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long id, EmployeeDto dto);
    void deleteEmployee(Long id);
}
