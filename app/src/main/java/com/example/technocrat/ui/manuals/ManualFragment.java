package com.example.technocrat.ui.manuals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.technocrat.databinding.FragmentManualBinding;
import com.example.technocrat.ui.manuals.ManualViewModel;

public class ManualFragment extends Fragment {

    private FragmentManualBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ManualViewModel manualViewModel =
                new ViewModelProvider(this).get(ManualViewModel.class);

        binding = FragmentManualBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}