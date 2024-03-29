package com.example.hab_reboen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.registration.RegistrationPasswordActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_login;
    Button button_create_account;
    Button skipbutton;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();
        button_login = findViewById(R.id.button_login);
        button_create_account = findViewById(R.id.button_create_account);
        skipbutton = findViewById(R.id.skip_button);
        button_login.setOnClickListener((View.OnClickListener) this);
        button_create_account.setOnClickListener((View.OnClickListener) this);
        skipbutton.setOnClickListener((View.OnClickListener) this);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://r-habits-dcdce-default-rtdb.firebaseio.com");
        myRef = database.getReference();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                Intent intent2profile = new Intent(StartActivity.this, ProfileActivity.class);
                intent2profile.putExtra("keyUserID","3");
                startActivity(intent2profile);
                break;
            case R.id.button_create_account:
                Intent intent2reg = new Intent(StartActivity.this, RegistrationPasswordActivity.class);
                startActivity(intent2reg);
                break;
            case R.id.skip_button:
                Intent intent2skip = new Intent(StartActivity.this, HabitCalendarActivity.class);
                startActivity(intent2skip);
                break;
        }
    }
}