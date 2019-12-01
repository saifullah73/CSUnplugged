package com.example.saif.tenbigideas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {

    private static PostActivity instance;
    private int position;
    private ImageView imageView;
    private TextView titleV, contentV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent i = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (i!=null){
            position = i.getIntExtra("pos",1);
        }
        instance = this;
        setPostview();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static PostActivity getInstance(){
        return instance;
    }


    private void setPostview(){
        imageView = (ImageView) findViewById(R.id.post_image);
        contentV = (TextView) findViewById(R.id.post_content);
        titleV = (TextView) findViewById(R.id.post_title);
        PostManager postManager = PostManager.PostManagerFactory.createPostManager(PostManager.PostManagerFactory.Type.FULL);
        Post post = postManager.getPost(position);

        if (post.getImageId() != 0){
            imageView.setImageResource(post.getImageId());
        }
        else{
            imageView.setImageResource(R.drawable.placeholder);
        }
        if (post.getTitle() != null){
            titleV.setText(post.getTitle());
        }
        else{
            titleV.setVisibility(View.GONE);
        }
        if(post.getContent() != null){
            String string = post.getContent();
            final SpannableString ss = new SpannableString(string);
            ss.setSpan(new RelativeSizeSpan(3f), 0, 1, 0);
            contentV.setText(ss);
        }
        else{
            contentV.setVisibility(View.GONE);
        }
    }

}
