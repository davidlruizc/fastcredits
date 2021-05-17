package com.example.fastcredits;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fastcredits.models.Countries;
import com.example.fastcredits.models.Lender;
import com.example.fastcredits.models.LenderResponse;
import com.example.fastcredits.services.ApiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends Fragment {
    List<String> formatCountries;

    String gender;

    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.activity_sign_up, container, false);

        // get id
        EditText document = (EditText) localView.findViewById(R.id.et_document);
        EditText names = (EditText) localView.findViewById(R.id.et_name);
        EditText lastNames = (EditText) localView.findViewById(R.id.et_lastName);
        EditText address = (EditText) localView.findViewById(R.id.et_address);
        EditText phoneHome = (EditText) localView.findViewById(R.id.et_homePhone);
        EditText phone = (EditText) localView.findViewById(R.id.et_phone);
        EditText email = (EditText) localView.findViewById(R.id.et_email);
        EditText password = (EditText) localView.findViewById(R.id.et_password);
        EditText confirmPassword = (EditText) localView.findViewById(R.id.et_repassword);
        Spinner spinnerCountry = (Spinner) localView.findViewById(R.id.countryList);
        Spinner spinnerProfile = (Spinner) localView.findViewById(R.id.profileType);

        // need to be fixed
        RadioGroup radioGroup = (RadioGroup) localView.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.et_male:
                        gender = "Masculino";
                        break;
                    case R.id.et_female:
                        gender = "Femenino";
                        break;
                }
            }
        });

        CountryResponse();

        // Profile spinner
        ArrayAdapter<CharSequence> adapterProfile = ArrayAdapter.createFromResource(getContext(),
                R.array.profileType, android.R.layout.simple_spinner_item);
        adapterProfile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfile.setAdapter(adapterProfile);

        // Country spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.countryList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        final Button button = localView.findViewById(R.id.btn_register);
        button.setOnClickListener(v -> {
            String documentText = document.getText().toString();
            String namesText = names.getText().toString();
            String lastNamesText = lastNames.getText().toString();
            String genderText = gender;
            String countryText = spinnerCountry.getSelectedItem().toString();
            String addressText = address.getText().toString();
            String phoneText = phone.getText().toString();
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String profileTypeText = spinnerProfile.getSelectedItem().toString();

            Lender l = new Lender(emailText, passwordText, documentText, namesText, lastNamesText, gender, countryText, addressText, phoneText);
            Toast.makeText(getContext(), genderText, Toast.LENGTH_SHORT).show();
            // SignUp();
        });

        // Inflate the layout for this fragment
        return localView;
    }

    private void CountryResponse() {
        Call<ArrayList<Countries>> call = ApiAdapter.getApiService().getCountries();
        call.enqueue(new Callback<ArrayList<Countries>>() {
            @Override
            public void onResponse(Call<ArrayList<Countries>> call, Response<ArrayList<Countries>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Countries> countries = response.body();

                    for (int i = 0; i < countries.size(); ++ i) {
                        String country = countries.get(i).getName();
                        Log.d("aaa", country);
                    }
                } else {
                    Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Countries>> call, Throwable t) {
                Toast.makeText(getContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SignUpSubmit (Lender lender) {
        Call<LenderResponse> call = ApiAdapter.getApiService().signUpLender(lender);
        call.enqueue(new Callback<LenderResponse>() {
            @Override
            public void onResponse(Call<LenderResponse> call, Response<LenderResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("respuesta", response.message());
                } else {
                    Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LenderResponse> call, Throwable t) {
                Toast.makeText(getContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }
}