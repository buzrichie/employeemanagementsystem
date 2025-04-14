package com.example.employeemanagementsystem;

public interface EmployeeInterface<T> {

    T getEmployeeId();

    void setEmployeeId(T id);

    String getName();

    void setName(String name);

    String getDepartment();

    void setDepartment(String department);

    double getSalary();

    void setSalary(double salary);

    double getPerformanceRating();

    void setPerformanceRating(byte scale);

    int getYearsOfExperience();

    boolean isActive();

    void setActive(boolean status);
}

