package com.example.recipeapp.details;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class RecipeDetailsViewPagerAdapter extends FragmentStateAdapter {
    public RecipeDetailsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new RecipeDetailsFragment();
            case 1:
                return new RecipeCommentsFragment();
            default:
                return new RecipeDetailsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
