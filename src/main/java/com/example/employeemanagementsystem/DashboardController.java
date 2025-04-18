package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.controller.EmployeeService;
import com.example.employeemanagementsystem.model.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Collections;
import java.util.Comparator;


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
    private TableColumn<Employee<?>, String> yearsOfExperienceColumn;
    @FXML
    private TableColumn<Employee<?>, String> salaryColumn;
    @FXML
    private TableColumn<Employee<?>, String> ratingColumn;
    @FXML
    private TextField searchField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private ChoiceBox<Employee.DepartmentType> departmentChoiceBox;
    @FXML
    private TextField salaryField;
    @FXML
    private ChoiceBox<Double> ratingChoiceBox;
    @FXML
    private TextField yearsOfExperienceField;


    @FXML
    private void initialize() {

        departmentChoiceBox.getItems().setAll(Employee.DepartmentType.values());
        departmentChoiceBox.setValue(Employee.DepartmentType.IT);

        ratingChoiceBox.getItems().setAll(new Double[]{1.0, 2.0, 3.0,4.0,5.0});
        ratingChoiceBox.setValue(1.0);

        departmentChoiceBox.setOnAction(this::addEmployee);
        ratingChoiceBox.setOnAction(this::addEmployee);

        departmentChoiceBox.setOnAction(this::updateEmployeeDetails);
        ratingChoiceBox.setOnAction(this::updateEmployeeDetails);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        yearsOfExperienceColumn.setCellValueFactory(new PropertyValueFactory<>("yearsOfExperience"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("performanceRating"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Set table's items once
        employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());

        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Populate fields with selected employee
                idField.setText(newSelection.getId());
                nameField.setText(newSelection.getName());
                departmentChoiceBox.setValue(Employee.DepartmentType.valueOf(newSelection.getDepartment().toString()));
                ratingChoiceBox.setValue(newSelection.getPerformanceRating());
                salaryField.setText(String.valueOf(newSelection.getSalary()));
                yearsOfExperienceField.setText(String.valueOf(newSelection.getYearsOfExperience()));
            }
        });

    }

    // Add Employee Event Handler
    public void addEmployee(ActionEvent event){
        try{

            if (this.idField.getText().isEmpty()||
                    this.nameField.getText().isEmpty()||
                    this.salaryField.getText().isEmpty()||
                    this.yearsOfExperienceField.getText().isEmpty()
            ){
                System.out.println("Some Fields are empty");
                throw new RuntimeException("Fields can not be empty");
            }
            String id = this.idField.getText();
            String name = this.nameField.getText();
            Employee.DepartmentType selectedDepartment = departmentChoiceBox.getValue();
            double salary = Double.parseDouble(this.salaryField.getText());
            int yearsOfExperience = Integer.parseInt(this.yearsOfExperienceField.getText());

            employeeService.addEmployee(id,name,selectedDepartment,salary, yearsOfExperience);
            employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()));
            employeeService.displayEmployeeReportWithStreams();

            employeeTextFieldCleanUp();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Remove or Delete Employee Event Handler
    public void deleteEmployee(){
        try{
        Employee<String> selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeeService.removeEmployee(selectedEmployee.getId());
            employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());
        }else {
            System.out.println("No employee selected.");
        }

    } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Search or Filter By Name Event Handler
    public void searchByName(){
        employeeService.searchNameByTerm(this.searchField.getText());
        if (!employeeService.searchNameByTerm(this.searchField.getText()).isEmpty()){
            employeeTable.setItems(FXCollections.observableArrayList(employeeService.searchNameByTerm(this.searchField.getText())));
        }else {
            employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());
        }
    }

    // Reset Search or Filter By Name Event Handler
    public void clearSearchByName(){
        this.searchField.clear();
        searchByName();
    }

    public void updateEmployeeDetails(ActionEvent event) {
        Employee<String> selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            try {
                Employee<String> updatedEmployee = getEmployeeData();

                // Replace in your service (you must implement this logic)
                employeeService.updateEmployeeDetails(selectedEmployee.getId(), updatedEmployee);

                // Update table view
                employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());
                employeeTextFieldCleanUp();
            } catch (Exception e) {
                System.out.println("Error updating employee: " + e.getMessage());
            }
        } else {
            System.out.println("No employee selected.");
        }
    }

    // Extract All Employee field Data
    private Employee<String> getEmployeeData() {
        String id = idField.getText();
        String name = nameField.getText();
        Employee.DepartmentType selectedDepartment = departmentChoiceBox.getValue();
        double selectedRating = ratingChoiceBox.getValue();
        double salary = Double.parseDouble(this.salaryField.getText());
        int yearsOfExperience = Integer.parseInt(yearsOfExperienceField.getText());

        // Create new employee with updated values
        return new Employee<>(id, name, selectedDepartment, salary, yearsOfExperience, selectedRating);
    }


    public void sortByExperience() {
        if (!employeeService.getAllEmployees().isEmpty()) {
            employeeTable.setItems(FXCollections.observableArrayList(employeeService.getAllEmployees()).sorted());
        }
    }


    public void employeeTextFieldCleanUp(){
        idField.clear();
        nameField.clear();
        salaryField.clear();
        yearsOfExperienceField.clear();
    }


}
