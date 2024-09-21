package com.example.ex01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        EditText edtA, edtB, edtTong;
        Button btnTong;
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtTong = findViewById(R.id.edtTong);
        btnTong = findViewById(R.id.btnTong);

        btnTong.setOnClickListener(v -> {
           int a = Integer.parseInt(edtA.getText().toString());
           int b= Integer.parseInt(edtB.getText().toString());

           edtTong.setText(String.valueOf(a + b));

        });
    }
}
