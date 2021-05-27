package com.example.fastcredits.ui.adminUsers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastcredits.R;
import com.example.fastcredits.models.Lenders;
import com.example.fastcredits.models.User;
import com.example.fastcredits.models.Users;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.ui.adminHome.CardLendersAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminUserFragment extends Fragment {
    private RecyclerView userCardRV;
    private ProgressBar progressBar;
    private ArrayList<User> userArrayList;
    private CardUsersAdapter cardUsersAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin_user, container, false);

        // initializing our variables.
        userCardRV = root.findViewById(R.id.admin_requests_recycler);
        progressBar = root.findViewById(R.id.admin_requests_loading);

        // creating new array list.
        userArrayList = new ArrayList<>();

        GetUsersRequests();

        return root;
    }

    public void GetUsersRequests() {
        Call<Users> call = ApiAdapter.getApiService().getAllUsers();
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    // on successful we are hiding our progressbar.
                    progressBar.setVisibility(View.GONE);

                    // below line is to add our data from api to our array list.
                    userArrayList = response.body().getUsers();

                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < userArrayList.size(); i++) {
                        cardUsersAdapter = new CardUsersAdapter(userArrayList, getContext());

                        cardUsersAdapter.setOnListRefresh(() -> GetUsersRequests());

                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());

                        // setting layout manager for our recycler view.
                        userCardRV.setLayoutManager(manager);

                        // below line is to set adapter to our recycler view.
                        userCardRV.setAdapter(cardUsersAdapter);
                    }
                } else {
                    Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }
}