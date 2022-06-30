package com.example.hab_reboen;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.supports.HabitForProgress;
import com.example.hab_reboen.supports.for_user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    DatabaseReference mDb;
    FrameLayout proglayout;
    int current_habit;
    ArrayList<User> listdata;
    ArrayList<HabitForProgress> habits;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        int progress = 60;
        habits = new ArrayList<HabitForProgress>(){};
        listdata = new ArrayList<User>();
        mDb = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        mDb.child("User").child(intent.getStringExtra("keyUserID")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listdata.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        User user1 = ds.getValue(User.class);
                        listdata.add(user1);
                    }
                    for (User us : listdata){
                        TextView name_of_user = findViewById(R.id.name_of_user);
                        name_of_user.setText(us.getName());
                        TextView date_of_birth = findViewById(R.id.date_of_birth);

                        TextView city_user = findViewById(R.id.city_users);
                        city_user.setText(us.getCity());
                    }
            }
        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        habits.add(new HabitForProgress("Чтение",8,"Nik_sila","2 дня"));
        habits.add(new HabitForProgress("Скетчинг",67,"Один","12 дней"));
        habits.add(new HabitForProgress("Бег по утрам",34,"anass20","5 дней"));
        habits.add(new HabitForProgress("Подготовка к ЕГЭ",97,"Lom0s","26 дней"));
        current_habit = 0;
        proglayout = findViewById(R.id.prog_layout);
        proglayout.setOnTouchListener(new OnSwipeTouchListener(ProfileActivity.this) {
            @Override
            public void onSwipeLeft(){
                if(current_habit >= habits.size()-1){
                    current_habit = 0;
                    update_progress_habit(current_habit);
                }else{
                    current_habit++;
                    update_progress_habit(current_habit);
                }
            }
            @Override
            public void onSwipeRight(){
                if(current_habit <= 0){
                    current_habit = habits.size()-1;
                    update_progress_habit(current_habit);
                }else{
                    current_habit--;
                    update_progress_habit(current_habit);
                }
            }
        });
        update_progress_habit(current_habit);

    }
    private void updateProgressBar(int progress){
        ProgressBar pr = findViewById(R.id.progress_circular);
        ObjectAnimator aniprogress = ObjectAnimator.ofInt(pr,"progress",0,progress);
        aniprogress.setDuration(1000);
        aniprogress.setInterpolator(new DecelerateInterpolator());
        aniprogress.start();
        pr.clearAnimation();
    }
    public void update_progress_habit(int current_habit){
        HabitForProgress habit = habits.get(current_habit);
        TextView prog_text = findViewById(R.id.progress_text);
        prog_text.setText(habit.getProgress() + "%");
        TextView name_of_habit = findViewById(R.id.name_of_habit);
        name_of_habit.setText(habit.getName());
        TextView name_of_partner = findViewById(R.id.name_of_partner);
        name_of_partner.setText(habit.getName_of_partner());
        TextView time = findViewById(R.id.time_of_habit);
        time.setText(habit.getTime());
        ImageView part_or_alone = findViewById(R.id.icon_partner_or_alone);
        if (habit.getName_of_partner().equals("Один")){
            part_or_alone.setImageResource(R.drawable.account);
        }else{
            part_or_alone.setImageResource(R.drawable.partnericon);
        }
        updateProgressBar(habit.getProgress());
    }
}