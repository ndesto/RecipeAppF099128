package com.example.recipeapp.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Comment;
import com.example.recipeapp.R;

import java.util.ArrayList;

public class CommentsDataAdapter extends RecyclerView.Adapter<CommentsDataAdapter.MyViewHolder> {
    private Context context;
    private ArrayList comments;
    CommentsDataAdapter(Context context, ArrayList comments){
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_comment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comment comment = (Comment) comments.get(position);
        holder.txtCommentId.setText(String.valueOf(comment.getId()));
        holder.txtCommentName.setText(String.valueOf(comment.getName()));
        holder.txtCommentBody.setText(String.valueOf(comment.getBody()));
        holder.txtCommentEmail.setText(String.valueOf(comment.getEmail()));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtCommentId, txtCommentName, txtCommentBody, txtCommentEmail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCommentId = itemView.findViewById(R.id.txt_comment_id);
            txtCommentName = itemView.findViewById(R.id.txt_comment_name);
            txtCommentBody = itemView.findViewById(R.id.txt_comment_body);
            txtCommentEmail = itemView.findViewById(R.id.txt_comment_email);
        }


    }
}
