package com.example.hab_reboen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.databinding.ActivityArticleBinding;
import com.squareup.picasso.Picasso;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityArticleBinding binding = ActivityArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Picasso.get().load(intent.getStringExtra("keyimage")).into(binding.imageOfArticle);
        binding.yearOfArticle.setText(intent.getStringExtra("keyyear"));
        binding.titleOfArticle.setText(intent.getStringExtra("keytitle"));
        binding.sourceOfArticle.setText(intent.getStringExtra("keysource"));
        String[] text_lines = intent.getStringExtra("keytext").split("/n");
        String text = "";
        for (String text_line : text_lines) {text += text_line + "\n";}
        binding.textOfArticle.setText(text);
    }
}