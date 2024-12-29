package com.example.project;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PayrollInterface {

    private List<Payroll> payrolls = new ArrayList<>();
    private TableView<Payroll> payrollTable;

    // Move input fields and button as instance variables
    private TextField employeeIdField;
    private TextField employeeNameField;
    private TextField hourlyRateField;
    private TextField hoursWorkedField;
    private TextField overtimeHoursField;
    private TextField bonusesField;
    private TextField deductionsField;
    private Button addPayrollButton;

    public PayrollInterface() {
        // Initialize input fields
        employeeIdField = new TextField();
        employeeIdField.setPromptText("Employee ID");

        employeeNameField = new TextField();
        employeeNameField.setPromptText("Employee Name");

        hourlyRateField = new TextField();
        hourlyRateField.setPromptText("Hourly Rate");

        hoursWorkedField = new TextField();
        hoursWorkedField.setPromptText("Hours Worked");

        overtimeHoursField = new TextField();
        overtimeHoursField.setPromptText("Overtime Hours");

        bonusesField = new TextField();
        bonusesField.setPromptText("Bonuses");

        deductionsField = new TextField();
        deductionsField.setPromptText("Deductions");

        // Add Payroll Button
        addPayrollButton = new Button("Add Payroll");
        addPayrollButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(employeeIdField.getText());
                String name = employeeNameField.getText();
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                int hoursWorked = Integer.parseInt(hoursWorkedField.getText());
                int overtimeHours = Integer.parseInt(overtimeHoursField.getText());
                double bonuses = Double.parseDouble(bonusesField.getText());
                double deductions = Double.parseDouble(deductionsField.getText());

                Payroll payroll = new Payroll(id, name, hourlyRate, hoursWorked, overtimeHours, bonuses, deductions);
                payrolls.add(payroll);
                payrollTable.getItems().add(payroll);

                clearFields(employeeIdField, employeeNameField, hourlyRateField, hoursWorkedField, overtimeHoursField, bonusesField, deductionsField);
            } catch (NumberFormatException ex) {
                showAlert("Input Error", "Please enter valid numbers for hourly rate, hours worked, overtime, bonuses, and deductions.");
            }
        });

        // Payroll Table
        payrollTable = new TableView<>();
        payrollTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Payroll, Integer> idColumn = new TableColumn<>("Employee ID");
        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmployeeId()));

        TableColumn<Payroll, String> nameColumn = new TableColumn<>("Employee Name");
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmployeeName()));

        TableColumn<Payroll, Double> netSalaryColumn = new TableColumn<>("Net Salary");
        netSalaryColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().calculateNetSalary()));

        payrollTable.getColumns().addAll(idColumn, nameColumn, netSalaryColumn);
    }

    public VBox getPayrollLayout() {
        // Use the instance variables directly
        VBox inputLayout = new VBox(10, employeeIdField, employeeNameField, hourlyRateField, hoursWorkedField, overtimeHoursField, bonusesField, deductionsField, addPayrollButton);
        inputLayout.setPadding(new Insets(10));

        VBox tableLayout = new VBox(10, payrollTable);
        tableLayout.setPadding(new Insets(10));

        VBox mainLayout = new VBox(20, inputLayout, tableLayout);
        mainLayout.setPadding(new Insets(10));

        return mainLayout;  // Return the actual layout
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
