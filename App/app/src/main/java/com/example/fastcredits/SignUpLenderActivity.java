package com.example.fastcredits;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fastcredits.models.ApiResponse;
import com.example.fastcredits.models.Countries;
import com.example.fastcredits.models.Lender;
import com.example.fastcredits.services.ApiAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpLenderActivity extends AppCompatActivity {
    private String gender;
    private Spinner spinnerCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_lender);

        // get form ids
        EditText document = findViewById(R.id.et_document);
        EditText names = findViewById(R.id.et_name);
        EditText lastNames = findViewById(R.id.et_lastName);
        EditText address = findViewById(R.id.et_address);
        EditText phoneHome = findViewById(R.id.et_homePhone);
        EditText phone = findViewById(R.id.et_phone);
        EditText email = findViewById(R.id.et_email);
        EditText password = findViewById(R.id.et_password);
        EditText confirmPassword = findViewById(R.id.et_repassword);
        Spinner spinnerProfile = findViewById(R.id.profileType);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId) {
                case R.id.et_male:
                    gender = "Masculino";
                    break;
                case R.id.et_female:
                    gender = "Femenino";
                    break;
            }
        });

        // Country spinner
        GetCountryList();

        // Profile spinner
        ArrayAdapter<CharSequence> adapterProfile = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.profileType, android.R.layout.simple_spinner_item);
        adapterProfile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfile.setAdapter(adapterProfile);

        final Button button = findViewById(R.id.btn_register);
        button.setOnClickListener(v -> {
            String documentText = document.getText().toString();
            String namesText = names.getText().toString();
            String lastNamesText = lastNames.getText().toString();
            String countryText = spinnerCountry.getSelectedItem().toString();
            String addressText = address.getText().toString();
            String phoneText = phone.getText().toString();
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String confirmPasswordText = confirmPassword.getText().toString();
            String profileTypeText = spinnerProfile.getSelectedItem().toString();


            if (documentText != null && namesText != null && lastNamesText != null && addressText != null && phoneText != null && emailText != null && passwordText != null && gender != null && confirmPasswordText != null) {
                if (passwordText.equals(confirmPasswordText)) {
                    if (profileTypeText.equals("Prestamista")) {
                        Lender lender = new Lender(emailText, passwordText, documentText, namesText, lastNamesText, gender, countryText, addressText, phoneText);
                        SignUpLenderSubmit(lender);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), profileTypeText, Toast.LENGTH_SHORT).show();
                    // dialog message
                    new AlertDialog.Builder(getApplicationContext())
                            .setTitle("¡Error!")
                            .setMessage("Lo sentimos, las contraseñas no coinciden, intenta nuevamente.")
                            .setPositiveButton("Aceptar", (dialog, which) -> Log.d("MainActivity", "Sending atomic bombs to Jupiter"))
                            .show();
                }
            } else {
                // dialog message
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("¡Error!")
                        .setMessage("No puedes continuar hasta que completes todos los campos")
                        .setPositiveButton("Aceptar", (dialog, which) -> Log.d("MainActivity", "Sending atomic bombs to Jupiter"))
                        .show();
            }
        });
    }

    private void GetCountryList() {
        Call<ArrayList<Countries>> call = ApiAdapter.getApiService().getCountries();
        call.enqueue(new Callback<ArrayList<Countries>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Countries>> call, @NotNull Response<ArrayList<Countries>> response) {
                spinnerCountry = findViewById(R.id.countryList);
                if (response.isSuccessful()) {
                    ArrayList<Countries> countries = response.body();

                    ArrayAdapter<Countries> adapter =
                            new ArrayAdapter<>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, countries);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

                    spinnerCountry.setAdapter(adapter);

                } else {
                    Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Countries>> call, @NotNull Throwable t) {
                Toast.makeText(getApplicationContext(), "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SignUpLenderSubmit (Lender lender) {
        Call<ApiResponse> call = ApiAdapter.getApiService().signUpLender(lender);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse> call, @NotNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        new AlertDialog.Builder(getApplicationContext())
                                .setTitle("¡Error!")
                                .setMessage(jObjError.getString("message"))
                                .setPositiveButton("Aceptar", (dialog, which) -> Log.d("MainActivity", "Sending atomic bombs to Jupiter"))
                                .show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ApiResponse> call, @NotNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error por favor intentarlo nuevamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}