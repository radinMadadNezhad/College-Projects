package com.example.project;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private ArrayList<Employee> employees; // aggregation
    private static String FileName = "employees.txt"; // file name for storing and retrieving

    public EmployeeManager() {
        this.employees = new ArrayList<>();
    }

    public boolean addEmployee(String name, String position, double salary) {
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name) && employee.getPosition().equalsIgnoreCase(position) && employee.getSalary() == salary) {
                System.out.println("Employee already exists");
                return false;
            }
        }
        Employee employee = new Employee(name, salary, position); // create the employee object with the given parameters
        employees.add(employee); // add the employee to the list of employees
        System.out.println("Employee added successfully");
        saveEmployees(); // save the added employee to the file
        return true;
    }

    public boolean removeEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) { // find the given id
                employees.remove(employee); // delete the employee with that id
                System.out.println("Employee removed successfully");
                saveEmployees(); // save the changes again
                return true;
            }
        }

        System.out.println("Employee not found.");
        return false;
    }

    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee; // return the employee with the specific id
            }
        }
        System.out.println("Employee not found.");
        return null;
    }

    // New method to get the list of employees
    public ArrayList<Employee> getAllEmployees() {
        return employees; // return the list of employees
    }

    public void saveEmployees() { // saving employees to the file method
        try (FileOutputStream output = new FileOutputStream("Employees.txt");
             ObjectOutputStream fileOut = new ObjectOutputStream(output)) {
            fileOut.writeObject(employees); // write the employees to the file
        } catch (IOException e) {
            System.out.println("Error saving Employees: " + e.getMessage()); // print the error if there was a problem
        }
    }

    public void loadEmployees() { // retrieving the list of employees from the file
        try (FileInputStream input = new FileInputStream("Employees.txt");
             ObjectInputStream fileIn = new ObjectInputStream(input)) {
            employees = (ArrayList<Employee>) fileIn.readObject(); // read the objects and change them to the correct type
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading Employees: " + e.getMessage()); // print error in case of a problem
        }
    }
}
