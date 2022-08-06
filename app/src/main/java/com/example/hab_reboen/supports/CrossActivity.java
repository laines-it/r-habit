package com.example.hab_reboen.supports;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.FindPartner;
import com.example.hab_reboen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrossActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed;
    Button b;
    CheckBox ch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross);
        ed = findViewById(R.id.editText);
        b = findViewById(R.id.go);
        ch = findViewById(R.id.checkBox);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.go){
            if (ed.getText().equals("")){
                Toast.makeText(this,"Choose a habit",Toast.LENGTH_SHORT).show();
            }else{
                String habit = "Reading";
                Integer id = getIntent().getIntExtra("keyID",0);
                String value = String.valueOf(ed.getText());
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("WaitingRoom").child(habit).child(value).push().setValue(id);
                Intent i2find = new Intent(CrossActivity.this, FindPartner.class);
                i2find.putExtra("keyHabit",habit);
                i2find.putExtra("keyValue",value);
                i2find.putExtra("keyID",id);
                startActivity(i2find);
            }
        }
    }
}
