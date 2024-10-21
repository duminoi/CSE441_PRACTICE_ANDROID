package com.example.gplxb2;

import android.os.Bundle;
import android.util.Log;
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
    }

    private void loadBienBaoData() {
        try {
            InputStream is = getAssets().open("BienBao.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(json);

            // Tải dữ liệu từ BienBaoCam
            JSONArray bienBaoCamArray = jsonObject.getJSONArray("BienBaoCam");
            for (int i = 0; i < bienBaoCamArray.length(); i++) {
                JSONObject bienBaoObject = bienBaoCamArray.getJSONObject(i);
                String name = bienBaoObject.getString("name");
                String des = bienBaoObject.getString("des");

                // Kiểm tra sự tồn tại của trường "image"
                String imagePath;
                if (bienBaoObject.has("image") && !bienBaoObject.isNull("image")) {
                    imagePath = bienBaoObject.getString("image");
                } else {
                    imagePath = "bienbao.png"; // Hình ảnh mặc định
                }

                BienBao bienBao = new BienBao(name, des, imagePath);
                bienBaoList.add(bienBao);
            }

        } catch (Exception e) {
            e.printStackTrace(); // In ra chi tiết lỗi
            Toast.makeText(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
