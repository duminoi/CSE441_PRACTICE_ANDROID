package com.example.blxproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exam);

        // Tìm kiếm các nút và thiết lập OnClickListener
        Button buttonExam1 = findViewById(R.id.button_exam_1);
        Button buttonExam2 = findViewById(R.id.button_exam_2);
        Button buttonExam3 = findViewById(R.id.button_exam_3);
        Button buttonExam4 = findViewById(R.id.button_exam_4);
        Button buttonExam5 = findViewById(R.id.button_exam_5);
        Button buttonExam6 = findViewById(R.id.button_exam_6);
        Button buttonExam7 = findViewById(R.id.button_exam_7);
        Button buttonExam8 = findViewById(R.id.button_exam_8);

        // Thiết lập sự kiện click cho các nút
        View.OnClickListener examClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamActivity.this, ExamQuestionActivity.class);
                // Bạn có thể truyền thêm thông tin nếu cần
                intent.putExtra("exam_id", v.getId()); // Ví dụ, truyền ID của nút
                startActivity(intent);
            }
        };

        buttonExam1.setOnClickListener(examClickListener);
        buttonExam2.setOnClickListener(examClickListener);
        buttonExam3.setOnClickListener(examClickListener);
        buttonExam4.setOnClickListener(examClickListener);
        buttonExam5.setOnClickListener(examClickListener);
        buttonExam6.setOnClickListener(examClickListener);
        buttonExam7.setOnClickListener(examClickListener);
        buttonExam8.setOnClickListener(examClickListener);
    }
}
