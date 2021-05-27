package com.example.fastcredits.ui.lenderDetails;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fastcredits.R;

public class LenderDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lender_details, container, false);

        String embeces = getArguments().getString("lender_id");
        Toast.makeText(getContext(), embeces, Toast.LENGTH_SHORT).show();

        return root;
    }
}