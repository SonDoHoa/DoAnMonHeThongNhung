package com.example.tintuc24h;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class TopicArticleModel extends ArrayList<TopicArticleModel> {
    Fragment fragment;
    String title;
    boolean isTopic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicArticleModel(Fragment fragment, boolean isTopic, String title) {
        this.fragment = fragment;
        this.isTopic = isTopic;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isTopic() {
        return isTopic;
    }

    public void setTopic(boolean topic) {
        isTopic = topic;
    }
}
