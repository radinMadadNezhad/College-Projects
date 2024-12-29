package com.example.project;
// Radin Madad Nezhad Aligorkeh, Student ID : 101474661
// Diana Mohammadi, Student ID : 101481507
// Arash Shalchian, Student ID : 101414035
import java.io.Serializable;

public class Payroll implements Serializable {
    private int employeeId;
    private String employeeName;
    private double hourlyRate; // Hourly rate of the employee
    private int hoursWorked; // Total hours worked in a pay period
    private int overtimeHours; // Total overtime hours worked
    private double bonuses; // Additional bonuses
    private double deductions; // Deductions (e.g., tax, insurance)

    private static final double OVERTIME_RATE = 1.5; // Overtime is 1.5 times hourly rate
    private static final double TAX_RATE = 0.13; // 13% tax

    // Constructor
    public Payroll(int employeeId, String employeeName, double hourlyRate, int hoursWorked, int overtimeHours, double bonuses, double deductions) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.overtimeHours = overtimeHours;
        this.bonuses = bonuses;
        this.deductions = deductions;
    }

    // Calculate regular salary
    public double calculateRegularSalary() {
        return hourlyRate * hoursWorked;
    }

    // Calculate overtime pay
    public double calculateOvertimePay() {
        return overtimeHours * hourlyRate * OVERTIME_RATE;
    }

    // Calculate gross salary (regular + overtime + bonuses)
    public double calculateGrossSalary() {
        return calculateRegularSalary() + calculateOvertimePay() + bonuses;
    }

    // Calculate taxes (based on gross salary)
    public double calculateTaxes() {
        return calculateGrossSalary() * TAX_RATE;
    }

    // Calculate net salary (gross salary - taxes - deductions)
    public double calculateNetSalary() {
        return calculateGrossSalary() - calculateTaxes() - deductions;
    }

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    @Override
    public String toString() {
        return String.format("Employee: %s (ID: %d)\nRegular Salary: $%.2f\nOvertime Pay: $%.2f\nBonuses: $%.2f\nGross Salary: $%.2f\nTaxes: $%.2f\nDeductions: $%.2f\nNet Salary: $%.2f",
                employeeName, employeeId, calculateRegularSalary(), calculateOvertimePay(), bonuses, calculateGrossSalary(), calculateTaxes(), deductions, calculateNetSalary());
    }
}
