package com.example.gplxb2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BienBaoAdapter extends RecyclerView.Adapter<BienBaoAdapter.BienBaoViewHolder> {
    private Context context;
    private List<BienBao> bienBaoList;

    public BienBaoAdapter(Context context, List<BienBao> bienBaoList) {
        this.context = context;
        this.bienBaoList = bienBaoList;
    }

    @NonNull
    @Override
    public BienBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bien_bao, parent, false);
        return new BienBaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BienBaoViewHolder holder, int position) {
        BienBao bienBao = bienBaoList.get(position);

        // Kiểm tra xem đây có phải là tiêu đề (không có mô tả và ảnh)
        if ((bienBao.getDes() == null || bienBao.getDes().isEmpty()) &&
                (bienBao.getImagePath() == null || bienBao.getImagePath().equals("bienbao.png"))) {
            // Nếu là tiêu đề, chỉ hiển thị tên với chữ in đậm và kích thước lớn hơn
            holder.nameTextView.setText(bienBao.getName());
            holder.nameTextView.setTextSize(20); // Kích thước chữ lớn hơn cho tiêu đề
            holder.nameTextView.setTypeface(null, Typeface.BOLD); // Đặt chữ in đậm
            holder.descriptionTextView.setVisibility(View.GONE); // Ẩn mô tả
            holder.imageView.setVisibility(View.GONE); // Ẩn hình ảnh
        } else {
            // Nếu không phải là tiêu đề
            holder.nameTextView.setText(bienBao.getName());
            holder.nameTextView.setTextSize(16); // Kích thước chữ bình thường
            holder.nameTextView.setTypeface(null, Typeface.BOLD); // Đặt chữ in đậm
            // Hiển thị hoặc ẩn mô tả tùy thuộc vào trường `des`
            if (bienBao.getDes() == null || bienBao.getDes().isEmpty()) {
                holder.descriptionTextView.setVisibility(View.GONE); // Ẩn mô tả nếu rỗng
            } else {
                holder.descriptionTextView.setText(bienBao.getDes());
                holder.descriptionTextView.setVisibility(View.VISIBLE); // Hiện mô tả nếu có
            }

            // Hiển thị hình ảnh nếu trường `imagePath` không phải là "bienbao.png"
            if (bienBao.getImagePath() != null && !bienBao.getImagePath().equals("bienbao.png")) {
                holder.imageView.setVisibility(View.VISIBLE); // Hiện hình ảnh

                // Sử dụng Glide để tải hình ảnh từ URL
                Glide.with(context)
                        .load(bienBao.getImagePath())
                        .placeholder(R.drawable.bienbao) // Hình ảnh mặc định khi đang tải
                        .error(R.drawable.bienbao) // Hình ảnh khi có lỗi tải
                        .into(holder.imageView);
            } else {
                holder.imageView.setVisibility(View.GONE); // Ẩn hình ảnh nếu không có URL hoặc là hình mặc định
            }
        }
    }



    @Override
    public int getItemCount() {
        return bienBaoList.size();
    }

    public static class BienBaoViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ImageView imageView;

        public BienBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_name);
            descriptionTextView = itemView.findViewById(R.id.text_description);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
