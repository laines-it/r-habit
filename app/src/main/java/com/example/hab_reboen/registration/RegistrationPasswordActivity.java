package com.example.hab_reboen.registration;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RegistrationPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_email;
    EditText pass1;
    EditText pass2;
    Button next_button;
    TextView text;
    FirebaseAuth myAuth;
    DatabaseReference myRef;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_pass);

        text = findViewById(R.id.text_message_2);
        edit_email = findViewById(R.id.email_edit);
        pass1 = findViewById(R.id.password);
        pass2 = findViewById(R.id.password_again);
        next_button = findViewById(R.id.finishpass);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://r-habits-dcdce-default-rtdb.firebaseio.com");
        myRef = database.getReference();
        myAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                }else{
                    Log.d(TAG,"onAuthStateChanged:signed_out");
                }
            }
        };
        next_button.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuthListener != null) {
            myAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            myAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.finishpass) {
            String email = edit_email.getText().toString();
            String p1 = pass1.getText().toString();
            String p2 = pass2.getText().toString();
            if((!email.equals(""))&&(!p1.equals(""))&&(p1.equals(p2))){
                myAuth.createUserWithEmailAndPassword(email,p1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "signInWithCustomToken:success");
                        Toast.makeText(RegistrationPasswordActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = myAuth.getCurrentUser();

                    }else{
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                        Toast.makeText(RegistrationPasswordActivity.this, "Error !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent = new Intent(RegistrationPasswordActivity.this, RegistrationActivity.class);
                intent.putExtra("keyemail",email);
                startActivity(intent);
            }else{
                text.setText("Пароли не совпадают");
                pass2.setText("");
            }

        }

    }
}
