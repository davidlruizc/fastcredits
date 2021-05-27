package com.example.fastcredits.ui.bottomProfile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fastcredits.MainActivity;
import com.example.fastcredits.R;
import com.example.fastcredits.utils.PreferenceStore;

public class BottomProfileFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_profile, container, false);

        final Button button = root.findViewById(R.id.button_id_profile);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CLEAR STORE
                Boolean persistCredentials = PreferenceStore.getPersistCredentials(getContext());
                PreferenceStore.setPersistSession(false, getContext());

                if (!persistCredentials) {
                    PreferenceStore.clearSession(getContext());
                    PreferenceStore.setPersistCredentials(false, getContext());
                }

                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return root;
    }
}