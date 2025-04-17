package com.example.employeemanagementsystem.model;

import java.util.*;

public class Employee<T> implements Comparable<Employee<T>>,EmployeeInterface<T> {

    private T id;
    private String name;
    private DepartmentType department;
    private double salary;
    private double performanceRating;
    private int yearsOfExperience;
    private boolean active;

    public Employee(T employeeId, String name, DepartmentType department, double salary){
        this.id = employeeId;
        this.name = name;
        this.department= department;
        this.salary = salary;
    }

    @Override
    public String toString(){
        return "Employee{"+"id="+this.id+", name='"+this.name+ '\''+ ", role='"+this.department+'\''+'}';
    }


    @Override
    public T getId() {
        return this.id;
    }

    @Override
    public void setId(T id) {
        this.id =  id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public DepartmentType getDepartment() {
        return this.department;
    }

    @Override
    public void setDepartment(String department) {
        this.department = DepartmentType.valueOf(department);
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(double salary) {
            this.salary = salary;
    }

    @Override
    public double getPerformanceRating() {
        return this.performanceRating;
    }

    @Override
    public void setPerformanceRating(byte scale) {
        this.performanceRating= scale;
    }

    @Override
    public int setYearsOfExperience() {
        return this.yearsOfExperience;
    }

    @Override
    public void setYearsOfExperience(int years) {
        this.yearsOfExperience = years;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean status) {
        this.active= status;
    }

    public enum DepartmentType {
        HR, IT, Finance
    }

    // Sort employees by years of experience, most experienced first
    @Override
    public int compareTo(Employee<T> other) {
        return Integer.compare(this.setYearsOfExperience(), other.setYearsOfExperience());
    }

    // Sorts employees by salary (highest first)
    Comparator<Employee<T>> EmployeeSalaryComparator = Comparator.comparing((x)->
         Double.compare(this.getSalary(), x.getSalary())
    );

    // Sorts employees by performance rating -best first-
    Comparator<Employee<T>> EmployeePerformanceComparator = Comparator.comparing((x)->
         Double.compare(this.getPerformanceRating(), x.getPerformanceRating())
    );


}
