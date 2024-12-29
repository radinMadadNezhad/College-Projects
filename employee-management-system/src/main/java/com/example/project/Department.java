package com.example.project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
public class Department implements Serializable {

    private String name;
    private int id;
    private static int idGenerator  = 200;
    private ArrayList<Employee> employees;//aggregates employees
    private int maxEmployee;

    public Department(String name, int maxEmployee) {
        this.name = name;
        this.id = idGenerator++;
        this.employees = new ArrayList<Employee>();
        this.maxEmployee = maxEmployee;
    }
    //setters and getters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;

    }
    public int getId() {
        return id;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public boolean addEmployeeToDepartment( String name, double salary, String position) {//for adding an employee to the department
        for(Employee employee : employees) {//check to see if the employee already exists in the department
            if(employee.getName().equalsIgnoreCase(name) && employee.getSalary() == salary && employee.getPosition().equalsIgnoreCase(position)) {
                System.out.println("Employee already exists");
                return false;
            }
        }

        if(employees.size() < maxEmployee) {//check the number of employees in the department and if it was less than the max add it
            Employee employee = new Employee(name, salary, position);
            employees.add(employee);//add
            System.out.println("Employee " + name + " added to department " + id);
            return true;
        }
        System.out.println("Employee " + name + " cannot be added to department " + id);
       return false;//the department was full
    }
    public boolean removeEmployeeFromDepartment(int employeeId) {

        for (Employee employee : employees) {//look for the id
            if (employee.getId() == employeeId) {
                employees.remove(employee);//remove when found
                System.out.println("Removed employee " + employee.getName() + " from department");
                return true;
            }
        }

        System.out.println("Employee with id " + employeeId + " not found");//no such id
        return false;

    }
    public void showEmployees() {
        System.out.println("Employees in the department: ");
        if(employees.isEmpty()){//printing employees in the department
            System.out.println("No employees.");

        }
        for(Employee employee : employees){

            System.out.println(employee.toString());
        }
    }

    @Override
    public String toString() {//returning department info
        return "Department{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", employees=" + employees +
                '}';
    }
}
