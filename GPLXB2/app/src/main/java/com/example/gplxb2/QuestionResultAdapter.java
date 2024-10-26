package com.example.gplxb2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuestionResultAdapter extends RecyclerView.Adapter<QuestionResultAdapter.ViewHolder> {
    private Context context;
    private List<QuestionResult> questionResults;
    private OnItemClickListener onItemClickListener; // Biến để xử lý sự kiện click

    // Interface cho sự kiện click vào câu hỏi
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Constructor
    public QuestionResultAdapter(Context context, List<QuestionResult> questionResults) {
        this.context = context;
        this.questionResults = questionResults;
    }

    // Đặt listener cho sự kiện click
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionResult questionResult = questionResults.get(position);

        // Hiển thị số câu hỏi
        holder.questionText.setText("Câu " + (position + 1));

        // Hiển thị icon đúng/sai
        if (questionResult.isCorrect()) {
            holder.resultIcon.setImageResource(R.drawable.right_answer); // Icon đúng
        } else {
            holder.resultIcon.setImageResource(R.drawable.false_answer); // Icon sai
        }

        // Thiết lập sự kiện khi click vào item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView resultIcon;
        public TextView questionText;

        public ViewHolder(View itemView) {
            super(itemView);
            resultIcon = itemView.findViewById(R.id.result_icon);
            questionText = itemView.findViewById(R.id.question_text);
        }
    }
}



//// QuestionResultAdapter.java
//package com.example.gplxb2;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class QuestionResultAdapter extends RecyclerView.Adapter<QuestionResultAdapter.ViewHolder> {
//    private Context context;
//    private List<QuestionResult> questionResults;
//
//    public QuestionResultAdapter(Context context, List<QuestionResult> questionResults) {
//        this.context = context;
//        this.questionResults = questionResults;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_question_result, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        QuestionResult questionResult = questionResults.get(position);
//
//        // Hiển thị số câu hỏi và văn bản câu hỏi
//        // holder.questionText.setText("Câu " + (position + 1) + ": " + questionResult.getQuestionText());
//            holder.questionText.setText("Câu " + (position + 1));
//
//        // Nếu có icon kết quả (đúng/sai) bạn có thể thêm vào đây
//        if (questionResult.isCorrect()) {
//            holder.resultIcon.setImageResource(R.drawable.right_answer); // Thay bằng icon đúng của bạn
//        } else {
//            holder.resultIcon.setImageResource(R.drawable.false_answer); // Thay bằng icon sai của bạn
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return questionResults.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView resultIcon;
//        public TextView questionText;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            resultIcon = itemView.findViewById(R.id.result_icon);
//            questionText = itemView.findViewById(R.id.question_text);
//        }
//    }
//}
