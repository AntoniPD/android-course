package com.example.android_lesson_6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_lesson_6.R;
import com.example.android_lesson_6.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends BaseAdapter {

    private Context context;
    private List<Post> posts = new ArrayList<>();

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void addPosts(List<Post> newPosts) {
        posts.addAll(newPosts);
    }

    public void addPost(Post post) {
        posts.add(post);
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return posts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(context)
                    .inflate(R.layout.activity_list_item, parent, false);
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        Post post = posts.get(position);
        holderView.photoImage.setImageResource(post.getPhotoUrl());
        holderView.titleText.setText(post.getTitle());
        holderView.contentText.setText(post.getContent());

        return convertView;
    }

}
