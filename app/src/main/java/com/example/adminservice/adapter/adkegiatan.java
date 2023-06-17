package com.example.adminservice.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.adminservice.Sekretaris.Edit_Kegiatan;
import com.example.adminservice.modul.data.data_kegiatan;

import java.util.List;

public class adkegiatan extends RecyclerView.Adapter<adkegiatan.AdapterHolder> {
    private Context context;
    private List<data_kegiatan> dlist;

    public adkegiatan(Context context, List<data_kegiatan> dlist) {
        this.context = context;
        this.dlist = dlist;
    }

    @NonNull
    @Override
    public adkegiatan.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listkegiatan, parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adkegiatan.AdapterHolder holder, int position) {
        data_kegiatan djum = dlist.get(position);
        String id = djum.getId_kegiatan();
        String hari = djum.getHari();
//        String tema = djum.getWaktu();
        String kegiatan = djum.getKegiatan();
        String pennggungJwb = djum.getPenangungjwb();
        String foto = djum.getFoto();

        String ur = "https://projekandro.000webhostapp.com/img/";
        Glide.with(context).load(ur+foto).thumbnail().into(holder.Imgkegiatan);

//        holder.idp.setText(id);
        holder.TVtgl.setText(hari);
        holder.TVnamakegiatan.setText(kegiatan);
        holder.TVpenanggungJwb.setText(pennggungJwb);

//        holder.idp.setText("|\t"+id+"\t|");
//        holder.pen.setText("|\t"+pencearamah+"\t|");
////        holder.tem.setText("|\t"+tema+"\t|");
//        holder.im.setText("|\t"+imam+"\t|");
//        holder.ad.setText("|\t"+adzan+"\t|");

        holder.rel.setOnClickListener(v -> {
            Intent intent=new Intent(context, Edit_Kegiatan.class);
            intent.putExtra("intent id",djum.getId_kegiatan());
            intent.putExtra("intent penceramah",djum.getHari());
//            intent.putExtra("intent tema",djum.getWaktu());
            intent.putExtra("intent imam",djum.getKegiatan());
            intent.putExtra("intent adzan",djum.getPenangungjwb());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount () {
        return dlist.size();
    }
    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView TVtgl, TVnamakegiatan, TVpenanggungJwb;
        ImageView Imgkegiatan;

        CardView rel;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            Imgkegiatan = itemView.findViewById(R.id.imgKegiatan);
            TVtgl = itemView.findViewById(R.id.tv_tgl);
            TVnamakegiatan = itemView.findViewById(R.id.tvkegiatan);
            TVpenanggungJwb = itemView.findViewById(R.id.tvPenanggungJwb);
            rel = itemView.findViewById(R.id.rec);
        }
    }
}
