package com.example.hab_reboen.ui.Running;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hab_reboen.Account;
import com.example.hab_reboen.MainActivity;
import com.example.hab_reboen.R;
import com.example.hab_reboen.databinding.FragmentRunningBinding;

public class RunningFragment extends Fragment implements View.OnClickListener{

    private FragmentRunningBinding binding;
    public TextView ch1text;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        RunningViewModel RunningViewModel =
                new ViewModelProvider(this).get(RunningViewModel.class);

        binding = FragmentRunningBinding.inflate(inflater, container, false);

//        ch1text.setVisibility(View.INVISIBLE);
        TextView textView = binding.textRunning;
        TextView partner_name = binding.partnerName;
        partner_name.setText("Bob");
        RunningViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        View root = binding.getRoot();
        ImageView chapter1_image = binding.chapter1;
        chapter1_image.setImageResource(R.drawable.useful);
        TextView chapter1 = binding.chapter1title;
        chapter1.setText("В чем польза утренних пробежек (sports.ru)");
        FrameLayout framec1 = (FrameLayout) root.findViewById(R.id.framech1);
        framec1.setOnClickListener((View.OnClickListener) this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.framech1:
                
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}