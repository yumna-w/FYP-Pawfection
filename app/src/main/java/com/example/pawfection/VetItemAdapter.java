package com.example.pawfection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VetItemAdapter extends RecyclerView.Adapter<VetItemAdapter.ViewHolder> {

    VetItem[] vetItems;
    Context context;

    public VetItemAdapter(VetItem[] vetItems, PetLoverVetAppointmentsVetsList activity) {
        this.vetItems = vetItems;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.vets_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final VetItem vetItemList = vetItems[position];
        holder.vetName.setText(vetItemList.getVetName());
        holder.vetArea.setText(vetItemList.getVetArea());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PetLoverVetAppointmentsVetProfile.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vetItems.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView vetName;
        TextView vetArea;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vetName = itemView.findViewById(R.id.vetName);
            vetArea = itemView.findViewById(R.id.vetArea);
        }
    }
}
