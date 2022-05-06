package com.example.hab_reboen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hab_reboen.registration.RegistrationActivity;
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
                Intent intent2main = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent2main);
                break;
            case R.id.button_create_account:
                Intent intent2reg = new Intent(StartActivity.this, RegistrationPasswordActivity.class);
                startActivity(intent2reg);
                break;
            case R.id.skip_button:

                myRef.child("User").push().setValue("fuuf");

//                Intent intent2skip = new Intent(StartActivity.this, ChooseHabitActivity.class);
//                startActivity(intent2skip);
                break;
        }
    }
}