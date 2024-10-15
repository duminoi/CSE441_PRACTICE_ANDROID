// ReviewQuestionActivity.java
package com.example.blxproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ReviewQuestion extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TypeAdapter typeAdapter;
    private TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_question);
        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v ->{
            Intent intent = new Intent(ReviewQuestion.this, SectionActivity.class);
            startActivity(intent);
        });

        // Thiết lập TextView hiển thị tiêu đề
        textTitle = findViewById(R.id.text_title_review);
        textTitle.setText("Danh sách câu hỏi ôn tập");

        // Thiết lập RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Đọc dữ liệu từ file JSON
        JSONArray types = loadJSONFromAsset();
        typeAdapter = new TypeAdapter(this, types);
        recyclerView.setAdapter(typeAdapter);
    }

    // Phương thức đọc dữ liệu từ file JSON
    private JSONArray loadJSONFromAsset() {
        try {
            InputStream is = getAssets().open("driver_type.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json); // Thay đổi để parse JSONObject
            return jsonObject.getJSONArray("types"); // Lấy mảng từ "types"
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
            return new JSONArray();
        }
    }

}
