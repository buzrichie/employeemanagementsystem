package com.example.employeemanagementsystem.util;

import com.example.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.example.employeemanagementsystem.exception.InvalidDepartmentException;
import com.example.employeemanagementsystem.exception.InvalidSalaryException;
import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.exception.InvalidYearOfExperienceException;

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
    public static Employee.DepartmentType validateDepartment(String input) throws InvalidDepartmentException {
        for (Employee.DepartmentType type : Employee.DepartmentType.values()) {
            if (type.name().equalsIgnoreCase(input)) {
                return type;
            }
        }
        throw new InvalidDepartmentException("Invalid department: " + input);
    }
    public static void validateYearOfExperience(int year) throws InvalidYearOfExperienceException {
        if (year < 0) {
            throw new InvalidYearOfExperienceException("Year of Experience cannot be negative.");
        }
    }

}
