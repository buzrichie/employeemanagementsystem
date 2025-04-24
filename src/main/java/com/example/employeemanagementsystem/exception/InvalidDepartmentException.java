package com.example.employeemanagementsystem.exception;

public class InvalidDepartmentException extends Exception{
    public InvalidDepartmentException(){}
    public InvalidDepartmentException(String message){
        super(message);
    }
}
