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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastcredits.R;
import com.example.fastcredits.models.AllUsers;
import com.example.fastcredits.models.ApiResponse;
import com.example.fastcredits.models.Countries;
import com.example.fastcredits.models.Credit;
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

        EditText periodicity = root.findViewById(R.id.lender_periodicity);
        EditText amount = root.findViewById(R.id.lender_amount);
        EditText interest = root.findViewById(R.id.lender_interest);
        TextView feeText = root.findViewById(R.id.lender_fee_tx);

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

        final Button button = root.findViewById(R.id.btn_lender_create_credit);
        button.setOnClickListener(v -> {
            String periodicityText = periodicity.getText().toString();
            String amountText = amount.getText().toString();
            String interestText = interest.getText().toString();

            float fee = Fee(Integer.parseInt(periodicityText), Integer.parseInt(amountText), Integer.parseInt(interestText));

            feeText.setText(String.valueOf(fee));

            Credit credit = new Credit( "6081eb78827e48414886db15", paymentMethod, Integer.parseInt(periodicityText), Integer.parseInt(amountText), Integer.parseInt(interestText), fee);

            CreateCreditSubmit(credit);
        });

        return root;
    }

    private float Fee(int periodicity, int amount, int interest) {
        return (amount/periodicity) + interest;
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

    private void CreateCreditSubmit(Credit credit) {
        Call<ApiResponse> call = ApiAdapter.getApiService().createCredit(credit);
        call.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
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