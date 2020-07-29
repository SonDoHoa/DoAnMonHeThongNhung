package com.example.tintuc24h;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class TopicArticleModel implements Serializable {
    String title;
    boolean isTopic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicArticleModel( boolean isTopic, String title) {
        this.isTopic = isTopic;
        this.title = title;
    }

    public boolean isTopic() {
        return isTopic;
    }

    public void setTopic(boolean topic) {
        isTopic = topic;
    }
}
