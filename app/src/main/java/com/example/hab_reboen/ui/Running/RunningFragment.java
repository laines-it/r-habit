package com.example.hab_reboen.ui.Running;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hab_reboen.databinding.FragmentRunningBinding;

public class RunningFragment extends Fragment {

    private FragmentRunningBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RunningViewModel RunningViewModel =
                new ViewModelProvider(this).get(RunningViewModel.class);

        binding = FragmentRunningBinding.inflate(inflater, container, false);


        final TextView textView = binding.textRunning;
        final TextView partner_name = binding.partnerName;
        partner_name.setText("Bob");
        RunningViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}