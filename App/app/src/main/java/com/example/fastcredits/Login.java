package com.example.fastcredits;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fastcredits.models.ApiResponse;
import com.example.fastcredits.models.SignIn;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.utils.PreferenceStore;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Fragment {

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.activity_login, container, false);

        EditText email = localView.findViewById(R.id.et_login_email);
        EditText password = localView.findViewById(R.id.et_login_password);
        CheckBox rememberUser = localView.findViewById(R.id.checkBox);
        Spinner spinnerProfile = localView.findViewById(R.id.et_profile_type);

        // Profile type spinner
        ArrayAdapter<CharSequence> adapterProfile = ArrayAdapter.createFromResource(getContext(),
                R.array.profileType, android.R.layout.simple_spinner_item);
        adapterProfile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfile.setAdapter(adapterProfile);

        final Button button = localView.findViewById(R.id.btn_login);
        button.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String profileTypeText = spinnerProfile.getSelectedItem().toString();
            int profileType;

            switch (profileTypeText) {
                case "Cliente":
                    profileType = 0;
                    break;
                case "Prestamista":
                    profileType = 1;
                    break;
                case "Rutero":
                    profileType = 2;
                    break;
                case "Administrador":
                    profileType = 3;
                    break;
                default:
                    profileType = 0;
                    break;
            }


            if (emailText != null && passwordText != null) {
                SignIn signIn = new SignIn(emailText, passwordText, profileType);
                SignInInSubmit(signIn, rememberUser.isChecked());
            } else {
                // dialog message
                new AlertDialog.Builder(getContext())
                        .setTitle("¡Error!")
                        .setMessage("Tiene campos incompletos")
                        .setPositiveButton("Aceptar", (dialog, which) -> Log.d("MainActivity", "Sending atomic bombs to Jupiter"))
                        .show();
            }
        });

        // Inflate the layout for this fragment
        return localView;
    }

    private void SignInInSubmit(SignIn signIn, Boolean rememberUser) {
        Call<ApiResponse> call = ApiAdapter.getApiService().singInUser(signIn);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    PreferenceStore.setEmailPassword(signIn.getEmail(), signIn.getPassword(), signIn.getRole(), getContext());
                    PreferenceStore.setPersistSession(true, getContext());
                    PreferenceStore.setPersistCredentials(rememberUser, getContext());

                    Toast.makeText(getContext(),"Bienvenido a FastCredits", Toast.LENGTH_LONG).show();

                    switch (signIn.getRole()) {
                        case 0:
                            startActivity(new Intent(getActivity(), UsersDrawerActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getActivity(), DrawerHome.class));
                            break;
                        case 2:
                            startActivity(new Intent(getActivity(), RouterDrawerActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(getActivity(), AdminBottomNaivation.class));
                            break;
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        new AlertDialog.Builder(getContext())
                                .setTitle("¡Error!")
                                .setMessage(jObjError.getString("message"))
                                .setPositiveButton("Aceptar", (dialog, which) -> Log.d("MainActivity", "Sending atomic bombs to Jupiter"))
                                .show();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Ha ocurrido un error por favor intentarlo nuevamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}