package com.example.fastcredits.ui.usersMyCredits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastcredits.R;
import com.example.fastcredits.models.Credit;
import com.example.fastcredits.models.EnableDisableUser;
import com.example.fastcredits.models.SetEnableDisableUser;
import com.example.fastcredits.models.User;
import com.example.fastcredits.services.ApiAdapter;
import com.example.fastcredits.ui.adminUsers.OnListRefresh;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardCreditsAdapter extends RecyclerView.Adapter<CardCreditsAdapter.RecyclerViewHolder> {
    // creating a variable for our array list and context.
    private ArrayList<Credit> creditsArrayList;
    private Context mcontext;

    private OnListRefresh onListRefresh;

    // creating a constructor class.
    public CardCreditsAdapter(ArrayList<Credit> recyclerDataArrayList, Context mcontext) {
        this.creditsArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_credits_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        // Set the data to textview from our modal class.
        Credit modal = creditsArrayList.get(position);
        holder.fullName.setText("Monto: " + modal.getAmount());
        holder.email.setText("Metodo de pago: " + modal.getPaymentMethod());
        holder.cellphone.setText("Cuota: " + modal.getFee());
        holder.role.setText("Periodicidad: " + modal.getPeriodicity());
        holder.date.setText(formatter.format(modal.getDate()));
    }

    @Override
    public int getItemCount() {
        return creditsArrayList.size();
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
        }
    }

}
