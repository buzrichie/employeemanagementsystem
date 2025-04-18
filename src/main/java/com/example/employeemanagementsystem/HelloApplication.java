package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.controller.EmployeeService;
import com.example.employeemanagementsystem.model.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        EmployeeService<String> employee= new EmployeeService<>();
        employee.addEmployee("1","Paul", Employee.DepartmentType.IT,300.00, 2);
        employee.addEmployee("2","Rich", Employee.DepartmentType.HR,500.00,5);
        employee.addEmployee("3","Fred", Employee.DepartmentType.IT,300.00,3);
        employee.addEmployee("4","Abena", Employee.DepartmentType.IT,300.00,4);


        Collections.sort( employee.getAllEmployees());
        for (Employee<String> e: employee.getAllEmployees()){
            System.out.println(e.getName());
        }


        launch();
    }
}