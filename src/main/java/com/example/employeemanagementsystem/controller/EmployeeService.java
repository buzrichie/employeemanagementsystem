package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService<T> {

    private Map<T , Employee<T>> employees = new HashMap<>();

    public EmployeeService(){
        this.employees = new HashMap<T, Employee<T>>();
    }

    // CRUD operations
    public void addEmployee(T employeeId, String name, Employee.DepartmentType department, double salary){
        employees.put(employeeId, new Employee<>(employeeId, name, department, salary));
        System.out.println(employees.values());
    }



    public void removeEmployee(T employeeId){
        employees.remove(employeeId);
    }

    public void updateEmployeeDetails(T employeeId, Employee<T> updatedEmployee) {
        if (employees.containsKey(employeeId)) {
            employees.put(employeeId, updatedEmployee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public Employee<T> getEmployeeById(T employeeId) {
        return employees.get(employeeId);
    }

    public Collection<Employee<T>> getAllEmployees(){
        return new ArrayList<>(employees.values());
    }

    // CRUD operations End


    public List<Employee<T>> getEmployeesByDepartment(Employee.DepartmentType departmentType){
            return employees.values().stream()
                    .filter(x -> x.getDepartment().equals(departmentType))
                    .collect(Collectors.toList());
    }

    public List<Employee<T>> searchNameByTerm(String term){
        return employees.values().stream().filter(e->e.getName().toLowerCase().contains(term.toLowerCase())).collect(Collectors.toList());
    }

    public List<Employee<T>> filterByPerformance(double minRating) {
        return employees.values().stream()
                .filter(e -> e.getPerformanceRating() >= minRating)
                .collect(Collectors.toList());
    }
    public List<Employee<T>> filterBySalaryRange(double minSalary, double maxSalary) {
        return employees.values().stream()
                .filter(e -> e.getSalary() >= minSalary && e.getSalary() <= maxSalary)
                .collect(Collectors.toList());
    }

    public List<Employee<T>> salaryIncrement() {
        return employees.values().stream()
                .filter(e -> e.getPerformanceRating() >= 4.5)
                .peek(e -> e.setSalary(e.getSalary() * 1.10))
                .collect(Collectors.toList());
    }

//    Retrieve the top 5 highest-paid employees
    public List<Employee<T>> top5Paid() {
        return employees.values().stream()
                .sorted((x, y) -> (int) (x.getSalary() - y.getSalary()))
                .limit(5)
                .collect(Collectors.toList());
    }

    //    average salary of employees in a specific department
    public double averageSalary(Employee.DepartmentType targetDept) {
        return employees.values().stream()
                .filter(e -> e.getDepartment().equals(targetDept))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public void displayEmployeesWithForEach() {
        System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", "ID", "Name", "Department", "Salary", "Rating");
        System.out.println("-----------------------------------------------------------------------");
        for (Employee<T> employee : getAllEmployees()) {
            System.out.printf("%-10s %-20s %-15s $%-9.2f %-10.1f\n",
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getSalary(),
                    employee.getPerformanceRating());
        }
    }

    public void displayEmployeeReportWithStreams() {
        System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", "ID", "Name", "Department", "Salary", "Rating");
        System.out.println("-----------------------------------------------------------------------");

        employees.values().stream()
                .sorted(Comparator.comparing(Employee::getName)) // optional: sort by name
                .forEach(e -> System.out.printf("%-10s %-20s %-15s $%-9.2f %-10.1f\n",
                        e.getId(),
                        e.getName(),
                        e.getDepartment(),
                        e.getSalary(),
                        e.getPerformanceRating()));
    }
}
