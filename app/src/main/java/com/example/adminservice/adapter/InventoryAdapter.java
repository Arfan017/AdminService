package com.example.adminservice.adapter;

import static com.example.adminservice.api.Konfigurasi.URL_IMAGES;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminservice.R;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final Context mCtx;
    private final List<ModelInventory> ListInventory;

    public InventoryAdapter(Context mCtx, List<ModelInventory> listInventory, RecyclerViewInterface recyclerViewInterface) {
        this.mCtx = mCtx;
        this.ListInventory = listInventory;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public InventoryAdapter.InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listinvestaris, parent, false);
        return new InventoryAdapter.InventoryViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        ModelInventory dataInventory = ListInventory.get(position);

        holder.nam.setText(dataInventory.getNama_aset());
        holder.jum.setText(dataInventory.getJumlah());
        holder.kon.setText(dataInventory.getKondisi());
        holder.ket.setText(dataInventory.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return ListInventory.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView idp, nam, jum, sat, kon, ket;

        CardView rel;
        public InventoryViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            nam = itemView.findViewById(R.id.aseta);
            jum = itemView.findViewById(R.id.juma);
            kon = itemView.findViewById(R.id.kona);
            ket = itemView.findViewById(R.id.keta);
            rel = itemView.findViewById(R.id.rel);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
