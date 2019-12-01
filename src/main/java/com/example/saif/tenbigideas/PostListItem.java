package com.example.saif.tenbigideas;

public class PostListItem {
    String title;
    int imageId;

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

    public PostListItem(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }
}
