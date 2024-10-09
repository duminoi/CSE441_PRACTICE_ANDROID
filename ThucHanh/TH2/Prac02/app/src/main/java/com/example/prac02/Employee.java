package com.example.prac02;

import java.util.Date;

public class Employee {
    private int id;            // Thêm thuộc tính id
    private String name;       // Tên nhân viên
    private String date;         // Ngày tham gia
    private double salary;      // Lương

    public Employee(int id, String name, String date, double salary) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }




}

