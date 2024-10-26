package com.example.gplxb2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private TextView totalQuestionsText;
    private TextView resultMessage;
    private Button backButton;
    private Toolbar toolbar;
    private TextView toolbarTitle; // TextView cho tiêu đề trên toolbar
    private List<QuestionResult> questionResults; // Danh sách kết quả câu hỏi


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        resultText = findViewById(R.id.result_text);
        totalQuestionsText = findViewById(R.id.total_questions_text);
        resultMessage = findViewById(R.id.result_message);
        backButton = findViewById(R.id.back_btn);
        RecyclerView questionsRecyclerView = findViewById(R.id.questions_recycler_view);

        // Receive data from Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        int totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0);
        int incorrectCriticalCount = intent.getIntExtra("INCORRECT_CRITICAL_COUNT", 0);
        String examTitle = intent.getStringExtra("TITLE");
        String examType = intent.getStringExtra("EXAM_TYPE"); // Nhận loại bộ đề
        questionResults = (List<QuestionResult>) intent.getSerializableExtra("QUESTION_RESULTS");

        // Set up RecyclerView
        questionsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        QuestionResultAdapter adapter = new QuestionResultAdapter(this, questionResults);
        questionsRecyclerView.setAdapter(adapter);

        // Set toolbar title
        toolbarTitle.setText(examTitle != null ? examTitle : "Kết quả thi");

        // Display the score and total questions
        resultText.setText("Your Score: " + score);
        totalQuestionsText.setText("Total Questions: " + totalQuestions);

        // Set toolbar title
        if (examTitle != null) {
            toolbarTitle.setText(examTitle); // Thiết lập tiêu đề lên toolbar
        } else {
            toolbarTitle.setText("Kết quả thi"); // Tiêu đề mặc định nếu không có
        }

        // Determine the result message
        if (incorrectCriticalCount > 0) {
            resultMessage.setText("Bạn đã thi trượt vì làm sai câu điểm liệt");
            resultMessage.setTextColor(Color.RED);
        } else if (score < 32) {
            resultMessage.setText("Bạn đã thi trượt do không đủ số câu đúng tối thiểu");
            resultMessage.setTextColor(Color.RED);
        } else {
            resultMessage.setText("Chúc mừng bạn đã thi đạt");
            resultMessage.setTextColor(Color.GREEN);
        }

        // Set up the back button event
        backButton.setOnClickListener(v -> goToMain());

        // Thiết lập sự kiện khi click vào các câu hỏi
        adapter.setOnItemClickListener(position -> {
            QuestionResult selectedResult = questionResults.get(position);
            goToTestActivity(position, selectedResult);
        });
    }

    // Điều hướng tới TestActivity với thông tin câu hỏi được chọn
    private void goToTestActivity(int position, QuestionResult selectedResult) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("SELECTED_QUESTION_POSITION", position);
        intent.putExtra("SELECTED_QUESTION_RESULT", selectedResult);
        startActivity(intent);
    }

    // Method to return to the main page
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}




//// ResultActivity.java
//package com.example.gplxb2;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ResultActivity extends AppCompatActivity {
//
//    private TextView resultText;
//    private TextView totalQuestionsText;
//    private TextView resultMessage;
//    private Button backButton;
//    private Toolbar toolbar;
//    private TextView toolbarTitle; // TextView cho tiêu đề trên toolbar
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_result);
//
//        // Initialize views
//        toolbar = findViewById(R.id.toolbar);
//        toolbarTitle = findViewById(R.id.toolbar_title);
//        resultText = findViewById(R.id.result_text);
//        totalQuestionsText = findViewById(R.id.total_questions_text);
//        resultMessage = findViewById(R.id.result_message);
//        backButton = findViewById(R.id.back_btn);
//        RecyclerView questionsRecyclerView = findViewById(R.id.questions_recycler_view);
//
//        // Receive data from Intent
//        Intent intent = getIntent();
//        int score = intent.getIntExtra("SCORE", 0);
//        int totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0);
//        int incorrectCriticalCount = intent.getIntExtra("INCORRECT_CRITICAL_COUNT", 0);
//        String examTitle = intent.getStringExtra("TITLE");
//        String examType = intent.getStringExtra("EXAM_TYPE"); // Nhận loại bộ đề
//        List<QuestionResult> questionResults = (List<QuestionResult>) intent.getSerializableExtra("QUESTION_RESULTS");
//
//        // Set up RecyclerView
//        questionsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        QuestionResultAdapter adapter = new QuestionResultAdapter(this, questionResults);
//        questionsRecyclerView.setAdapter(adapter);
//
//        // Set toolbar title
//        toolbarTitle.setText(examTitle != null ? examTitle : "Kết quả thi");
//
//        // Display the score and total questions
//        resultText.setText("Your Score: " + score);
//        totalQuestionsText.setText("Total Questions: " + totalQuestions);
//
//
//
//
//        // Set toolbar title
//        if (examTitle != null) {
//            toolbarTitle.setText(examTitle); // Thiết lập tiêu đề lên toolbar
//        } else {
//            toolbarTitle.setText("Kết quả thi"); // Tiêu đề mặc định nếu không có
//        }
//
//        // Determine the result message
//        if (incorrectCriticalCount > 0) {
//            resultMessage.setText("Bạn đã thi trượt vì làm sai câu điểm liệt");
//            resultMessage.setTextColor(Color.RED);
//        } else if (score < 32) {
//            resultMessage.setText("Bạn đã thi trượt do không đủ số câu đúng tối thiểu");
//            resultMessage.setTextColor(Color.RED);
//        } else {
//            resultMessage.setText("Chúc mừng bạn đã thi đạt");
//            resultMessage.setTextColor(Color.GREEN);
//        }
//
//        // Set up the back button event
//        backButton.setOnClickListener(v -> goToMain());
//
//
//
//    }
//
//    // Method to return to the main page
//    public void goToMain() {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//    }
//}
