package com.example.recipeapp.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.recipeapp.R;

public class RecipeDetailsFragment extends Fragment {
    TextView txtRecipeDetailsId, txtRecipeDetailsName, txtRecipeDetailsDescription;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        txtRecipeDetailsId = (TextView) inflatedView.findViewById(R.id.txt_recipe_details_id);
        txtRecipeDetailsName = (TextView) inflatedView.findViewById(R.id.txt_recipe_details_name);
        txtRecipeDetailsDescription = (TextView) inflatedView.findViewById(R.id.txt_recipe_details_description);


        txtRecipeDetailsId.setText(getActivity().getIntent().getExtras().getString("id"));
        txtRecipeDetailsName.setText(getActivity().getIntent().getExtras().getString("name"));
        txtRecipeDetailsDescription.setText(getActivity().getIntent().getExtras().getString("description"));
        return inflatedView;
    }
}