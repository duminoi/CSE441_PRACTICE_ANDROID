

package com.example.blxproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private TextView txtResult;
    private TextView txtScore;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private List<Answer> answerList; // Danh sách câu trả lời

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResult = findViewById(R.id.txt_result);
        txtScore = findViewById(R.id.txt_score);
        recyclerView = findViewById(R.id.recycler_view);

        // Nhận dữ liệu từ ExamQuestionActivity
        Intent intent = getIntent();
        int correctAnswers = intent.getIntExtra("correctAnswers", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);
        String[] userAnswers = intent.getStringArrayExtra("userAnswers");
        Question[] questions = (Question[]) intent.getSerializableExtra("questions");

        // Tính toán trượt/đỗ
        double percentage = (double) correctAnswers / totalQuestions * 100;
        if (percentage >= 85) {
            txtResult.setText("Đỗ");
            txtResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            txtResult.setText("Trượt");
            txtResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        txtScore.setText(correctAnswers + " / " + totalQuestions);

        // Hiển thị danh sách câu hỏi
        answerList = new ArrayList<>(); // Khởi tạo danh sách câu trả lời
        for (int i = 0; i < questions.length; i++) {
            boolean isCorrect = userAnswers[i].equals(questions[i].getAnswer());
            answerList.add(new Answer(questions[i].getQuestion(), isCorrect));
        }
        Log.d("ResultActivity", "Total Questions: " + questions.length);
        Log.d("ResultActivity", "User Answers: " + userAnswers.length);
        Log.d("ResultActivity", "Answer List Size: " + answerList.size());

        // Thiết lập RecyclerView
        adapter = new ResultAdapter(this, answerList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(adapter);
    }
}
