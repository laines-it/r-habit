package com.example.hab_reboen.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.HabitActivity;
import com.example.hab_reboen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseHabitActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    TextView nameofhabit;
    HorizontalScrollView choose_area;
    ImageView re;
    ImageView mu;
    ImageView sc;
    ImageView ha;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit);
        getSupportActionBar().hide();
        button = findViewById(R.id.select_new_habit);
        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(this);

        nameofhabit = findViewById(R.id.name_of_habit);
        choose_area = findViewById(R.id.choose_area);
        re = findViewById(R.id.readinghabitchoose);
        mu = findViewById(R.id.musichabitchoose);
        sc = findViewById(R.id.sciencehabitchoose);
        ha = findViewById(R.id.handicrafthabitchoose);
        re.setOnClickListener(this);
        mu.setOnClickListener(this);
        sc.setOnClickListener(this);
        ha.setOnClickListener(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://r-habits-dcdce-default-rtdb.firebaseio.com");
        myRef = database.getReference();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.readinghabitchoose:
                nameofhabit.setText("Reading");
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.musichabitchoose:
                nameofhabit.setText("Music");
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.sciencehabitchoose:
                nameofhabit.setText("Science");
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.handicrafthabitchoose:
                nameofhabit.setText("Handicraft");
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.select_new_habit:
                Intent intent2main = new Intent(ChooseHabitActivity.this, HabitActivity.class);
                startActivity(intent2main);
                break;
        }
    }
}