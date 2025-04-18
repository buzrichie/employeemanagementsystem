package com.example.employeemanagementsystem.model;

public interface EmployeeInterface<T> {

    T getId();

    void setId(T id);

    String getName();

    void setName(String name);

    Employee.DepartmentType getDepartment();

    void setDepartment(String department);

    double getSalary();

    void setSalary(double salary);

    double getPerformanceRating();

    void setPerformanceRating(byte scale);

    int getYearsOfExperience();

    void getYearsOfExperience(int years);

    boolean isActive();

    void setActive(boolean status);
}


