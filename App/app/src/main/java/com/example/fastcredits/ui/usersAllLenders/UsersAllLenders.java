package com.example.fastcredits.ui.usersAllLenders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fastcredits.R;
import com.example.fastcredits.models.Lender;
import com.example.fastcredits.models.Lenders;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.ui.dashboard.CardAdminAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsersAllLenders extends Fragment {
    private RecyclerView lenderCardRV;
    private ArrayList<Lender> lendersArrayList;
    private CardLendersAdapter cardLendersAdapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_users_all_lenders, container, false);

        // initializing our variables.
        lenderCardRV = root.findViewById(R.id.user_all_lenders_recycler);
        progressBar = root.findViewById(R.id.user_all_lenders_loading);

        // creating new array list.
        lendersArrayList = new ArrayList<>();

        GetAllLenders();

        return root;
    }

    public void GetAllLenders() {
        Call<Lenders> call = ApiAdapter.getApiService().getAllLenders();
        call.enqueue(new Callback<Lenders>() {
            @Override
            public void onResponse(Call<Lenders> call, Response<Lenders> response) {
                if (response.isSuccessful()) {
                    // on successful we are hiding our progressbar.
                    progressBar.setVisibility(View.GONE);

                    // below line is to add our data from api to our array list.
                    lendersArrayList = response.body().getLenders();

                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < lendersArrayList.size(); i++) {
                        cardLendersAdapter = new CardLendersAdapter(lendersArrayList, getContext());

                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());

                        // setting layout manager for our recycler view.
                        lenderCardRV.setLayoutManager(manager);

                        // below line is to set adapter to our recycler view.
                        lenderCardRV.setAdapter(cardLendersAdapter);
                    }
                } else {
                    Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lenders> call, Throwable t) {
                Toast.makeText(getContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }
}