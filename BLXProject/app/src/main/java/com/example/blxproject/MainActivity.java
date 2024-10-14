package com.example.blxproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonA1 = findViewById(R.id.button_a1);
        Button buttonA2 = findViewById(R.id.button_a2);

        buttonA1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SectionActivity.class);
            intent.putExtra("license_type", "A1");
            startActivity(intent);
        });

        buttonA2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SectionActivity.class);
            intent.putExtra("license_type", "A2");
            startActivity(intent);
        });
    }
}
