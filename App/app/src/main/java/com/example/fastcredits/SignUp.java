package com.example.fastcredits;

import androidx.appcompat.app.AlertDialog;
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

public class SignUp extends Fragment {
    String gender;
    Spinner spinnerCountry;

    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.activity_sign_up, container, false);

        RadioGroup radioGroup = localView.findViewById(R.id.radioGroup);

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

        final Button button = localView.findViewById(R.id.btn_register);
        button.setOnClickListener(v -> {
            Toast.makeText(getContext(), "BIBIBIBIIBIB", Toast.LENGTH_SHORT).show();
        });

        // Inflate the layout for this fragment
        return localView;
    }
}