package com.example.android_lesson_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android_lesson_6.adapter.PostAdapter;
import com.example.android_lesson_6.model.Post;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Post> posts = Arrays.asList(
            new Post(1L, "Football", R.drawable.default_person, "Lets watch the match tonight"),
            new Post(2L, "Volleyball", R.drawable.kaziyski, "Kaziyski is playing againts Modena"),
            new Post(3L, "Darts", R.drawable.darts, "Wooow 16yo won the title")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        PostAdapter postAdapter = new PostAdapter(getBaseContext());
        postAdapter.addPosts(posts);
        listView.setAdapter(postAdapter);
    }
}