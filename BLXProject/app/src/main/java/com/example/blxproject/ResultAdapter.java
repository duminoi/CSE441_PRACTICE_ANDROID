package com.example.blxproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private final List<Answer> answers; // Danh sách các câu trả lời
    private final Context context;

    public ResultAdapter(Context context, List<Answer> answers) {
        this.context = context;
        this.answers = answers;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_answer, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Answer answer = answers.get(position);
        holder.txtQuestion.setText("Câu" +(position + 1));

        // Kiểm tra câu trả lời đúng hay sai
        if (answer.isCorrect()) {
            holder.imgResult.setImageResource(R.drawable.right_answer);
        } else {
            holder.imgResult.setImageResource(R.drawable.false_answer);
        }
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuestion;
        ImageView imgResult;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txt_question);
            imgResult = itemView.findViewById(R.id.img_result);
        }
    }
}
