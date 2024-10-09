package com.example.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 101;
    private ImageView imageView;
    private Button captureButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imgView);
        imageView.setOnClickListener(view -> {
            startAnimation();
        });
        captureButton = findViewById(R.id.captureButton);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndOpenCamera();
            }

            private void checkPermissionAndOpenCamera() {
                    // Kiểm tra quyền truy cập Camera
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Nếu chưa có quyền, yêu cầu quyền truy cập
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                } else {
                    // Nếu đã có quyền, mở ứng dụng Camera
                    openCamera();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Người dùng đã cấp quyền, mở Camera
                openCamera();
            } else {
                // Người dùng từ chối cấp quyền, hiển thị thông báo
                Toast.makeText(this, "Ứng dụng cần quyền truy cập Camera để chụp ảnh.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Nhận ảnh từ Camera và hiển thị trong ImageView
            Bundle extras = data.getExtras();
            if (extras != null) {
                // Lấy ảnh dưới dạng Bitmap
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }
    private void startAnimation(){
        RotateAnimation animation = new RotateAnimation( 0f,    // Từ 0 độ
                360f,  // Đến 360 độ
                Animation.RELATIVE_TO_SELF, 0.5f, // Trục X tại giữa View
                Animation.RELATIVE_TO_SELF, 0.5f );
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(animation);

    }
}
