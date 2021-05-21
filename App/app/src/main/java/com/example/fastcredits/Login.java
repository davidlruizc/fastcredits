package com.example.fastcredits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fastcredits.utils.PreferenceStore;

public class Login extends Fragment {
    private PreferenceStore store = new PreferenceStore();

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

            if (emailText != null && passwordText != null) {

            }
        });

        // Inflate the layout for this fragment
        return localView;
    }

    private void SignInInSubmit() {

    }
}