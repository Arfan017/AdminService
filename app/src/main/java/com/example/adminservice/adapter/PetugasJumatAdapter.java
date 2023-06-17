package com.example.adminservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminservice.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class PetugasJumatAdapter extends RecyclerView.Adapter<PetugasJumatAdapter.PetugasJumatViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final Context mCtx;
    private final List<ModelPetugasJumat> ListPetugasJumat;

    public PetugasJumatAdapter(Context mCtx, List<ModelPetugasJumat> listPetugasJumat, RecyclerViewInterface recyclerViewInterface) {
        this.mCtx = mCtx;
        this.ListPetugasJumat = listPetugasJumat;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public PetugasJumatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listpetugas, parent, false);
        return new PetugasJumatViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PetugasJumatViewHolder holder, int position) {
        ModelPetugasJumat dataPetugasJumat = ListPetugasJumat.get(position);

        holder.tgl.setText(dataPetugasJumat.getTanggal());
        holder.pen.setText(dataPetugasJumat.getPenceramah());
        holder.tem.setText(dataPetugasJumat.getTema());
        holder.im.setText(dataPetugasJumat.getImam_sholat());
    }

    @Override
    public int getItemCount() {
        return ListPetugasJumat.size();
    }

    public class PetugasJumatViewHolder extends RecyclerView.ViewHolder {
        TextView idp, pen, tem, im, ad, tgl;

        CardView rel;

        public PetugasJumatViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            tgl = itemView.findViewById(R.id.TVtgl);
            pen = itemView.findViewById(R.id.penc);
            tem = itemView.findViewById(R.id.temc);
            im = itemView.findViewById(R.id.imamm);
            rel = itemView.findViewById(R.id.rel);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
