package com.example.tintuc24h.Adapters;

import android.app.ActionBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tintuc24h.TopicArticleModel;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();
//    private final List<TopicArticleModel> models = new ArrayList<>();

    public PagerAdapter (FragmentManager fm){
        super(fm);
    }
    @Override
    public int getCount() {
        return titleList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

        public void AddFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            titleList.add(title);
    }
}
