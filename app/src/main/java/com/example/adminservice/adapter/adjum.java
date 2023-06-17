package com.example.adminservice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminservice.R;
import com.example.adminservice.Sekretaris.Edit_Petugas_Jumat;
import com.example.adminservice.modul.data.dataptgsjumat;

import java.util.List;

public class adjum extends RecyclerView.Adapter<adjum.AdapterHolder> {
    private Context context;
    private List<dataptgsjumat> dlist;

    public adjum(Context context, List<dataptgsjumat> dlist) {
        this.context = context;
        this.dlist = dlist;

    }

    @NonNull
    @Override
    public adjum.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listjum, parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adjum.AdapterHolder holder, int position) {
        dataptgsjumat djum = dlist.get(position);
        String id = djum.getId_penceramah();
        String pencearamah = djum.getPenceramah();
        String tema = djum.getTema();
        String imam = djum.getImam_shalat();
        String adzan = djum.getAdzan();

        holder.pen.setText(pencearamah);
        holder.tem.setText(tema);
        holder.im.setText(imam);
        holder.ad.setText(adzan);

        holder.rel.setOnClickListener(v -> {
            Intent intent=new Intent(context, Edit_Petugas_Jumat.class);
            intent.putExtra("intent id",djum.getId_penceramah());

            intent.putExtra("intent penceramah",djum.getPenceramah());

            intent.putExtra("intent tema",djum.getTema());

            intent.putExtra("intent imam",djum.getImam_shalat());

            intent.putExtra("intent adzan",djum.getAdzan());

            context.startActivity(intent);
        });


    }



    @Override
    public int getItemCount () {
        return dlist.size();
    }
    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView idp, pen, tem,im,ad;

        CardView rel;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            idp = itemView.findViewById(R.id.idc);
            pen = itemView.findViewById(R.id.penc);
            tem = itemView.findViewById(R.id.temc);
            im = itemView.findViewById(R.id.imamm);
            ad = itemView.findViewById(R.id.adz);
            rel = itemView.findViewById(R.id.rel);
        }
    }
}
