package com.example.saif.tenbigideas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostListActivity extends AppCompatActivity {

    private static PostListActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        instance = this;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Ten Big Ideas");

        PostManager postManager = PostManager.PostManagerFactory.createPostManager(PostManager.PostManagerFactory.Type.LIST);
        List<PostListItem> postList = postManager.getPostList(10);



        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.post_list_recycler_view);
        // Create the linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // Use above layout manager for RecyclerView..
        recyclerView.setLayoutManager(linearLayoutManager);

        // Create recycler view data adapter with item list.
        PostListAdapter dataAdapter = new PostListAdapter(postList);
        // Set RecyclerView data adapter.
        recyclerView.setAdapter(dataAdapter);

    }

    public static PostListActivity getInstance(){
        if (instance == null){
            instance = new PostListActivity();
        }
        return instance;
    }


    public void goToPost(int position){
        Intent i = new Intent(getInstance(),PostActivity.class);
        i.putExtra("pos",position+1);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_post_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                Intent i = new Intent(PostListActivity.getInstance(),Login.class);
                startActivity(i);
                finish();
                break;
            case R.id.exit:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

}
