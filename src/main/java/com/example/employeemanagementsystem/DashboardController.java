package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.controller.EmployeeService;
import com.example.employeemanagementsystem.model.Employee;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class DashboardController {
    private final EmployeeService<String> employeeService = new EmployeeService<>();

    @FXML
    private TableView<Employee<String>> employeeTable;

    @FXML
    private TableColumn<Employee<?>, String> idColumn;

    @FXML
    private TableColumn<Employee<?>, String> nameColumn;

    @FXML
    private TableColumn<Employee<?>, String> departmentColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        //salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Set table's items once
        employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());
    }
    @FXML
    private TextField searchField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField ratingField;





    // Add Employee Event Handler
    public void addEmployee(){
        try{
            if (this.idField.getText().isEmpty()||
                    this.nameField.getText().isEmpty()||
                    this.departmentField.getText().isEmpty()||
                    this.salaryField.getText().isEmpty()||
                    this.ratingField.getText().isEmpty()
            ){
                System.out.println("Some Fields are empty");
                throw new RuntimeException("Fields can not be empty");
            }
            String id = this.idField.getText();
            String name = this.nameField.getText();
            Employee.DepartmentType departmentType = Employee.DepartmentType.valueOf(this.departmentField.getText());
            double salary = Double.parseDouble(this.salaryField.getText());
            double ratingField = Double.parseDouble(this.ratingField.getText());


            employeeService.addEmployee(id,name,departmentType,salary);
            //employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());

            employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());
            employeeService.displayEmployeeReportWithStreams();



            idField.clear();
            nameField.clear();
            departmentField.clear();
            salaryField.clear();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
