package com.example.recylerviewdemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgFlag;
    private TextView txtCountryName, txtCapital, txtPopulation, txtArea, txtDensity, txtWorldShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Đảm bảo tệp XML phù hợp

        imgFlag = findViewById(R.id.img_flag);
        txtCountryName = findViewById(R.id.txt_nation);
        txtCapital = findViewById(R.id.txt_capital);
        txtPopulation = findViewById(R.id.txt_population);
        txtArea = findViewById(R.id.txt_area);
        txtDensity = findViewById(R.id.txt_density);
        txtWorldShare = findViewById(R.id.txt_world_share);

        // Nhận dữ liệu từ Intent
        String countryName = getIntent().getStringExtra("countryName");
        String countryCapital = getIntent().getStringExtra("countryCapital");
        int flag = getIntent().getIntExtra("flag", 0);
        int population = getIntent().getIntExtra("population", 0);
        int area = getIntent().getIntExtra("area", 0);
        double density = getIntent().getDoubleExtra("density", 0.0);
        double worldShare = getIntent().getDoubleExtra("worldShare", 0.0);

        // Thiết lập dữ liệu vào các View
        imgFlag.setImageResource(flag);
        txtCountryName.setText("Nation: "+countryName);
        txtCapital.setText("Capital: "+countryCapital);
        txtPopulation.setText("Population: "+String.valueOf(population));
        txtArea.setText("Area: "+String.valueOf(area));
        txtDensity.setText("Density: "+String.valueOf(density));
        txtWorldShare.setText("World share: "+String.valueOf(worldShare));
    }
}
