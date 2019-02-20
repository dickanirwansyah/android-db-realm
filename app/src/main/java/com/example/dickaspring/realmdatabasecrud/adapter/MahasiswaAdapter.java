package com.example.dickaspring.realmdatabasecrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dickaspring.realmdatabasecrud.ActivityDetail;
import com.example.dickaspring.realmdatabasecrud.R;
import com.example.dickaspring.realmdatabasecrud.model.MahasiswaModel;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {

    private List<MahasiswaModel> mahasiswaModels;
    Context context;

    public MahasiswaAdapter(Context context, List<MahasiswaModel> mahasiswaModels){
        this.context = context;
        this.mahasiswaModels = mahasiswaModels;
    }

    @Override
    public MahasiswaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.
                from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaAdapter.MyViewHolder holder, int position){
        final MahasiswaModel mahasiswaModel = mahasiswaModels.get(position);
        holder.txtNim.setText(mahasiswaModel.getNim().toString());
        holder.txtNama.setText(mahasiswaModel.getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityDetail.class);
                intent.putExtra("id", mahasiswaModel.getId().toString());
                intent.putExtra("nim", mahasiswaModel.getNim().toString());
                intent.putExtra("nama", mahasiswaModel.getNama());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtNim;
        private TextView txtNama;

        public MyViewHolder(View itemView){
            super(itemView);
            txtNim = itemView.findViewById(R.id.itmMahasiswaNim);
            txtNama = itemView.findViewById(R.id.itmMahasiswaNama);
        }
    }
}
