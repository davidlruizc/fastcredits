package com.example.fastcredits.ui.pendingLenderDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastcredits.R;
import com.squareup.picasso.Picasso;

public class PendingLenderDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pending_lender_details, container, false);

        TextView textFullName = root.findViewById(R.id.user_profile_name);
        TextView textEmail = root.findViewById(R.id.user_profile_email);
        TextView textGender = root.findViewById(R.id.user_gender);
        TextView textCountry = root.findViewById(R.id.user_country);
        TextView textDate = root.findViewById(R.id.user_date);

        String fullName = getArguments().getString("full_name");
        String email = getArguments().getString("email");
        String gender = getArguments().getString("gender");
        String country = getArguments().getString("country");
        String date = getArguments().getString("date");

        textFullName.setText(fullName);
        textEmail.setText(email);
        textGender.setText("Genero: " + gender);
        textCountry.setText("País: " + country);
        textDate.setText("Fecha de creación: " + date);


        // Toast.makeText(getContext(), embeces, Toast.LENGTH_SHORT).show();


        Picasso.get().load("https://source.unsplash.com/random").into((ImageView) root.findViewById(R.id.header_cover_image));

        // Inflate the layout for this fragment
        return root;
    }
}