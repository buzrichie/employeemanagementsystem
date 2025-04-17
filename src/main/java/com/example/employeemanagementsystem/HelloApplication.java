package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.controller.EmployeeService;
import com.example.employeemanagementsystem.model.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        EmployeeService<String> employee= new EmployeeService<>();
        employee.addEmployee("2","Paul", Employee.DepartmentType.IT,300.00);
        employee.addEmployee("3","Rich", Employee.DepartmentType.HR,500.00);

        //employee.getAllEmployees();
        //employee.removeEmployee("2");
        employee.updateEmployeeDetails("2", new Employee<>("2","Prince",Employee.DepartmentType.Finance, 200.9));
        //System.out.println(employee.getEmployeeById("3"));

//        System.out.println(employee.getAllEmployees());
//        ArrayList<Employee<String>> employees = new ArrayList<>();

        employee.displayEmployeesWithForEach();
//        employee.displayEmployeeReportWithStreams();
//        employees.add(employee1);
//        employees.add(employee2);




//        for (Employee<String> e: employees){
//            System.out.println(e.getName());
//        }


        launch();
    }

    public static void text(){

    }
}