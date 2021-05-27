package com.example.fastcredits.ui.adminUsers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastcredits.R;
import com.example.fastcredits.models.EnableDisableUser;
import com.example.fastcredits.models.SetEnableDisableUser;
import com.example.fastcredits.models.User;
import com.example.fastcredits.models.Users;
import com.example.fastcredits.services.ApiAdapter;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardUsersAdapter extends RecyclerView.Adapter<CardUsersAdapter.RecyclerViewHolder> {
    // creating a variable for our array list and context.
    private ArrayList<User> usersArrayList;
    private Context mcontext;

    private OnListRefresh onListRefresh;

    // creating a constructor class.
    public CardUsersAdapter(ArrayList<User> recyclerDataArrayList, Context mcontext) {
        this.usersArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    public void setOnListRefresh(OnListRefresh onListRefresh) {
        this.onListRefresh = onListRefresh;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_users_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        // Set the data to textview from our modal class.
        User modal = usersArrayList.get(position);
        holder.fullName.setText(modal.getFullName());
        holder.email.setText(modal.getEmail());
        holder.cellphone.setText(modal.getCellphone());
        holder.role.setText(modal.getRole());
        holder.date.setText(formatter.format(modal.getDate()));
        holder.id = modal.getId();
        holder.rol = modal.getRole();

        Boolean state = modal.getState();
        holder.state = state;
        if(state){
            holder.active.setText("Desactivar");
        } else {
            holder.active.setText("Activar");
        }
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder  {
        // creating variables for our views.
        private TextView fullName, email, cellphone, role, date;
        private MaterialButton active;
        private String id, rol;
        private Boolean state;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            // initializing our views with their ids.
            fullName = itemView.findViewById(R.id.card_full_name);
            email = itemView.findViewById(R.id.card_email);
            cellphone = itemView.findViewById(R.id.card_phone_number);
            role = itemView.findViewById(R.id.card_role);
            cellphone = itemView.findViewById(R.id.card_phone_number);
            date = itemView.findViewById(R.id.card_date);
            active = itemView.findViewById(R.id.card_active);

            active.setOnClickListener(v -> {
                active.setEnabled(false);
                SetEnableDisableUser edu = new SetEnableDisableUser(id, rol, !state);
                Call<EnableDisableUser> call = ApiAdapter.getApiService().enableDisableUser(edu);
                call.enqueue(new Callback<EnableDisableUser>() {
                    @Override
                    public void onResponse(Call<EnableDisableUser> call, Response<EnableDisableUser> response) {
                        if (response.isSuccessful()) {
                            String res = response.body().getResponse();
                            Toast.makeText(itemView.getContext(), res, Toast.LENGTH_SHORT).show();
                            onListRefresh.refreshList();
                        } else {
                            Toast.makeText(itemView.getContext(), "Ha ocurrido un error, por favor inténtelo nuevamente.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EnableDisableUser> call, Throwable t) {
                        Toast.makeText(itemView.getContext(), "Ha ocurrido un error, por favor inténtelo nuevamente o revisa tu conexión a internet.", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }
    }

}
