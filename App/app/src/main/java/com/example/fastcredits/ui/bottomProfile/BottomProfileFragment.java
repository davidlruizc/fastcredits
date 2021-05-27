package com.example.fastcredits.ui.bottomProfile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fastcredits.MainActivity;
import com.example.fastcredits.R;
import com.example.fastcredits.ui.notifications.NotificationsViewModel;
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