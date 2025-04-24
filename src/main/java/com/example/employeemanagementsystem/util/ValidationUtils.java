package com.example.employeemanagementsystem.util;

import com.example.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.example.employeemanagementsystem.exception.InvalidSalaryException;
import com.example.employeemanagementsystem.model.Employee;

import java.util.List;
import java.util.Map;

public class ValidationUtils {
    public static void validateSalary(double salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative.");
        }
    }
    public static <T> void validateEmployeeById(Map<T, Employee<T>> employeeMap, T id) throws EmployeeNotFoundException {
        if (!employeeMap.containsKey(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " was not found.");
        }
    }

}
