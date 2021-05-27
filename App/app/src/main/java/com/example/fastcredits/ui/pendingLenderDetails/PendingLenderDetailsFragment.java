package com.example.fastcredits.ui.pendingLenderDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastcredits.R;
import com.example.fastcredits.models.ApiResponse;
import com.example.fastcredits.models.EnableLender;
import com.example.fastcredits.models.Lenders;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.ui.adminHome.CardLendersAdapter;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingLenderDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pending_lender_details, container, false);

        Picasso.get().load("https://source.unsplash.com/random").into((ImageView) root.findViewById(R.id.header_cover_image));

        TextView textFullName = root.findViewById(R.id.user_profile_name);
        TextView textEmail = root.findViewById(R.id.user_profile_email);
        TextView textGender = root.findViewById(R.id.user_gender);
        TextView textCountry = root.findViewById(R.id.user_country);
        TextView textDate = root.findViewById(R.id.user_date);
        Button enableBtn = root.findViewById(R.id.enable_lender_btn);

        String fullName = getArguments().getString("full_name");
        String email = getArguments().getString("email");
        String gender = getArguments().getString("gender");
        String country = getArguments().getString("country");
        String date = getArguments().getString("date");
        String Id = getArguments().getString("Id");

        textFullName.setText(fullName);
        textEmail.setText(email);
        textGender.setText("Genero: " + gender);
        textCountry.setText("País: " + country);
        textDate.setText("Fecha de creación: " + date);

        enableBtn.setOnClickListener(v -> {
            EnableLender lender = new EnableLender(Id);
            EnableLenderSubmit(lender);
        });

        // Inflate the layout for this fragment
        return root;
    }

    public void EnableLenderSubmit(EnableLender enableLender) {
        Call<ApiResponse> call = ApiAdapter.getApiService().enableLender(enableLender);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    Navigation.findNavController(getView()).navigate(R.id.navigation_bottom_home);
                    Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }
}