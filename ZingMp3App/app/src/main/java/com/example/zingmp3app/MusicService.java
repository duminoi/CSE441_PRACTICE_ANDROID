package com.example.zingmp3app;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.yeuvoivang); // Thay your_audio_file bằng file âm thanh của bạn
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Kiểm tra Intent có null không
        if (intent == null) {
            Log.e("MusicService", "Intent is null");
            return START_NOT_STICKY;
        }

        // Lấy dữ liệu từ Intent
        int someValue = intent.getIntExtra("key_name", -1); // Sử dụng -1 hoặc giá trị mặc định khác nếu không tìm thấy

        // Chạy nhạc nếu cần
        if (someValue != -1) {
            mediaPlayer.start(); // Chạy nhạc
        } else {
            Log.e("MusicService", "No valid value received from Intent");
        }

        // Trả về trạng thái cho hệ thống
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Không sử dụng binding
    }
}
