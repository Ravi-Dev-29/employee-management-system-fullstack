package com.dev.employeemanagementsystem.mapper;

import com.dev.employeemanagementsystem.dto.EmployeeDto;
import com.dev.employeemanagementsystem.entity.Employee;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class EmployeeMapper {

    private EmployeeMapper() {}

    public static EmployeeDto toDto(Employee e) {
        if (e == null) return null;
        return new EmployeeDto(e.getId(), e.getFirstName(), e.getLastName(), e.getEmail());
    }

    public static Employee toEntity(EmployeeDto d) {
        if (d == null) return null;
        Employee e = new Employee();
        e.setId(d.getId());
        e.setFirstName(d.getFirstName());
        e.setLastName(d.getLastName());
        e.setEmail(d.getEmail());
        return e;
    }

    public static List<EmployeeDto> toDtoList(List<Employee> employees) {
        if (employees == null) return List.of();
        return employees.stream()
                .filter(Objects::nonNull)
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
