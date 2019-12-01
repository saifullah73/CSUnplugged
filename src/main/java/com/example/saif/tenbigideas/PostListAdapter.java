package com.example.saif.tenbigideas;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListItemViewHolder> {

    private List<PostListItem> viewItemList;

    public PostListAdapter(List<PostListItem> viewItemList) {
        this.viewItemList = viewItemList;
    }



    @Override
    public PostListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item_view, parent, false);
        return new PostListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostListItemViewHolder holder, final int position) {
        if(viewItemList!=null) {
            // Get item dto in list.
            PostListItem viewItem = viewItemList.get(position);

            if(viewItem != null) {
                holder.textView.setText(viewItem.getTitle());
                Glide.with(PostListActivity.getInstance()).load(viewItem.getImageId()).placeholder(R.drawable.placeholder).into(holder.imageView);
//                holder.imageView.setImageResource(viewItem.getImageId());
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PostListActivity.getInstance().goToPost(position);
                    }
                });

            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(viewItemList!=null)
        {
            ret = viewItemList.size();
        }
        return ret;
    }

    public class PostListItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView = null;
        private TextView textView = null;
        private CardView cardView = null;

        public PostListItemViewHolder(View itemView) {
            super(itemView);

            if(itemView != null)
            {
                cardView = (CardView) itemView.findViewById(R.id.main_blog_post);
                imageView = (ImageView)itemView.findViewById(R.id.blog_image);
                textView = (TextView) itemView.findViewById(R.id.blog_desc);
            }
        }

    }
}