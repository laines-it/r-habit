package com.example.hab_reboen.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.R;
import com.example.hab_reboen.supports.CrossActivity;

public class ChooseHabitActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView button;
    TextView nameofhabit;
    TextView des_habit;
    HorizontalScrollView choose_area;
    ImageView re;
    ImageView dr;
    ImageView lan;
    private String sel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit);
        getSupportActionBar().hide();
        button = findViewById(R.id.choose_habit_button);
        button.setOnClickListener(this);
        des_habit = findViewById(R.id.description_habit);
        nameofhabit = findViewById(R.id.name_of_habit);
        choose_area = findViewById(R.id.choose_area);
        re = findViewById(R.id.readinghabitchoose);
        dr = findViewById(R.id.drawinghabitchoose);
        lan = findViewById(R.id.languagehabitchoose);
        re.setOnClickListener(this);
        dr.setOnClickListener(this);
        lan.setOnClickListener(this);
        sel = "Nothing";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.readinghabitchoose:
                nameofhabit.setText("Reading");
                des_habit.setText("read some pages per day");
                sel = "Reading";
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.drawinghabitchoose:
                nameofhabit.setText("Drawing");
                des_habit.setText("draw/sketch");
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.languagehabitchoose:
                nameofhabit.setText("Language");
                des_habit.setText("learn languages");
                button.setVisibility(View.VISIBLE);
                break;
            case R.id.choose_habit_button:
                if(nameofhabit.equals("")){
                    Toast.makeText(this,"Choose a habit",Toast.LENGTH_SHORT).show();
                }else{
                    if(sel.equals("Reading")){
//                        Intent intent2cal = new Intent(ChooseHabitActivity.this, HabitCalendarActivity.class);
//                        intent2cal.putExtra("keyID",getIntent().getIntExtra("keyID",0));
//                        intent2cal.putExtra("namehabit", (CharSequence) nameofhabit);
//                        intent2cal.putExtra("deshabit", (CharSequence) des_habit);

                        Intent i2c = new Intent(ChooseHabitActivity.this, CrossActivity.class);
                        i2c.putExtra("keyID",getIntent().getIntExtra("keyID",0));
                        startActivity(i2c);
                    }else{
                        Toast.makeText(this,"We are working on it",Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
}