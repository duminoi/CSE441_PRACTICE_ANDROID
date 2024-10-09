package com.example.simplenoteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dictionary.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE words (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "word TEXT," +
                "definition TEXT," +
                "pronunciation TEXT," +
                "example TEXT)";
        db.execSQL(sql);

        // Chèn dữ liệu từ file CSV
        try {
            InputStream inputStream = context.getAssets().open("data.csv"); // Thay "data.csv" bằng tên file CSV của bạn
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Giả sử dữ liệu trong file CSV được phân cách bằng dấu phẩy
                if (parts.length >= 2) {
                    String word = parts[0];
                    String definition = parts[1];
                    String pronunciation = (parts.length >= 3) ? parts[2] : "";
                    String example = (parts.length >= 4) ? parts[3] : "";
                    insertWord(db, word, definition, pronunciation, example);
                }
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp database (nếu cần)
        db.execSQL("DROP TABLE IF EXISTS words");
        onCreate(db);
    }

    private void insertWord(SQLiteDatabase db, String word, String definition, String pronunciation, String example) {
        String sql = "INSERT INTO words (word, definition, pronunciation, example) VALUES (?, ?, ?, ?)";
        db.execSQL(sql, new Object[]{word, definition, pronunciation, example});
    }
}

