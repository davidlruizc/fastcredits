package com.example.fastcredits.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastcredits.R;
import com.example.fastcredits.models.Lender;
import com.example.fastcredits.models.Lenders;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.ui.adminHome.CardLendersAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private RecyclerView lenderCardRV;
    private ArrayList<Lender> lendersArrayList;
    private CardAdminAdapter cardAdminAdapter;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // initializing our variables.
        lenderCardRV = root.findViewById(R.id.admin_administration_recycler);
        progressBar = root.findViewById(R.id.admin_administration_loading);

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
                        cardAdminAdapter = new CardAdminAdapter(lendersArrayList, getContext());

                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());

                        // setting layout manager for our recycler view.
                        lenderCardRV.setLayoutManager(manager);

                        // below line is to set adapter to our recycler view.
                        lenderCardRV.setAdapter(cardAdminAdapter);
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