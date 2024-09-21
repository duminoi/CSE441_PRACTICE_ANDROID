package com.example.ex03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText edtA = findViewById(R.id.edtA);
        EditText edtB = findViewById(R.id.edtB);
        EditText edtKetQua = findViewById(R.id.edtKetQua);
        Button btnTong = findViewById(R.id.btnTong);
        Button btnHieu = findViewById(R.id.btnHieu);
        Button btnTich = findViewById(R.id.btnTich);
        Button btnThuong = findViewById(R.id.btnThuong);

        btnTong.setOnClickListener(view -> {
            float a = Float.parseFloat(edtA.getText().toString());
            float b = Float.parseFloat(edtB.getText().toString());
            float tong = a + b;
            edtKetQua.setText(String.valueOf(tong));
        });

        btnHieu.setOnClickListener(view -> {
            float a = Float.parseFloat(edtA.getText().toString());
            float b = Float.parseFloat(edtB.getText().toString());
            float hieu = a - b;
            edtKetQua.setText(String.valueOf(hieu));
        });

        btnTich.setOnClickListener(view -> {
            float a = Float.parseFloat(edtA.getText().toString());
            float b = Float.parseFloat(edtB.getText().toString());
            float tich = a * b;
            edtKetQua.setText(String.valueOf(tich));
        });

        btnThuong.setOnClickListener(view -> {
            float a = Float.parseFloat(edtA.getText().toString());
            float b = Float.parseFloat(edtB.getText().toString());
            float thuong = a / b;
            if( b == 0){
                edtKetQua.setText("Không thể chia cho 0");
                return;
            }else{
                edtKetQua.setText(String.valueOf(thuong));
            }
        });
    }

}