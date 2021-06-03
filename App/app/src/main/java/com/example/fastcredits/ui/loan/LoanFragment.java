package com.example.fastcredits.ui.loan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastcredits.R;
import com.example.fastcredits.models.AllUsers;
import com.example.fastcredits.models.Countries;
import com.example.fastcredits.models.User;
import com.example.fastcredits.models.UserModel;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.ui.clients.ClientsViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanFragment extends Fragment {

    private LoanViewModel loanViewModel;
    private String paymentMethod;
    private Spinner spinnerAllUsers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_loan, container, false);

        RadioGroup radioGroup = root.findViewById(R.id.lender_radio_payment);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId) {
                case R.id.lender_radio_payment_card:
                    paymentMethod = "Tarjeta";
                    break;
                case R.id.lender_radio_payment_cash:
                    paymentMethod = "Efectivo";
                    break;
            }
        });

        GetAllUsers();

        return root;
    }

    private void GetAllUsers() {
        Call<AllUsers> call = ApiAdapter.getApiService().getAllUsersAccounts();
        call.enqueue(new Callback<AllUsers>() {
            @Override
            public void onResponse(Call<AllUsers> call, Response<AllUsers> response) {
                spinnerAllUsers = getActivity().findViewById(R.id.client_list);

                if (response.isSuccessful()) {
                    ArrayList<UserModel> users = response.body().getUsers();

                    ArrayAdapter<UserModel> adapter =
                            new ArrayAdapter<>(getContext(),  android.R.layout.simple_spinner_dropdown_item, users);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

                    spinnerAllUsers.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllUsers> call, Throwable t) {
                Toast.makeText(getContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }
}