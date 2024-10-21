package com.example.gplxb2;

public class BienBao {
    private String name;
    private String des; // Đã đổi tên biến từ des sang getDes
    private String imagePath; // Thêm thuộc tính imagePath

    public BienBao(String name, String des, String imagePath) {
        this.name = name;
        this.des = des;
        this.imagePath = imagePath; // Khởi tạo imagePath
    }

    public String getName() {
        return name;
    }

    public String getDes() { // Thay đổi tên phương thức từ getDescription sang getDes
        return des;
    }

    public String getImagePath() {
        return (imagePath != null && !imagePath.isEmpty()) ? imagePath : "bienbao.png"; // Hình ảnh mặc định
    }
}
