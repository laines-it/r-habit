package com.example.hab_reboen.ui.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hab_reboen.databinding.FragmentOtherBinding;
import com.example.hab_reboen.ui.other.OtherViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


    public class OtherFragment extends Fragment {

        private @NonNull FragmentOtherBinding binding;

        public OtherFragment() {

        }

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            OtherViewModel otherViewModel =
                    new ViewModelProvider(this).get(OtherViewModel.class);

             binding = FragmentOtherBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            final TextView textView = binding.textOther;
            otherViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
            return root;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }

