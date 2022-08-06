package com.example.hab_reboen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hab_reboen.supports.for_user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.florent37.shapeofview.shapes.RoundRectView;

public class FindPartner extends AppCompatActivity {

    LinearLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_partner);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("WaitingRoom").child(getIntent().getStringExtra("keyHabit")).addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Integer[]> partners = new ArrayList();
                String myOffer = getIntent().getStringExtra("keyValue");
                for(DataSnapshot s : snapshot.getChildren()){
                    int offer = Integer.parseInt(String.valueOf(s.getKey()));

//                    Log.e("CHECK",String.valueOf(s.getValue()).split("=")[1].split(("}"))[0]); //FOR id :
//                    System.out.println(String.valueOf(s.getValue()).split("=")[1].split(Pattern.quote("}"))[0].getClass().getName());
//                    Log.e("CHECK",String.valueOf(s.getValue()).split("=")); //FOR id :
                    for(String id_str : String.valueOf(s.getValue()).split(",")){
                        int id = Integer.parseInt(id_str.split("=")[1].replaceAll(Pattern.quote("}"), ""));
                        if(offer>0 && id!=getIntent().getIntExtra("keyID",0)){
                            Integer[] u = new Integer[3];
                            u[0] = Math.abs(offer-Integer.parseInt(myOffer));
                            u[1] = offer;
                            u[2] = id;
                            partners.add(u);
                        }
                    }

                }
                partners.sort(new Comparator<Integer[]>() {
                    @Override
                    public int compare(Integer[] i1, Integer[] i2) {
                        return i1[0].compareTo(i2[0]);
                    }
                });

                l = findViewById(R.id.place_for_found_partners);
                l.setWeightSum(100);
                Log.e("COUNT_OF_IDS", String.valueOf(partners.size()));
                for(Integer[] p : partners){
                    myRef.child("User").child(String.valueOf(p[2])).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot s : snapshot.getChildren()){
                                Log.e("ID",String.valueOf(p[2]));
                                User user = s.getValue(User.class);

                                //Parent
                                RoundRectView place_parent = new RoundRectView(FindPartner.this);
                                LinearLayout parent = new LinearLayout(FindPartner.this);
                                parent.setOrientation(LinearLayout.HORIZONTAL);
                                place_parent.addView(parent);
                                l.addView(place_parent);

                                //Profile image
                                CircleImageView icon = new CircleImageView(FindPartner.this);
                                LinearLayout.LayoutParams circle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,75);
                                circle.setMargins(dp2px(10),dp2px(10),dp2px(10),dp2px(10));
                                icon.setLayoutParams(circle);
                                parent.addView(icon);


                                //Info
                                RoundRectView place_info = new RoundRectView(FindPartner.this);
                                place_info.setBackgroundColor(R.color.back_in_black_dark);
                                place_info.setBottomLeftRadius(dp2px(10));
                                place_info.setBottomRightRadius(dp2px(10));
                                place_info.setTopLeftRadius(dp2px(10));
                                place_info.setTopRightRadius(dp2px(10));
                                LinearLayout right = new LinearLayout(FindPartner.this);
                                LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,25);
                                rp.setMargins(dp2px(20),dp2px(20),dp2px(20),dp2px(20));
                                right.setWeightSum(100);
                                right.setLayoutParams(rp);

                                //Personal info
                                LinearLayout about = new LinearLayout(FindPartner.this);
                                LinearLayout.LayoutParams aboutp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,20);
                                about.setLayoutParams(aboutp);
                                about.setOrientation(LinearLayout.VERTICAL);
                                about.setWeightSum(50);
                                TextView name = new TextView(FindPartner.this);
                                name.setLayoutParams(rp);
                                name.setText(user.getName());
                                about.addView(name);
                                TextView city = new TextView(FindPartner.this);
                                city.setLayoutParams(rp);
                                city.setText(user.getCity()+p[1]);
                                about.addView(city);

                                //
                                LinearLayout relativeLayout = new LinearLayout(FindPartner.this);
                                LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,80);
                                right.addView(about);
                                right.addView(relativeLayout);
                                place_info.addView(right);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private int dp2px(int dp){
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, FindPartner.this.getResources().getDisplayMetrics()));
    }
}