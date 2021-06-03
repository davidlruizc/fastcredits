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

import com.example.fastcredits.R;
import com.example.fastcredits.ui.clients.ClientsViewModel;

public class LoanFragment extends Fragment {

    private LoanViewModel loanViewModel;
    private String paymentMethod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_loan, container, false);

        Spinner spinnerProfile = root.findViewById(R.id.client_list);

        // Profile type spinner
        ArrayAdapter<CharSequence> adapterProfile = ArrayAdapter.createFromResource(getContext(),
                R.array.profileType_sign_up, android.R.layout.simple_spinner_item);
        adapterProfile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfile.setAdapter(adapterProfile);

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

        return root;
    }
}