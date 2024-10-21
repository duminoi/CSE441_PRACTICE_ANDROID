package com.example.gplxb2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BienBaoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BienBaoAdapter adapter;
    private List<BienBao> bienBaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien_bao);

        recyclerView = findViewById(R.id.recyclerView);
        bienBaoList = new ArrayList<>();

        loadBienBaoData();

        adapter = new BienBaoAdapter(this, bienBaoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        // Thiết lập sự kiện cho nút "Quay lại"
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Kết thúc activity hiện tại
            }
        });
    }

    private void loadBienBaoData() {
        try {
            InputStream is = getAssets().open("BienBao.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray names = jsonObject.names(); // Lấy danh sách các khóa

            if (names != null) {
                for (int i = 0; i < names.length(); i++) {
                    String key = names.getString(i); // Lấy khóa
                    JSONArray bienBaoArray = jsonObject.getJSONArray(key);

                    // Thêm một tiêu đề cho mỗi mảng
                    bienBaoList.add(new BienBao(key, "", "")); // Sử dụng tên mảng làm tiêu đề

                    for (int j = 0; j < bienBaoArray.length(); j++) {
                        JSONObject bienBaoObject = bienBaoArray.getJSONObject(j);
                        String name = bienBaoObject.getString("name");
                        String des = bienBaoObject.getString("des");

                        String imagePath;
                        if (bienBaoObject.has("image") && !bienBaoObject.isNull("image")) {
                            imagePath = bienBaoObject.getString("image");
                        } else {
                            imagePath = "bienbao.png"; // Hình ảnh mặc định
                        }

                        BienBao bienBao = new BienBao(name, des, imagePath);
                        bienBaoList.add(bienBao);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
