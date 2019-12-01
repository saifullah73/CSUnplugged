package com.example.saif.tenbigideas;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PostManager {
    private Context c;


    private PostManager(Context c){
        this.c = c;
    }


    public Post getPost(int position){
        if (position<=0){
            Log.i("PostMake","null");
            return null;
        }
        else {
            PostBuilder postBuilder;
            Post post;
            switch (position){
                case 1:
                    Log.i("PostMake","in 1");
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post1_t));
                    postBuilder.setContent(c.getString(R.string.post1_c));
                    postBuilder.setImageId(R.drawable.post1_i);
                    post = postBuilder.build();
                    break;
                case 2:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post2_t));
                    postBuilder.setContent(c.getString(R.string.post2_c));
                    postBuilder.setImageId(R.drawable.post2_i);
                    post = postBuilder.build();
                    break;
                case 3:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post3_t));
                    postBuilder.setContent(c.getString(R.string.post3_c));
                    postBuilder.setImageId(R.drawable.post3_i);
                    post = postBuilder.build();
                    break;
                case 4:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post4_t));
                    postBuilder.setContent(c.getString(R.string.post4_c));
                    postBuilder.setImageId(R.drawable.post2_i);
                    post = postBuilder.build();
                    break;
                case 5:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post5_t));
                    postBuilder.setContent(c.getString(R.string.post5_c));
                    postBuilder.setImageId(R.drawable.post5_i);
                    post = postBuilder.build();
                    break;
                case 6:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post6_t));
                    postBuilder.setContent(c.getString(R.string.post6_c));
                    postBuilder.setImageId(R.drawable.post6_i);
                    post = postBuilder.build();
                    break;
                case 7:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post7_t));
                    postBuilder.setContent(c.getString(R.string.post7_c));
                    postBuilder.setImageId(R.drawable.post7_i);
                    post = postBuilder.build();
                    break;
                case 8:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post8_t));
                    postBuilder.setContent(c.getString(R.string.post8_c));
                    postBuilder.setImageId(R.drawable.post8_i);
                    post = postBuilder.build();
                    break;
                case 9:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post9_t));
                    postBuilder.setContent(c.getString(R.string.post9_c));
                    postBuilder.setImageId(R.drawable.post9_i);
                    post = postBuilder.build();
                    break;
                case 10:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post10_t));
                    postBuilder.setContent(c.getString(R.string.post10_c));
                    postBuilder.setImageId(R.drawable.post10_i);
                    post = postBuilder.build();
                    break;
                default:
                    postBuilder = new PostBuilder();
                    postBuilder.createPost();
                    postBuilder.setTitle(c.getString(R.string.post1_t));
                    post = postBuilder.build();
                    break;
            }
            return post;
        }
    }

    public List<PostListItem> getPostList(int limit){
        List<PostListItem> postListItems = new ArrayList<>();
        if (limit > 0) {
            if (limit >=10) {
                for (int i = 1; i <= 10; i++) {
                    Post post = getPost(i);
                    PostListItem postListItem = convertPostToPostListItem(post);
                    postListItems.add(postListItem);

                }
            } else {
                for (int i = 1; i <= limit; i++) {
                    Post post = getPost(i);
                    PostListItem postListItem = convertPostToPostListItem(post);
                    postListItems.add(postListItem);
                }
            }
            return postListItems;
        }
        else{
            return postListItems;
        }
    }

    private PostListItem convertPostToPostListItem(Post post){
        return  new PostListItem(post.title,post.imageId);
    }



    public static class PostManagerFactory{
        public enum Type{LIST,FULL}
        public static PostManager createPostManager(Type t){
            PostManager postManager;
            if (t == Type.LIST) {
                postManager = new PostManager(PostListActivity.getInstance());
            }
            else if (t == Type.FULL){
                postManager = new PostManager(PostActivity.getInstance());
            }
            else{
                postManager = new PostManager(PostListActivity.getInstance());
            }
            return postManager;
        }
    }
}
