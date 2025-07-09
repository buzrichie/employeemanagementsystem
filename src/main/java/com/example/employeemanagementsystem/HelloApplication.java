package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.controller.EmployeeService;
import com.example.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.example.employeemanagementsystem.model.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {

        EmployeeService<String> employeeService= new EmployeeService<>();
        try {
            // ADD employees
            employeeService.addEmployee("1", "Paul", Employee.DepartmentType.IT, 300.00, 2);
            employeeService.addEmployee("2", "Rich", Employee.DepartmentType.HR, 500.00, 5);
            employeeService.addEmployee("3", "Fred", Employee.DepartmentType.IT, 300.00, 3);
            employeeService.addEmployee("4", "Abena", Employee.DepartmentType.IT, 300.00, 4);
            employeeService.addEmployee("5", "Kwame", Employee.DepartmentType.Finance, 700.00, 6);

            System.out.println("Added employees:");
            printAll(employeeService);
            
            // UPDATE one employee
            Employee<String> updated = new Employee<>("2", "Richmond", Employee.DepartmentType.IT, 550.00, 6);
            employeeService.updateEmployeeDetails("2", updated);

            System.out.println("\nUpdated employee with ID '2':");
            System.out.println(employeeService.getEmployeeById("2"));

            // DELETE one employee
            employeeService.removeEmployee("3");

            System.out.println("\nAfter deleting employee with ID '3':");
            printAll(employeeService);

            // SORT by salary
            System.out.println("\nSorted by salary:");
            List<Employee<String>> sortedBySalary = employeeService.filterBySalaryRange(300,700);
            for (Employee<String> e : sortedBySalary) {
                System.out.println(e.getName() + " - $" + e.getSalary());
            }

            // SEARCH by department
            System.out.println("\nEmployees in IT department:");
            List<Employee<String>> itEmployees = employeeService.getEmployeesByDepartment(Employee.DepartmentType.IT);
            for (Employee<String> e : itEmployees) {
                System.out.println(e);
            }

            // HANDLE: Search non-existent employee
            try {
                System.out.println("\nSearching for employee with ID '99':");
                System.out.println(employeeService.getEmployeeById("99"));
            } catch (EmployeeNotFoundException e) {
                System.out.println("Search: " + e.getMessage());
            }

            // HANDLE: Sorting an empty list
            EmployeeService<String> emptyService = new EmployeeService<>();
            List<Employee<String>> emptySorted = emptyService.filterBySalaryRange(0,200);
            System.out.println("\nSorting empty list did not crash. List size: " + emptySorted.size());

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        launch();
    }
    private static void printAll(EmployeeService<String> service) {
        for (Employee<String> e : service.getAllEmployees()) {
            System.out.println(e);
        }
    }
}
