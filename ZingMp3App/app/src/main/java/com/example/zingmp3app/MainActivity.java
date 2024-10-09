package com.example.zingmp3app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnPlay, btnStop;
    private TextView tvSongTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnPlay = findViewById(R.id.txt_play);
        btnStop = findViewById(R.id.txt_stop);
        tvSongTitle = findViewById(R.id.txt_name);

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            intent.putExtra("audioResource", R.raw.yeuvoivang); // Thay đổi ở đây để sử dụng tài nguyên âm thanh
            startService(intent);
            tvSongTitle.setText("Đang phát: song.mp3"); // Thay đổi tiêu đề nếu cần
        });

        btnStop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            stopService(intent);
            tvSongTitle.setText("Dừng phát nhạc");
        });
    }
}
