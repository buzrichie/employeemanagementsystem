package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.example.employeemanagementsystem.exception.InvalidDepartmentException;
import com.example.employeemanagementsystem.exception.InvalidSalaryException;
import com.example.employeemanagementsystem.exception.InvalidYearOfExperienceException;
import com.example.employeemanagementsystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeServiceTest {

    private EmployeeService<String> employeeService;

    @BeforeEach
    public void setup() {
        employeeService = new EmployeeService<>();
    }

    @Test
    void addEmployee() throws InvalidDepartmentException, InvalidSalaryException, InvalidYearOfExperienceException, EmployeeNotFoundException {
        employeeService.addEmployee("1",  "Richmond", Employee.DepartmentType.IT, 1000.0, 5);
        assertNotNull(employeeService.getEmployeeById("1"));
        assertEquals("Richmond", employeeService.getEmployeeById("1").getName());
    }

    @Test
    void removeEmployee() throws InvalidDepartmentException, InvalidSalaryException, InvalidYearOfExperienceException, EmployeeNotFoundException {
        employeeService.addEmployee("2","Ama",Employee.DepartmentType.Finance,23,4);
        assertNotNull(employeeService.getEmployeeById("2"));
        employeeService.removeEmployee("2");
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.getEmployeeById("2"));
    }

    @Test
    void getEmployeesByDepartment() throws InvalidDepartmentException, InvalidSalaryException, InvalidYearOfExperienceException {
        employeeService.addEmployee("1","Ama",Employee.DepartmentType.Finance,48,18);
        employeeService.addEmployee("2","Kwame",Employee.DepartmentType.IT,23,8);
        employeeService.addEmployee("3","Kwodwo",Employee.DepartmentType.HR,52,26);
        employeeService.addEmployee("4","Esi",Employee.DepartmentType.IT,18,1);

        List<Employee<String>> itEmployees = employeeService.getEmployeesByDepartment(Employee.DepartmentType.IT);

        // Assert that only the 2 IT employees are returned
        assertEquals(2, itEmployees.size());
        assertTrue(itEmployees.stream().anyMatch(emp -> emp.getName().equals("Kwame")));
        assertTrue(itEmployees.stream().anyMatch(emp -> emp.getName().equals("Esi")));
    }

    @Test
    void addEmployeeWithInvalidSalary() {
        assertThrows(InvalidSalaryException.class, () -> {
            employeeService.addEmployee("1", "Richmond", Employee.DepartmentType.IT, -1000.0, 5);
        });
    }

    @Test
    void addEmployeeWithInvalidDepartment() {
        assertThrows(InvalidDepartmentException.class, () -> {
            employeeService.addEmployee("1", "Richmond", null, 1000.0, 5);
        });
    }

    @Test
    void addEmployeeWithInvalidExperience() {
        assertThrows(InvalidYearOfExperienceException.class, () -> {
            employeeService.addEmployee("1", "Richmond", Employee.DepartmentType.IT, 1000.0, -1);
        });
    }
    @Test
    void searchForNonExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById("999");
        });
    }
    @Test
    void sortEmptyEmployeeList_shouldNotCrash() {
        List<Employee<String>> sortedList = employeeService.sortByExperience(); // or by salary, etc.
        assertTrue(sortedList.isEmpty());
    }

}