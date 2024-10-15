// TypeAdapter.java
package com.example.blxproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private Context context;
    private JSONArray typeList;

    public TypeAdapter(Context context, JSONArray typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drive_type_item, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        try {
            JSONObject typeObject = typeList.getJSONObject(position);
            String title = typeObject.getString("title");
            int quantity = typeObject.getInt("quantity"); // Lấy số lượng
            holder.textTitle.setText(title);
            holder.textSubtitle.setText("Số lượng: " + quantity); // Hiển thị số lượng
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return typeList.length();
    }

    public static class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textSubtitle; // Thêm biến cho subtitle

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txt_title); // Chỉnh sửa id
            textSubtitle = itemView.findViewById(R.id.txt_subtitle); // Chỉnh sửa id
        }
    }
}
