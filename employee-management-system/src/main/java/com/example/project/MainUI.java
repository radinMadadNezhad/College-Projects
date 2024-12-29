package com.example.project;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainUI extends Application {

    private Stage primaryStage;
    private BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("HRM and Payroll System");

        showLoginScreen();
    }

    private void showLoginScreen() {
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(20));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Label loginMessage = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if ("admin".equals(username) && "admin".equals(password)) {
                showAdminDashboard();
            } else if ("employee".equals(username) && "user".equals(password)) {
                showEmployeeDashboard();
            } else {
                loginMessage.setText("Invalid username or password.");
            }
        });

        loginLayout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, loginMessage);

        Scene loginScene = new Scene(loginLayout, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showAdminDashboard() {
        mainLayout = new BorderPane();

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Departments Menu
        Menu departmentMenu = new Menu("Departments");
        MenuItem manageDepartments = new MenuItem("Manage Departments");
        manageDepartments.setOnAction(e -> mainLayout.setCenter(new DepartmentManagerInterface().getDepartmentManagerLayout()));
        departmentMenu.getItems().add(manageDepartments);

        // Employees Menu
        Menu employeeMenu = new Menu("Employees");
        MenuItem manageEmployees = new MenuItem("Manage Employees");
        manageEmployees.setOnAction(e -> mainLayout.setCenter(new EmployeeManagerInterface().getEmployeeManagerLayout()));
        employeeMenu.getItems().add(manageEmployees);

        // Payroll Menu
        Menu payrollMenu = new Menu("Payroll");
        MenuItem managePayroll = new MenuItem("Manage Payroll");
        managePayroll.setOnAction(e -> mainLayout.setCenter(new PayrollInterface().getPayrollLayout()));
        payrollMenu.getItems().add(managePayroll);

        menuBar.getMenus().addAll(departmentMenu, employeeMenu, payrollMenu);

        // Set default center to Employee Manager interface
        mainLayout.setTop(menuBar);
        mainLayout.setCenter(new EmployeeManagerInterface().getEmployeeManagerLayout());

        // Scene and stage
        Scene adminScene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(adminScene);
        primaryStage.show();
    }

    private void showEmployeeDashboard() {
        VBox employeeLayout = new VBox(10);
        employeeLayout.setPadding(new Insets(20));

        Label welcomeLabel = new Label("Welcome, Employee!");

        Button clockInButton = new Button("Clock In");
        Button clockOutButton = new Button("Clock Out");
        Label statusLabel = new Label();
        Label hoursWorkedLabel = new Label();

        clockOutButton.setDisable(true);

        // Clock-in and clock-out logic
        final long[] clockInTime = {0}; // To store clock-in time

        clockInButton.setOnAction(e -> {
            clockInTime[0] = System.currentTimeMillis();
            clockInButton.setDisable(true);
            clockOutButton.setDisable(false);
            statusLabel.setText("Clocked in at: " + new java.util.Date(clockInTime[0]).toString());
        });

        clockOutButton.setOnAction(e -> {
            if (clockInTime[0] > 0) {
                long clockOutTime = System.currentTimeMillis();
                long hoursWorked = (clockOutTime - clockInTime[0]) / (1000 * 60 * 60); // Convert milliseconds to hours
                clockInTime[0] = 0; // Reset clock-in time

                clockOutButton.setDisable(true);
                clockInButton.setDisable(false);

                statusLabel.setText("Clocked out at: " + new java.util.Date(clockOutTime).toString());
                hoursWorkedLabel.setText("Hours Worked: " + hoursWorked);

                // Save hours worked into the payroll
                saveHoursWorked(hoursWorked);
            }
        });

        employeeLayout.getChildren().addAll(welcomeLabel, clockInButton, clockOutButton, statusLabel, hoursWorkedLabel);

        Scene employeeScene = new Scene(employeeLayout, 400, 300);
        primaryStage.setScene(employeeScene);
        primaryStage.show();
    }

    // Save hours worked to payroll (or a file)
    private void saveHoursWorked(long hoursWorked) {
        try {
            // Append worked hours to the payroll file (use a file or database in a real project)
            java.nio.file.Path filePath = java.nio.file.Paths.get("payroll_hours.txt");
            String logEntry = "Employee worked " + hoursWorked + " hours on " + new java.util.Date() + "\n";

            java.nio.file.Files.write(filePath, logEntry.getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
