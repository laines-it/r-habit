package com.example.hab_reboen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.hab_reboen.supports.Quote;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ChooseQuoteProfile extends DialogFragment{
    private static final String TAG = "ChooseQuoteProfile";
    DatabaseReference mRef;
    LinearLayout lr;
    String[] data = new String[4];
    public interface OnInputListener{
        void sendInput(Quote input);
    }
    public OnInputListener mOnInputListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRef = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_choose_quote_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lr = getView().findViewById(R.id.place_for_quotes_to_choose);
        mRef.child("Quotes").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()){
                    for(int i = 1; i <= Integer.parseInt(s.getKey()); i++){
                        mRef.child("Quotes").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                            @SuppressLint("ResourceAsColor")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot s : snapshot.getChildren()) {
                                    Quote q = s.getValue(Quote.class);

                                    LinearLayout parent = new LinearLayout(getContext());
                                    LinearLayout.LayoutParams lpparent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    lpparent.setMargins(0, dp2px(20), dp2px(20), dp2px(20));
                                    parent.setWeightSum(100);
                                    parent.setOrientation(LinearLayout.VERTICAL);
                                    parent.setBackgroundResource(R.drawable.border);
                                    lr.addView(parent);

                                    TextView textquote = new TextView(getContext());
                                    LinearLayout.LayoutParams textpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 33);
                                    textpar.setMargins(dp2px(10), dp2px(10), dp2px(10), 0);
                                    textquote.setLayoutParams(textpar);
                                    textquote.setTextColor(Color.parseColor("#000000"));
                                    textquote.setTypeface(getResources().getFont(R.font.gareta));
                                    textquote.setText(q.getText());
                                    parent.addView(textquote);

                                    LinearLayout lp = new LinearLayout(getContext());
                                    LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 66);
                                    lp.setWeightSum(100);
                                    lp.setOrientation(LinearLayout.HORIZONTAL);
                                    parent.addView(lp);

                                    LinearLayout author_layout = new LinearLayout(getContext());
                                    LinearLayout.LayoutParams alp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 30);
                                    alp.setMargins(dp2px(5), 0, dp2px(5), 0);
                                    author_layout.setOrientation(LinearLayout.VERTICAL);
                                    author_layout.setBackgroundResource(R.drawable.back_in_black_dark);
                                    author_layout.setLayoutParams(alp);
                                    lp.addView(author_layout);

                                    TextView name_author = new TextView(getContext());
                                    name_author.setTextColor(Color.parseColor("#ffffff"));
                                    name_author.setText(q.getAuthor());
                                    LinearLayout.LayoutParams in_alp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 50);
                                    TextView about_author = new TextView(getContext());
                                    about_author.setTextColor(Color.parseColor("#ffffff"));
                                    String about = q.getCountry() + ", " + q.getYear();
                                    about_author.setText(about);
                                    name_author.setLayoutParams(in_alp);
                                    about_author.setLayoutParams(in_alp);
                                    name_author.setTypeface(getResources().getFont(R.font.gillsans));
                                    about_author.setTypeface(getResources().getFont(R.font.gillsans));
                                    author_layout.addView(name_author);
                                    author_layout.addView(about_author);

                                    ImageView rank = new ImageView(getContext());
                                    LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 70);
                                    rank.setLayoutParams(rlp);
                                    int r = q.getRank();
                                    switch (r) {
                                        case (1):
                                            rank.setImageResource(R.color.rank1);
                                            break;
                                        case (2):
                                            rank.setImageResource(R.color.rank2);
                                            break;
                                        case (3):
                                            rank.setImageResource(R.color.rank3);
                                            break;
                                        case (4):
                                            rank.setImageResource(R.color.rank4);
                                            break;
                                        case (5):
                                            rank.setImageResource(R.color.rank5);
                                            break;
                                    }
                                    lp.addView(rank);
                                    parent.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mOnInputListener.sendInput(q);
                                            Objects.requireNonNull(getDialog()).dismiss();
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private int dp2px(int dp){
          return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ChooseQuoteProfile.this.getResources().getDisplayMetrics()));
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG,"onAttach: ClassCastException: " + e.getMessage());
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}