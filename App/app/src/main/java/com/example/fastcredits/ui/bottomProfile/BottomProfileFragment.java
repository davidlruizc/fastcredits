package com.example.fastcredits.ui.bottomProfile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastcredits.MainActivity;
import com.example.fastcredits.R;
import com.example.fastcredits.utils.PreferenceStore;
import com.squareup.picasso.Picasso;

public class BottomProfileFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_profile, container, false);

        Picasso.get().load("https://cdn.pixabay.com/photo/2014/05/02/00/21/exit-335818_960_720.jpg").into((ImageView) root.findViewById(R.id.imageView2));

        final Button button = root.findViewById(R.id.button_id_profile);
        final TextView email = root.findViewById(R.id.text_email);
        final TextView rol = root.findViewById(R.id.text_rol);
        String emailText = PreferenceStore.getPersistUser(this.getContext());
        int rolText = PreferenceStore.getRolePersisted(this.getContext());

        email.setText(emailText);
        if(rolText == 0){
            rol.setText("Usuario");
        } else if(rolText == 1){
            rol.setText("Rutero");
        } else if(rolText == 2){
            rol.setText("Prestamista");
        } else if(rolText == 3){
            rol.setText("Administrador");
        }


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CLEAR STORE
                Boolean persistCredentials = PreferenceStore.getPersistCredentials(getContext());
                PreferenceStore.setPersistSession(false, getContext());
                PreferenceStore.clearMongoId(getContext());

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