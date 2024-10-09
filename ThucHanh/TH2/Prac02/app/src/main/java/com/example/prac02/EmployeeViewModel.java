package com.example.prac02;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeViewModel extends ViewModel {
    private MutableLiveData<List<Employee>> employees;

    public EmployeeViewModel() {
        employees = new MutableLiveData<>();
        List<Employee> initialEmployees = new ArrayList<>();
//        initialEmployees.add(new Employee(1, "Nguyen Van A", "1/2/2000", 50000));
//        initialEmployees.add(new Employee(2, "Le Thi B", "1/2/2000", 60000));
//        initialEmployees.add(new Employee(3, "Tran Van C", "1/2/2000", 70000));
        employees.setValue(initialEmployees);
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        List<Employee> currentList = employees.getValue();
        if (currentList != null) {
            currentList.add(employee);
            employees.setValue(currentList);
        }
    }
}
