package com.example.todoapp;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAddTask;
    private Switch switchShowCompleted;
    private ListView listViewTasks;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private TaskDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.edit_text_task);
        buttonAddTask = findViewById(R.id.button_add_task);
        switchShowCompleted = findViewById(R.id.switch_show_completed);
        listViewTasks = findViewById(R.id.list_view_tasks);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(taskAdapter);

        dbHelper = new TaskDbHelper(this);

        loadTasks();
        loadSettings();

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        switchShowCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveSettings();
            loadTasks();
        });

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = taskList.get(position);
                CheckBox checkBoxCompleted = view.findViewById(R.id.checkbox_completed);
                checkBoxCompleted.setChecked(!task.completed);
                updateTask(task.id, !task.completed);
            }
        });

        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = taskList.get(position);
                deleteTask(task.id);
                return true;
            }
        });
    }

    private void addTask() {
        String taskName = editTextTask.getText().toString();
        if (taskName.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", taskName);
        long newRowId = db.insert("tasks", null, values);
        db.close();

        if (newRowId != -1) {
            editTextTask.setText("");
            loadTasks();
        } else {
            Toast.makeText(this, "Lỗi khi thêm công việc", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadTasks() {
        taskList.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = { "id", "name", "completed" };
        String selection = null;
        String[] selectionArgs = null;
        if (!switchShowCompleted.isChecked()) {
            selection = "completed = ?";
            selectionArgs = new String[] { "0" };
        }
        Cursor cursor = db.query("tasks", projection, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            boolean completed = cursor.getInt(cursor.getColumnIndexOrThrow("completed")) == 1;
            taskList.add(new Task(id, name, completed));
        }

        cursor.close();
        db.close();

        taskAdapter.notifyDataSetChanged();
    }

    private void updateTask(int id, boolean completed) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", completed ? 1 : 0);
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(id) };
        db.update("tasks", values, whereClause, whereArgs);
        db.close();

        loadTasks();
    }

    private void deleteTask(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(id) };
        db.delete("tasks",
                whereClause, whereArgs);
        db.close();

        loadTasks();
    }

    private void loadSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        boolean showCompleted = sharedPreferences.getBoolean("show_completed", false);
        switchShowCompleted.setChecked(showCompleted);
    }

    private void saveSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("show_completed",
                switchShowCompleted.isChecked());
        editor.apply();
    }
}
