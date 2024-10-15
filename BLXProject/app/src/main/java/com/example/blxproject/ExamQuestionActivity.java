package com.example.blxproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExamQuestionActivity extends AppCompatActivity {
    private TextView txtQuestion;
    private RadioGroup radioGroup;
    private Button btnPrevious;
    private Button btnNext;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private List<String> userAnswers; // Lưu đáp án đã chọn của người dùng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);

        txtQuestion = findViewById(R.id.txt_question);
        radioGroup = findViewById(R.id.radio_group);
        btnPrevious = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);

        // Khởi tạo danh sách câu hỏi từ tệp JSON
        questions = loadQuestionsFromJson();
        if (questions == null || questions.isEmpty()) {
            Toast.makeText(this, "Không có câu hỏi nào được tải!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity nếu không có câu hỏi
            return;
        }

        userAnswers = new ArrayList<>(questions.size());
        for (int i = 0; i < questions.size(); i++) {
            userAnswers.add(""); // Khởi tạo danh sách đáp án với các chuỗi trống
        }

        displayCurrentQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNextButtonClick();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePreviousButtonClick();
            }
        });
    }

    private void handleNextButtonClick() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(ExamQuestionActivity.this, "Vui lòng chọn một câu trả lời!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedAnswer = selectedRadioButton.getTag().toString();
        userAnswers.set(currentQuestionIndex, selectedAnswer); // Lưu đáp án đã chọn

        currentQuestionIndex++;

        // Log giá trị currentQuestionIndex
        Log.d("ExamQuestionActivity", "currentQuestionIndex: " + currentQuestionIndex);

        // Kiểm tra nếu currentQuestionIndex vượt quá số câu hỏi
        if (currentQuestionIndex >= questions.size()) {
            showResults(); // Gọi phương thức để hiện kết quả
        } else {
            displayCurrentQuestion();
        }
    }



    private void handlePreviousButtonClick() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayCurrentQuestion();
        } else {
            Toast.makeText(this, "Đây là câu hỏi đầu tiên!", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Question> loadQuestionsFromJson() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("driver_data.json")));
            // Đọc JSON từ trường "question"
            Type listType = new TypeToken<List<Question>>() {}.getType();
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            return new Gson().fromJson(jsonObject.get("question"), listType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void displayCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        txtQuestion.setText(currentQuestion.getQuestion());

        // Đặt các đáp án cho RadioButton
        String[] options = {"a", "b", "c", "d", "e"};
        radioGroup.clearCheck();
        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton == null) {
                continue; // Bỏ qua nếu RadioButton không tồn tại
            }
            String optionText = null;
            switch (options[i]) {
                case "a":
                    optionText = currentQuestion.getOption().getA();
                    break;
                case "b":
                    optionText = currentQuestion.getOption().getB();
                    break;
                case "c":
                    optionText = currentQuestion.getOption().getC();
                    break;
                case "d":
                    optionText = currentQuestion.getOption().getD();
                    break;
                case "e":
                    optionText = currentQuestion.getOption().getE();
                    break;
            }
            if (optionText != null) {
                radioButton.setVisibility(View.VISIBLE);
                radioButton.setText(optionText);
                radioButton.setTag(options[i]);
            } else {
                radioButton.setVisibility(View.GONE);
            }
        }
    }

    private void showResults() {
        int correctAnswers = 0;

        // Tính số câu đúng
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String correctAnswer = question.getAnswer(); // Đáp án đúng
            String userAnswer = userAnswers.get(i); // Đáp án của người dùng

            if (userAnswer.equals(correctAnswer)) {
                correctAnswers++;
            }
        }

        // Chuyển đến ResultActivity
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("correctAnswers", correctAnswers);
        intent.putExtra("totalQuestions", questions.size());
        intent.putExtra("userAnswers", userAnswers.toArray(new String[0])); // Chuyển đổi danh sách thành mảng
        intent.putExtra("questions", questions.toArray(new Question[0])); // Chuyển đổi danh sách thành mảng

        startActivity(intent);
        finish(); // Đóng Activity hiện tại
    }



}
