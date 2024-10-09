package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.btn_submit);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            Bundle bundle = new Bundle();

//            String greeting = "hello";
//            bundle.putString("greeting",greeting);
//            intent.putExtras(bundle);
//            setResult(RESULT_OK,intent);

            EditText edtName = findViewById(R.id.edt_name);
            EditText edtGPA = findViewById(R.id.edt_gpa);
            String name = edtName.getText().toString();
            String gpa = edtGPA.getText().toString();
            bundle.putString("name", name);
            bundle.putString("gpa", gpa);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
        ImageButton imgBack = findViewById(R.id.img_btn_back);
        imgBack.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });
    }
}