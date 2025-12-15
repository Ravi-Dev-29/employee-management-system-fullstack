package com.dev.employeemanagementsystem.service.impl;

import com.dev.employeemanagementsystem.dto.EmployeeDto;
import com.dev.employeemanagementsystem.entity.Employee;
import com.dev.employeemanagementsystem.exception.ResourceConflictException;
import com.dev.employeemanagementsystem.exception.ResourceNotFoundException;
import com.dev.employeemanagementsystem.mapper.EmployeeMapper;
import com.dev.employeemanagementsystem.repository.EmployeeRepository;
import com.dev.employeemanagementsystem.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new ResourceConflictException("Email already exists: " + dto.getEmail());
        }
        Employee saved = repository.save(EmployeeMapper.toEntity(dto));
        return EmployeeMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return EmployeeMapper.toDto(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        return EmployeeMapper.toDtoList(repository.findAll());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        // If email is changing, ensure uniqueness
        if (dto.getEmail() != null && !dto.getEmail().equals(existing.getEmail()) &&
                repository.existsByEmail(dto.getEmail())) {
            throw new ResourceConflictException("Email already exists: " + dto.getEmail());
        }

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());

        Employee updated = repository.save(existing);
        return EmployeeMapper.toDto(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        repository.delete(existing);
    }
}
