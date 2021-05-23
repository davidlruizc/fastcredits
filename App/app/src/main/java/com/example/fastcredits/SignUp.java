package com.example.fastcredits;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUp extends Fragment {
    String gender;

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
                case R.id.et_clients:
                    gender = getString(R.string.profile_clients);
                    break;
                case R.id.et_lender:
                    gender = getString(R.string.profile_lender);
                    break;
                case R.id.et_router:
                    gender = getString(R.string.profile_router);
                    break;
                case R.id.et_admin:
                    gender = getString(R.string.profile_admin);
                    break;
            }
        });

        final Button button = localView.findViewById(R.id.btn_register);
        button.setOnClickListener(v -> {
            if (gender != null) {
                // TODO: set all activities for each option
                switch(gender) {
                    case "Cliente":
                        Toast.makeText(getContext(), "Cliente", Toast.LENGTH_SHORT).show();
                        break;
                    case "Prestamista":
                        startActivity(new Intent(getActivity(), SignUpLenderActivity.class));
                        break;
                    case "Rutero":
                        Toast.makeText(getContext(), "Rutero", Toast.LENGTH_SHORT).show();
                        break;
                    case "Administrador":
                        Toast.makeText(getContext(), "Admin", Toast.LENGTH_SHORT).show();
                        break;
                }
            } else {
                new AlertDialog.Builder(getContext())
                        .setTitle("¡Ups!")
                        .setMessage("Para poder continuar debes seleccionar un tipo de perfil")
                        .setPositiveButton("Aceptar", (dialog, which) -> Log.d("MainActivity", "Sending atomic bombs to Jupiter"))
                        .show();
            }
        });

        // Inflate the layout for this fragment
        return localView;
    }
}