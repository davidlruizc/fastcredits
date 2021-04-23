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

    EditText document;
    EditText names;
    EditText lastNames;
    EditText address;
    EditText phoneHome;
    EditText phone;
    EditText email;
    EditText password;
    EditText confirmPassword;
    String gender;

    String documentText;
    String namesText;
    String lastNamesText;
    String addressText;
    String phoneHomeText;
    String phoneText;
    String emailText;
    String passwordText;
    String confirmPasswordText;
    String genderText;
    String countryText;

    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.activity_sign_up, container, false);

        RadioGroup radioGroup = (RadioGroup) localView.findViewById(R.id.radioGroup);
        Spinner spinnerCountry = (Spinner) localView.findViewById(R.id.countryList);
        Spinner spinnerProfile = (Spinner) localView.findViewById(R.id.profileType);
        document = (EditText) localView.findViewById(R.id.et_document);
        names = (EditText) localView.findViewById(R.id.et_name);
        lastNames = (EditText) localView.findViewById(R.id.et_lastName);
        address = (EditText) localView.findViewById(R.id.et_address);
        phoneHome = (EditText) localView.findViewById(R.id.et_homePhone);
        phone = (EditText) localView.findViewById(R.id.et_phone);
        email = (EditText) localView.findViewById(R.id.et_email);
        password = (EditText) localView.findViewById(R.id.et_password);
        confirmPassword = (EditText) localView.findViewById(R.id.et_repassword);

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.countryList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapterProfile = ArrayAdapter.createFromResource(getContext(),
                R.array.profileType, android.R.layout.simple_spinner_item);
        adapterProfile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfile.setAdapter(adapterProfile);

        documentText = document.getText().toString();
        namesText = names.getText().toString();
        lastNamesText = lastNames.getText().toString();
        genderText = gender;
        countryText = spinnerCountry.getSelectedItem().toString();
        addressText = address.getText().toString();
        phoneText = phone.getText().toString();
        emailText = email.getText().toString();
        passwordText = password.getText().toString();

        final Button button = localView.findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("emv", documentText);
                // SignUp();
            }
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

    private void SignUp () {
        Call<LenderResponse> call = ApiAdapter.getApiService().signUpLender(new Lender(emailText, passwordText, documentText, namesText, lastNamesText, genderText, countryText, addressText, phoneText));
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