package com.example.employeemanagementsystem.exception;

public class InvalidSalaryException extends Exception{

    public InvalidSalaryException(){}
    public InvalidSalaryException(String message){
        super(message);
    }

}
