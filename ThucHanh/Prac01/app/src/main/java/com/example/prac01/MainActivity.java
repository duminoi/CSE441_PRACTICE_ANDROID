package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
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
     Button button = findViewById(R.id.btn_start_activity);
     button.setOnClickListener(v -> {
         Intent intent = new Intent(MainActivity.this, MainActivity2.class);
         startActivityForResult(intent, REQUEST_CODE);
     });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onResume();
        assert data != null;
//        String greeting = data.getStringExtra("greeting");
//        Toast.makeText(this, greeting, Toast.LENGTH_SHORT).show(); // Hiển thị greeting
        String name = data.getStringExtra("name");
        String gpa = data.getStringExtra("gpa");
        TextView txtName = findViewById(R.id.txt_name);
        TextView txtGPA = findViewById(R.id.txt_gpa);
        txtName.setText("Ho va ten: " +name);
        txtGPA.setText("GPa: "+gpa);

    }


}