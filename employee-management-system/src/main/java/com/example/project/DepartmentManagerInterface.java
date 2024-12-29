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

public class DepartmentManagerInterface {

    private TableView<Department> departmentTable;
    private DepartmentManager departmentManager;

    public DepartmentManagerInterface() {
        // Initialize the DepartmentManager (handles data manipulation)
        departmentManager = new DepartmentManager();
        departmentManager.loadDepartments(); // Load existing departments if any
    }

    public VBox getDepartmentManagerLayout() {
        // Input fields
        TextField departmentNameField = new TextField();
        departmentNameField.setPromptText("Department Name");

        TextField maxEmployeesField = new TextField();
        maxEmployeesField.setPromptText("Max Employees");

        // Buttons
        Button addDepartmentButton = new Button("Add Department");
        addDepartmentButton.setOnAction(e -> {
            try {
                String name = departmentNameField.getText();
                int maxEmployees = Integer.parseInt(maxEmployeesField.getText());

                if (departmentManager.addDepartment(name, maxEmployees)) {
                    clearFields(departmentNameField, maxEmployeesField);
                    updateDepartmentTable();  // Update the table to reflect changes
                }
            } catch (NumberFormatException ex) {
                showAlert("Input Error", "Please enter a valid number for max employees.");
            }
        });

        Button removeDepartmentButton = new Button("Remove Department");
        removeDepartmentButton.setOnAction(e -> {
            Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
            if (selectedDepartment != null) {
                if (departmentManager.removeDepartment(selectedDepartment.getId())) {
                    updateDepartmentTable();  // Update the table after removal
                }
            } else {
                showAlert("Selection Error", "Please select a department to remove.");
            }
        });

        // Department Table
        departmentTable = new TableView<>();
        departmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Department, Integer> idColumn = new TableColumn<>("Department ID");
        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));

        TableColumn<Department, String> nameColumn = new TableColumn<>("Department Name");
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getName()));

        TableColumn<Department, Integer> maxEmployeesColumn = new TableColumn<>("Max Employees");
        maxEmployeesColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmployees().size()));

        departmentTable.getColumns().addAll(idColumn, nameColumn, maxEmployeesColumn);

        // Layout
        VBox inputLayout = new VBox(10, departmentNameField, maxEmployeesField, addDepartmentButton, removeDepartmentButton);
        inputLayout.setPadding(new Insets(10));

        VBox tableLayout = new VBox(10, departmentTable);
        tableLayout.setPadding(new Insets(10));

        HBox mainLayout = new HBox(20, inputLayout, tableLayout);
        mainLayout.setPadding(new Insets(10));

        // Initial table update to display the current departments
        updateDepartmentTable();

        return new VBox(mainLayout);  // Return the main layout
    }

    private void updateDepartmentTable() {
        // Clear the table and reload department data from DepartmentManager
        departmentTable.getItems().clear();
        departmentTable.getItems().addAll(departmentManager.getAllDepartments()); // Use the correct method to get the department list
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
