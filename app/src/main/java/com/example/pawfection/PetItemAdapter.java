package com.example.pawfection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetItemAdapter extends RecyclerView.Adapter<PetItemAdapter.ViewHolder> {

    PetItem[] petItems;
    Context context;

    public PetItemAdapter(PetItem[] petItems, PetLoverEditProfile activity) {
        this.petItems = petItems;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.pets_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final PetItem petItemList = petItems[i];
        viewHolder.petName.setText(petItemList.getPetName());
        viewHolder.petAnimalType.setText(petItemList.getPetAnimaType());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return petItems.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView petName;
        TextView petAnimalType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petName = itemView.findViewById(R.id.petName);
            petAnimalType = itemView.findViewById(R.id.petAnimalType);
        }
    }

    public void clearData() {
        for (int i = 0; i < petItems.length; i++) {
            petItems[i] = null; // Set elements to null for a String array
        }
    }
}
