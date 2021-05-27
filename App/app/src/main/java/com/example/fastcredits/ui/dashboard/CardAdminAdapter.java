package com.example.fastcredits.ui.dashboard;

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

import com.example.fastcredits.R;
import com.example.fastcredits.models.Lender;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CardAdminAdapter extends RecyclerView.Adapter<CardAdminAdapter.RecyclerViewHolder> {
    // creating a variable for our array list and context.
    private ArrayList<Lender> lendersArrayList;
    private Context mcontext;

    // creating a constructor class.
    public CardAdminAdapter(ArrayList<Lender> recyclerDataArrayList, Context mcontext) {
        this.lendersArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public CardAdminAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new CardAdminAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdminAdapter.RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        Lender modal = lendersArrayList.get(position);
        holder.fullName.setText(modal.getFullName());
        holder.email.setText(modal.getEmail());
        holder.cellphone.setText(modal.getCellphone());
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
                bundle.putString("lender_id", "embeces");

                Navigation.findNavController(itemView).navigate(R.id.navigation_bottom_lender_details, bundle);
            });

        }
    }
}
