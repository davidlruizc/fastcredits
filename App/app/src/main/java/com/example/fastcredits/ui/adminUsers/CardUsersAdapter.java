package com.example.fastcredits.ui.adminHome;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastcredits.models.Lender;
import com.example.fastcredits.R;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CardLendersAdapter extends RecyclerView.Adapter<CardLendersAdapter.RecyclerViewHolder> {
    // creating a variable for our array list and context.
    private ArrayList<Lender> lendersArrayList;
    private Context mcontext;
    private String gender;
    private String country;
    private String address;
    private Date date;

    // creating a constructor class.
    public CardLendersAdapter(ArrayList<Lender> recyclerDataArrayList, Context mcontext) {
        this.lendersArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        Lender modal = lendersArrayList.get(position);
        holder.fullName.setText(modal.getFullName());
        holder.email.setText(modal.getEmail());
        holder.cellphone.setText(modal.getCellphone());
        gender = modal.getGender();
        country = modal.getCountry();
        address = modal.getAddress();
        date = modal.getDate();
    }

    @Override
    public int getItemCount() {
        return lendersArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder  {
        // creating variables for our views.
        private TextView fullName, email, cellphone;
        private ImageView image;
        private MaterialButton viewMore;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            // initializing our views with their ids.
            fullName = itemView.findViewById(R.id.card_full_name);
            email = itemView.findViewById(R.id.card_email);
            cellphone = itemView.findViewById(R.id.card_phone_number);
            viewMore = itemView.findViewById(R.id.card_more_bn);

            viewMore.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("full_name", fullName.getText().toString());
                bundle.putString("email", email.getText().toString());
                bundle.putString("cellphone", cellphone.getText().toString());

                DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mcontext);

                Toast.makeText(mcontext, dateFormat.format(date), Toast.LENGTH_LONG).show();

                Navigation.findNavController(itemView).navigate(R.id.navigation_bottom_pending_lender_details, bundle);
            });
        }
    }
}
