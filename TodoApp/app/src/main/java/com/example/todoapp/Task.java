package com.example.todoapp;

public class Task {
    public int id;
    public String name;
    public boolean completed;

    public Task(int id, String name, boolean completed) {
        this.id = id;
        this.name = name;
        this.completed = completed;
    }
}
