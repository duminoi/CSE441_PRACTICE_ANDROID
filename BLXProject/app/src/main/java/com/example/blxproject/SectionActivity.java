package com.example.blxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SectionActivity extends AppCompatActivity {

    private String licenseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        licenseType = getIntent().getStringExtra("license_type");
        TextView titleText = findViewById(R.id.text_title);
        titleText.setText("Ôn bằng lái xe máy hạng " + licenseType);

        // Gán hành động cho các nút
        ImageButton buttonTest = findViewById(R.id.button_test);
        buttonTest.setOnClickListener(v -> {
            // Mở màn hình thi
            Intent intent = new Intent(SectionActivity.this, ExamActivity.class);
            startActivity(intent);
        });

        ImageButton buttonWrongQuestions = findViewById(R.id.button_wrong_questions);
        buttonWrongQuestions.setOnClickListener(v -> {
            // Mở màn hình xem câu bị sai
        });

        ImageButton buttonTrafficSigns = findViewById(R.id.button_traffic_signs);
        buttonTrafficSigns.setOnClickListener(v -> {
            // Mở màn hình biển báo
        });

        ImageButton buttonFiftyQuestions = findViewById(R.id.button_fifty_questions);
        buttonFiftyQuestions.setOnClickListener(v -> {
            // Mở màn hình 50 câu hỏi hay sai
        });

        ImageButton buttonSixtyCriticalQuestions = findViewById(R.id.button_sixty_critical_questions);
        buttonSixtyCriticalQuestions.setOnClickListener(v -> {
            // Mở màn hình 60 câu điểm liệt
        });

        ImageButton buttonTips = findViewById(R.id.button_tips);
        buttonTips.setOnClickListener(v -> {
            // Mở màn hình mẹo thi
        });

        ImageButton buttonLookupLaw = findViewById(R.id.button_lookup_law);
        buttonLookupLaw.setOnClickListener(v -> {
            // Mở màn hình tra cứu luật
        });
    }
}
