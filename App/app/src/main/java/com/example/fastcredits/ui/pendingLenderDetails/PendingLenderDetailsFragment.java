package com.example.fastcredits.ui.pendingLenderDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fastcredits.R;

public class PendingLenderDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pending_lender_details, container, false);

        // String embeces = getArguments().getString("lender_id");
        // Toast.makeText(getContext(), embeces, Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        return root;
    }
}