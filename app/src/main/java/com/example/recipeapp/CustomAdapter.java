package com.example.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.details.RecipeDetailsActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList recipeId, recipeName, recipeDescription;
    CustomAdapter(Context context,
                  ArrayList recipeId,
                  ArrayList recipeName,
                  ArrayList recipeDescription){
        this.context = context;
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_recipe, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtRecipeId.setText(String.valueOf(recipeId.get(position)));
        holder.txtRecipeName.setText(String.valueOf(recipeName.get(position)));
        holder.txtRecipeDescription.setText(String.valueOf(recipeDescription.get(position)));
    }

    @Override
    public int getItemCount() {
        return recipeId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtRecipeId, txtRecipeName, txtRecipeDescription;
        LinearLayout itemRecipeList;
        ImageButton btnShare;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            btnShare = itemView.findViewById(R.id.btnShare);
            txtRecipeId = itemView.findViewById(R.id.txt_recipe_id);
            txtRecipeName = itemView.findViewById(R.id.txt_recipe_name);
            txtRecipeDescription = itemView.findViewById(R.id.txt_recipe_description);
            itemRecipeList = itemView.findViewById(R.id.item_recipe_list);
            itemRecipeList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RecipeDetailsActivity.class);
                    intent.putExtra("id", txtRecipeId.getText().toString());
                    intent.putExtra("name", txtRecipeName.getText().toString());
                    intent.putExtra("description", txtRecipeDescription.getText().toString());
                    context.startActivity(intent);
                }
            });
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareSub = txtRecipeName.getText().toString();
                    String shareBody = txtRecipeDescription.getText().toString();
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    context.startActivity(Intent.createChooser(shareIntent, "Share using: "));
                }
            });
        }


    }
}
