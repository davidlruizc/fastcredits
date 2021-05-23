package com.example.fastcredits.ui.adminHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fastcredits.R;
import com.example.fastcredits.ui.dashboard.DashboardViewModel;

public class AdminHomeFragment extends Fragment {

    private AdminHomeViewModel adminHomeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adminHomeViewModel =
                new ViewModelProvider(this).get(AdminHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_home, container, false);
        final TextView textView = root.findViewById(R.id.text_adminHome);
        adminHomeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}