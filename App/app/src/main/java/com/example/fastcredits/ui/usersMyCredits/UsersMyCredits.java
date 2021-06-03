package com.example.fastcredits.ui.usersMyCredits;

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
import com.example.fastcredits.models.AllCredits;
import com.example.fastcredits.models.Credit;
import com.example.fastcredits.models.User;
import com.example.fastcredits.models.Users;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.ui.adminUsers.CardUsersAdapter;
import com.example.fastcredits.utils.PreferenceStore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersMyCredits#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersMyCredits extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsersMyCredits() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersMyCredits.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersMyCredits newInstance(String param1, String param2) {
        UsersMyCredits fragment = new UsersMyCredits();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView userCardRV;
    private ProgressBar progressBar;
    private ArrayList<Credit> creditArrayList;
    private CardCreditsAdapter cardCreditAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_users_my_credits, container, false);

        // initializing our variables.
        userCardRV = root.findViewById(R.id.credit_requests_recycler);
        progressBar = root.findViewById(R.id.credit_requests_loading);

        // creating new array list.
        creditArrayList = new ArrayList<>();

        GetUsersRequests();

        return root;
    }

    public void GetUsersRequests() {
        String id = PreferenceStore.getPersistMongoId(this.getContext());
        Call<AllCredits> call = ApiAdapter.getApiService().getCredits(id);
        call.enqueue(new Callback<AllCredits>() {
            @Override
            public void onResponse(Call<AllCredits> call, Response<AllCredits> response) {
                if (response.isSuccessful()) {
                    // on successful we are hiding our progressbar.
                    progressBar.setVisibility(View.GONE);

                    // below line is to add our data from api to our array list.
                    creditArrayList = response.body().getCredits();

                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < creditArrayList.size(); i++) {
                        cardCreditAdapter = new CardCreditsAdapter(creditArrayList, getContext());

                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());

                        // setting layout manager for our recycler view.
                        userCardRV.setLayoutManager(manager);

                        // below line is to set adapter to our recycler view.
                        userCardRV.setAdapter(cardCreditAdapter);
                    }
                } else {
                    Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllCredits> call, Throwable t) {
                Toast.makeText(getContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }
}