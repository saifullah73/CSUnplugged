package com.example.saif.tenbigideas;

public class PostBuilder{
    private Post post;

    public void createPost(){
        post = new Post();
    }

    public void setTitle(String title) {
        post.title = title;
    }

    public void setImageId(int imageId) {
        post.imageId = imageId;
    }

    public void setContent(String content) {
        post.content = content;
    }

    public Post build(){
        return post;
    }
}
