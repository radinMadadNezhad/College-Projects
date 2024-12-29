package com.example.project;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EmployeeManagerInterface {

    private TableView<Employee> employeeTable;
    private EmployeeManager employeeManager;

    public EmployeeManagerInterface() {
        // Initialize the EmployeeManager (handles data manipulation)
        employeeManager = new EmployeeManager();
        employeeManager.loadEmployees(); // Load existing employees if any
    }

    public VBox getEmployeeManagerLayout() {
        // Input fields
        TextField employeeNameField = new TextField();
        employeeNameField.setPromptText("Employee Name");

        TextField employeePositionField = new TextField();
        employeePositionField.setPromptText("Position");

        TextField employeeSalaryField = new TextField();
        employeeSalaryField.setPromptText("Salary");

        // Buttons
        Button addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setOnAction(e -> {
            try {
                String name = employeeNameField.getText();
                String position = employeePositionField.getText();
                double salary = Double.parseDouble(employeeSalaryField.getText());

                if (employeeManager.addEmployee(name, position, salary)) {
                    // Clear input fields after adding employee
                    clearFields(employeeNameField, employeePositionField, employeeSalaryField);
                    updateEmployeeTable();  // Update the table to reflect changes
                }
            } catch (NumberFormatException ex) {
                showAlert("Input Error", "Please enter a valid number for salary.");
            }
        });

        Button removeEmployeeButton = new Button("Remove Employee");
        removeEmployeeButton.setOnAction(e -> {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                if (employeeManager.removeEmployee(selectedEmployee.getId())) {
                    updateEmployeeTable();  // Update the table after removal
                }
            } else {
                showAlert("Selection Error", "Please select an employee to remove.");
            }
        });

        // Employee Table
        employeeTable = new TableView<>();
        employeeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Employee, Integer> idColumn = new TableColumn<>("Employee ID");
        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Employee Name");
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getName()));

        TableColumn<Employee, String> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPosition()));

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSalary()));

        employeeTable.getColumns().addAll(idColumn, nameColumn, positionColumn, salaryColumn);

        // Layout
        VBox inputLayout = new VBox(10, employeeNameField, employeePositionField, employeeSalaryField, addEmployeeButton, removeEmployeeButton);
        inputLayout.setPadding(new Insets(10));

        VBox tableLayout = new VBox(10, employeeTable);
        tableLayout.setPadding(new Insets(10));

        HBox mainLayout = new HBox(20, inputLayout, tableLayout);
        mainLayout.setPadding(new Insets(10));

        // Initial table update to display the current employees
        updateEmployeeTable();

        return new VBox(mainLayout);  // Return the main layout
    }

    private void updateEmployeeTable() {
        // Clear the table and reload employee data from EmployeeManager
        employeeTable.getItems().clear();
        employeeTable.getItems().addAll(employeeManager.getAllEmployees()); // Use the correct method to get the employee list
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
