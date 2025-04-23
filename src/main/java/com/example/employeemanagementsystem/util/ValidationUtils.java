package com.example.employeemanagementsystem.util;

import com.example.employeemanagementsystem.exception.InvalidSalaryException;

public class ValidationUtils {
    public static void validateSalary(double salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative.");
        }
    }
}
