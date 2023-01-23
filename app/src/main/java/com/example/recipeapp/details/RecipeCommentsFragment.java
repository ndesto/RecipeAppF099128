package com.example.recipeapp.details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recipeapp.Comment;
import com.example.recipeapp.CustomAdapter;
import com.example.recipeapp.MainActivity;
import com.example.recipeapp.MyHttpRequestTask;
import com.example.recipeapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeCommentsFragment extends Fragment {
    RecyclerView recyclerViewComments;
    CommentsDataAdapter commentsDataAdapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_comments, container, false);
        recyclerViewComments = view.findViewById(R.id.recycler_view_comments);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        String my_url = "https://jsonplaceholder.typicode.com/comments";
        String myData = "";
        new MyHttpRequestTask(new MyHttpRequestTask.AsyncResponse(){

            @Override
            public void processFinish(String output){
                commentsDataAdapter = new CommentsDataAdapter(getContext(), parse(output));
                recyclerViewComments.setAdapter(commentsDataAdapter);
                recyclerViewComments.setLayoutManager(new LinearLayoutManager(getContext()));
                progressBar.setVisibility(View.GONE);

            }
        }).execute(my_url, myData);
        System.out.println("");
        return view;
    }

    private ArrayList<Comment> parse(String finalResponse){
        ArrayList<Comment> commentArrayList = new ArrayList<>();
        try {
            JSONArray comments = new JSONArray(finalResponse);
            for(int i = 0; i < comments.length(); i++){
                JSONObject comment = comments.getJSONObject(i);
                int id = comment.getInt("id");
                String name = comment.getString("name");
                String email = comment.getString("email");
                String body = comment.getString("body");
                commentArrayList.add(new Comment(id, name, email, body));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return commentArrayList;
    }
}