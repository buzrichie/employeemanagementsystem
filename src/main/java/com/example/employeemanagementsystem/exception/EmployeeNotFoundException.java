package com.example.employeemanagementsystem.exception;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(){}
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
