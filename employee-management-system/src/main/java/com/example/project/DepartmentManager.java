package com.example.project;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
import java.io.*;
import java.util.ArrayList;

public class DepartmentManager {

    private ArrayList<Department> departments; // Aggregate department
    private static final String fileName = "departments.txt"; // File to save to and retrieve from

    public DepartmentManager() {
        this.departments = new ArrayList<>();
    }

    public boolean addDepartment(String name, int maxEmployees) {
        Department department = new Department(name, maxEmployees);
        departments.add(department); // Add to the list
        System.out.println("Department added successfully");
        saveDepartments(); // Save to the file
        return true;
    }

    public boolean removeDepartment(int id) {
        for (Department department : departments) {
            if (department.getId() == id) { // Look for the id
                departments.remove(department); // Remove when found
                System.out.println("Department removed successfully");
                saveDepartments();
                return true;
            }
        }
        System.out.println("No such department found");
        return false;
    }

    public ArrayList<Department> getAllDepartments() {
        return departments; // Return the list of departments
    }

    public void saveDepartments() { // Save to the file
        try (FileOutputStream output = new FileOutputStream(fileName);
             ObjectOutputStream fileOut = new ObjectOutputStream(output)) {
            fileOut.writeObject(departments); // Write the object
        } catch (IOException e) {
            System.out.println("Error saving departments: " + e.getMessage());
        }
    }

    public void loadDepartments() { // Retrieve data from the file
        try (FileInputStream input = new FileInputStream(fileName);
             ObjectInputStream fileIn = new ObjectInputStream(input)) {
            departments = (ArrayList<Department>) fileIn.readObject(); // Read the object and cast it to the correct type
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading departments: " + e.getMessage());
        }
    }
}
