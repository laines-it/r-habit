package com.example.hab_reboen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hab_reboen.databinding.ActivityHabitBinding;
import com.example.hab_reboen.supports.Article;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HabitActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mRef;
    private List<Article> listdata;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHabitBinding binding = ActivityHabitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        DrawerLayout drawer = binding.drawerLayout;



        ArrayList<Drawable> rect_hash = new ArrayList<>();
        rect_hash.add(getResources().getDrawable(R.drawable.hashtag_rect1));
        rect_hash.add(getResources().getDrawable(R.drawable.hashtag_rect2));
        rect_hash.add(getResources().getDrawable(R.drawable.hashtag_rect3));
        rect_hash.add(getResources().getDrawable(R.drawable.hashtag_rect4));
        rect_hash.add(getResources().getDrawable(R.drawable.hashtag_rect5));
        String[] hashs = {"новички","исследование","советы","история","успех"};
        for(String h : hashs){
            RelativeLayout rl = new RelativeLayout(this);
            rl.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 100/hashs.length));
            binding.placeForHashs.addView(rl);

            ImageView imageView = new ImageView(this);
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
////            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
//            imageView.setLayoutParams(lp);
            imageView.setMaxWidth(100/hashs.length);
            System.out.println(100/hashs.length);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageDrawable(rect_hash.get(new Random().nextInt(rect_hash.size())));
//            imageView.setImageDrawable(rect_hash.get(0));

            TextView text_hash = new TextView(this);
            RelativeLayout.LayoutParams lpt = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            lpt.addRule(RelativeLayout.CENTER_IN_PARENT);
            text_hash.setLayoutParams(lpt);
            text_hash.setText("#" + h);
            text_hash.setTextColor(Color.parseColor("#ffffff"));
            text_hash.setTypeface(getResources().getFont(R.font.berlinsansbold));
            rl.addView(imageView);
            rl.addView(text_hash);


        }

        listdata = new ArrayList<Article>();
        ArrayAdapter<Article> adapter = new ArrayAdapter<Article>(this,android.R.layout.simple_list_item_1,listdata);
        mRef = FirebaseDatabase.getInstance().getReference();
        String habit = "Running";
        String article = "sports1";
        String[] articles_info = {"Title","Text","Image"};
        mRef.child("Articles");
        mRef.child(habit);
        mRef.child("Articles").child(habit).addValueEventListener(new ValueEventListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listdata.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Article article1 = ds.getValue(Article.class);
                        listdata.add(article1);
                    }
                    for (Article ar : listdata) {
                        LinearLayout parent = new LinearLayout(HabitActivity.this);
                        LinearLayout.LayoutParams lpparent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lpparent.setMargins(0, (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, HabitActivity.this.getResources().getDisplayMetrics())), 0, 0);
                        parent.setOrientation(LinearLayout.HORIZONTAL);
                        parent.setBackground(getResources().getDrawable((R.drawable.rounded_rect_for_articles)));
//                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) parent.getLayoutParams();
//                        params.addRule(RelativeLayout.ALIGN_PARENT_START);
//                        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//
//                        parent.setLayoutParams(params);
                        parent.setLayoutParams(lpparent);
                        parent.setOrientation(LinearLayout.HORIZONTAL);

                        binding.placeForArticles.addView(parent);
                        ImageView imageView = new ImageView(HabitActivity.this);
                        imageView.setAdjustViewBounds(true);
                        imageView.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, HabitActivity.this.getResources().getDisplayMetrics()));
                        imageView.setMaxHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, HabitActivity.this.getResources().getDisplayMetrics()));
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        Picasso.get().load(ar.getImageres()).into(imageView);

                        LinearLayout layout_with_text = new LinearLayout(HabitActivity.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, HabitActivity.this.getResources().getDisplayMetrics()));
                        layout_with_text.setLayoutParams(lp);
                        layout_with_text.setOrientation(LinearLayout.VERTICAL);
                        layout_with_text.setWeightSum(100);
                        parent.addView(imageView);
                        parent.addView(layout_with_text);

                        TextView title = new TextView(HabitActivity.this);
                        title.setText(ar.getTitle());
                        title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 50));
                        title.setGravity(Gravity.CENTER);
                        title.setBackgroundColor(Color.parseColor("#ffeac8"));
                        title.setTypeface(getResources().getFont(R.font.berlinsansbold));

                        TextView source = new TextView(HabitActivity.this);
                        source.setText(ar.getSource());
                        source.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 50));
                        source.setTypeface(getResources().getFont(R.font.gillsans));
                        source.setGravity(Gravity.CENTER_HORIZONTAL);
                        source.setGravity(Gravity.BOTTOM);

                        layout_with_text.addView(title);
                        layout_with_text.addView(source);

                        parent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(HabitActivity.this,ArticleActivity.class);
                                intent.putExtra("keytitle",title.getText());
                                intent.putExtra("keysource",source.getText());
                                intent.putExtra("keyimage",ar.getImageres());
                                intent.putExtra("keytext",ar.getText());
                                intent.putExtra("keyyear",ar.getYear().toString());
                                startActivity(intent);
                            }
                        });

                        // Hashtags
                        LinearLayout hashs = new LinearLayout(HabitActivity.this);
                        LinearLayout.LayoutParams lphashs = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lphashs.setMargins(0, (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, HabitActivity.this.getResources().getDisplayMetrics())), 0, 0);
                        hashs.setOrientation(LinearLayout.HORIZONTAL);

                        for (String hash : ar.getHashtags()) {
                            TextView h = new TextView(HabitActivity.this);
                            h.setBackground(getResources().getDrawable(R.drawable.rounded_rect_for_hashtags));
                            h.setText("#" + hash);
                            hashs.addView(h);
                        }
                        binding.placeForArticles.addView(hashs);


                    }

                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                    for(int i = 0; i<binding.placeForArticles.getChildCount();i++){
                        binding.placeForArticles.getChildAt(i).setClickable(false);
                    }
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                    for(int i = 0; i<binding.placeForArticles.getChildCount();i++){
                        binding.placeForArticles.getChildAt(i).setClickable(true);
                    }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

//        mRef.child("");
//        Bitmap bitmapImage = BitmapFactory.decodeFile("Your path");
//        int nh = (int) ( bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()) );
//        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);
//        binding.buttonYouAndHimRabbits.setImageBitmap(scaled);
    }

    @Override
    public void onClick(View v) {
        }



}