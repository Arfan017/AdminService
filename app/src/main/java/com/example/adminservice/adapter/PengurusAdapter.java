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

import java.util.List;

public class PengurusAdapter extends RecyclerView.Adapter<PengurusAdapter.PengurusViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final Context mCtx;
    private final List<ModelPengurus> ListPengurus;

    public PengurusAdapter(Context mCtx, List<ModelPengurus> listPengurus, RecyclerViewInterface recyclerViewInterface) {
        this.mCtx = mCtx;
        this.ListPengurus = listPengurus;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public PengurusAdapter.PengurusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listpengurus, parent, false);
        return new PengurusAdapter.PengurusViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PengurusViewHolder holder, int position) {
        ModelPengurus dataPengurus = ListPengurus.get(position);

        holder.pen.setText(dataPengurus.getNama_pengurus());
        holder.tem.setText(dataPengurus.getJabatan());
        holder.im.setText(dataPengurus.getNo_hp());
    }

    @Override
    public int getItemCount() {
        return ListPengurus.size();
    }

    public class PengurusViewHolder extends RecyclerView.ViewHolder {
        TextView idp, pen, tem,im,ad;
        CardView rel;
        public PengurusViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            pen = itemView.findViewById(R.id.penc);
            tem = itemView.findViewById(R.id.temc);
            im = itemView.findViewById(R.id.imamm);
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
