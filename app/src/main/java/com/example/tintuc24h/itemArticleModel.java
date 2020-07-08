package com.example.tintuc24h;

public class itemArticleModel {
    public String title, nameArticle, time;
    public String image;
    public String link;

    public itemArticleModel(String title, String image, String nameArticle, String time, String link) {
        this.title = title;
        this.image = image;
        this.nameArticle = nameArticle;
        this.time = time;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
