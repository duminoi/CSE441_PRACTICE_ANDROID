package com.example.prac02;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;
    private TextView txtResult, txtStatus;
    private EditText edtId, edtName, edtDate, edtSalary;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view từ layout
        txtResult = findViewById(R.id.txt_result);
        txtStatus = findViewById(R.id.txt_status);
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        edtDate = findViewById(R.id.edt_date);
        edtSalary = findViewById(R.id.edt_salary);
        btnAdd = findViewById(R.id.btn_add);

        // Tạo ViewModel
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        // Giám sát dữ liệu từ ViewModel
        employeeViewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                if (employees.isEmpty()) {
                    txtResult.setText("No result");
                } else {
                    StringBuilder result = new StringBuilder();

                    for (Employee employee : employees) {
                        result.append("ID: ").append(employee.getId())
                                .append(", Name: ").append(employee.getName())
                                .append(", Date: ").append(employee.getDate())
                                .append(", Salary: ").append(employee.getSalary())
                                .append("\n");
                    }
                    txtResult.setText(result.toString());
                }
                // Cập nhật trạng thái sau khi thêm vài nhân viên
                txtStatus.setText("Sau khi thêm " + employees.size() + " nhân viên");
            }
        });

        // Lắng nghe thay đổi trong các trường nhập liệu
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kiểm tra xem các trường có được nhập chưa
                if (edtId.getText().toString().trim().isEmpty() ||
                        edtName.getText().toString().trim().isEmpty() ||
                        edtDate.getText().toString().trim().isEmpty() ||
                        edtSalary.getText().toString().trim().isEmpty()) {
                    txtStatus.setText("Chưa nhập đủ dữ liệu");
                } else {
                    txtStatus.setText("Đã nhập nhưng chưa nhấn nút");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        // Áp dụng textWatcher cho các EditText
        edtId.addTextChangedListener(textWatcher);
        edtName.addTextChangedListener(textWatcher);
        edtDate.addTextChangedListener(textWatcher);
        edtSalary.addTextChangedListener(textWatcher);

        // Thêm nhân viên khi bấm nút Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ EditText
                String idInput = edtId.getText().toString().trim();
                String nameInput = edtName.getText().toString().trim();
                String dateInput = edtDate.getText().toString().trim();
                String salaryInput = edtSalary.getText().toString().trim();

                // Kiểm tra nếu chưa nhập đầy đủ dữ liệu
                if (idInput.isEmpty() || nameInput.isEmpty() || dateInput.isEmpty() || salaryInput.isEmpty()) {
                    txtStatus.setText("Vui lòng nhập đầy đủ thông tin");
                } else {
                    // Nếu đã nhập đủ dữ liệu, thêm nhân viên mới
                    int id = Integer.parseInt(idInput);
                    String name = nameInput;
                    String date = dateInput;
                    double salary = Double.parseDouble(salaryInput);

                    // Thêm nhân viên mới vào ViewModel
                    Employee newEmployee = new Employee(id, name, date, salary);
                    employeeViewModel.addEmployee(newEmployee);

                    // Xóa dữ liệu sau khi thêm
                    edtId.setText("");
                    edtName.setText("");
                    edtDate.setText("");
                    edtSalary.setText("");

                    // Cập nhật trạng thái sau khi thêm
                    txtStatus.setText("Đã thêm nhân viên mới");
                }
            }
        });
    }
}
