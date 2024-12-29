package com.example.project;
import java.io.Serializable;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
public class Employee implements Serializable {
    private static int idGenerator = 100;//starting the id's from 100
    private int id;
    private String name;
    private double salary;
    private String position;

    public Employee( String name, double salary, String position) {//construction
        this.id = idGenerator++;//adding 1 to the id with each creation to give them a unique id
        this.name = name;
        this.salary = salary;
        this.position = position;

    }
    //getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;

    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;

    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }
}
