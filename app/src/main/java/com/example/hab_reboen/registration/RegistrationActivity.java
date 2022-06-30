package com.example.hab_reboen.registration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.R;
import com.example.hab_reboen.supports.for_user.HiddenInfo;
import com.example.hab_reboen.supports.for_user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText city;
    EditText date;
    TextView text;
    Spinner spinner;
    CheckBox sex_hide;
    CheckBox date_hide;
    CheckBox city_hide;
    DatabaseReference myRef;
    Button button;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        date = findViewById(R.id.date_of_birth_edit);
         name = findViewById(R.id.name_edit);
         date.addTextChangedListener(new TextWatcher() {
             private String current = "";
             private final Calendar cal = Calendar.getInstance();
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @SuppressLint("DefaultLocale")
             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if (!s.toString().equals(current)) {
                     String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                     String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                     int cl = clean.length();
                     int sel = cl;
                     for (int i = 2; i <= cl && i < 6; i += 2) {
                         sel++;
                     }
                     //Fix for pressing delete next to a forward slash
                     if (clean.equals(cleanC)) sel--;

                     if (clean.length() < 8){
                         String ddmmyyyy = "DDMMYYYY";
                         clean = clean + ddmmyyyy.substring(clean.length());
                     }else{
                         //This part makes sure that when we finish entering numbers
                         //the date is correct, fixing it otherwise
                         int day  = Integer.parseInt(clean.substring(0,2));
                         int mon  = Integer.parseInt(clean.substring(2,4));
                         int year = Integer.parseInt(clean.substring(4,8));

                         mon = mon < 1 ? 1 : Math.min(mon, 12);
                         cal.set(Calendar.MONTH, mon-1);
                         year = (year<1900)?1900: Math.min(year, 2100);
                         cal.set(Calendar.YEAR, year);
                         // ^ first set year for the line below to work correctly
                         //with leap years - otherwise, date e.g. 29/02/2012
                         //would be automatically corrected to 28/02/2012

                         day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                         clean = String.format("%02d%02d%02d",day, mon, year);
                     }

                     clean = String.format("%s/%s/%s", clean.substring(0, 2),
                             clean.substring(2, 4),
                             clean.substring(4, 8));

                     sel = Math.max(sel, 0);
                     current = clean;
                     date.setText(current);
                     date.setSelection(Math.min(sel, current.length()));
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });
         spinner = findViewById(R.id.sex_spinner);
         text = findViewById(R.id.text_message);
         button = findViewById(R.id.save_changes_profile);
         city = findViewById(R.id.city_edit);
        button.setOnClickListener((View.OnClickListener) this);
        sex_hide = findViewById(R.id.sex_is_hide);
        date_hide = findViewById(R.id.date_of_birth_is_hide);
        city_hide = findViewById(R.id.city_is_hide);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://r-habits-dcdce-default-rtdb.firebaseio.com");
        myRef = database.getReference();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_changes_profile) {
            String entered_name = name.getText().toString();
            String entered_city = city.getText().toString();
            String entered_date = date.getText().toString();
            String[] date_array = entered_date.split("\\.");
            boolean errorindate = (date_array.length == 0);
            String selected_sex = spinner.getSelectedItem().toString();
            if (entered_name.equals("") | entered_city.equals("") | entered_date.equals("") | selected_sex.equals("Sex")) {
                text.setText("Заполнены не все поля");
            } else {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                myRef.child("User").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot s : snapshot.getChildren()){
                            int id = Integer.parseInt(s.getKey())+1;
                            User user = null;
                            String email = getIntent().getStringExtra("keyemail");
                            try {
                                int sex = 0;
                                    if(selected_sex.equals("Male")){
                                         sex = 1;

                                    }else{
                                        if(selected_sex.equals("Female")){
                                             sex = 2;
                                        }
                                    }
                                HiddenInfo hiddenInfo = new HiddenInfo(sex_hide.isChecked(),date_hide.isChecked(),city_hide.isChecked());
                                user = new User(id,entered_name,email,entered_city,formatter.parse(entered_date),sex,hiddenInfo);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            System.out.println(id);
                            myRef.child("User").child(String.valueOf(id)).push().setValue(user);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent intent = new Intent(RegistrationActivity.this, ChooseHabitActivity.class);

                startActivity(intent);
            }
        }
    }
}