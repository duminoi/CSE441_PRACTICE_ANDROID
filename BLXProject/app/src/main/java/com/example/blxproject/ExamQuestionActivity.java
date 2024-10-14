package com.example.blxproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ExamQuestionActivity extends AppCompatActivity {
    private TextView questionText;
    private RadioGroup radioGroup;
    private Button buttonNext;

    private List<Question> questions;
    private int currentQuestionIndex = 0;

    // Danh sách để lưu trữ câu trả lời của người dùng
    private List<Integer> userAnswers; // Lưu đáp án đã chọn của người dùng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);

        questionText = findViewById(R.id.question_text);
        radioGroup = findViewById(R.id.radio_group);
        buttonNext = findViewById(R.id.button_next);

        // Khởi tạo danh sách câu hỏi
        initializeQuestions();
        userAnswers = new ArrayList<>(questions.size()); // Khởi tạo danh sách lưu đáp án

        displayCurrentQuestion();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra xem có câu trả lời nào được chọn không
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    // Nếu không có câu trả lời nào được chọn, hiển thị thông báo
                    Toast.makeText(ExamQuestionActivity.this, "Vui lòng chọn một câu trả lời!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lưu đáp án đã chọn của người dùng
                RadioButton selectedRadioButton = findViewById(selectedId);
                int selectedAnswerIndex = radioGroup.indexOfChild(selectedRadioButton);
                userAnswers.add(selectedAnswerIndex); // Lưu đáp án đã chọn

                // Chuyển đến câu hỏi tiếp theo
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayCurrentQuestion();
                } else {
                    // Hiển thị tổng hợp kết quả
                    showResults();
                }
            }
        });
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("Câu hỏi 1: Đây là câu hỏi gì?",
                new String[]{"Đáp án 1", "Đáp án 2", "Đáp án 3", "Đáp án 4"}, 0));
        questions.add(new Question("Câu hỏi 2: Câu hỏi này là gì?",
                new String[]{"Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D"}, 1));
        questions.add(new Question("Câu hỏi 3: Câu hỏi tiếp theo là gì?",
                new String[]{"Đáp án X", "Đáp án Y", "Đáp án Z", "Đáp án W"}, 2));
        // Thêm nhiều câu hỏi hơn nếu cần
    }

    private void displayCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionText.setText(currentQuestion.getQuestion());

        // Đặt các đáp án cho RadioButton
        ((RadioButton) findViewById(R.id.option1)).setText(currentQuestion.getOptions()[0]);
        ((RadioButton) findViewById(R.id.option2)).setText(currentQuestion.getOptions()[1]);
        ((RadioButton) findViewById(R.id.option3)).setText(currentQuestion.getOptions()[2]);
        ((RadioButton) findViewById(R.id.option4)).setText(currentQuestion.getOptions()[3]);

        // Đặt lại RadioGroup
        radioGroup.clearCheck();
    }

    private void showResults() {
        StringBuilder result = new StringBuilder();
        int correctAnswers = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            int correctAnswerIndex = question.getAnswerIndex();
            int userAnswerIndex = userAnswers.get(i);

            if (userAnswerIndex == correctAnswerIndex) {
                correctAnswers++;
                result.append("Câu hỏi ").append(i + 1).append(": Đúng!\n");
            } else {
                result.append("Câu hỏi ").append(i + 1).append(": Sai! Đáp án đúng là: ")
                        .append(question.getOptions()[correctAnswerIndex]).append("\n");
            }
        }

        result.append("\nTổng số câu đúng: ").append(correctAnswers).append("/")
                .append(questions.size());

        // Hiển thị kết quả
        Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
    }
}
