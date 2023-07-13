package com.emirer.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emirer.myapplication.Model.hastaModel;
import com.emirer.myapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class hastaAdapter extends RecyclerView.Adapter<hastaAdapter.MyViewHolder> {
    private Context context;
    private List<hastaModel> list;
    private Dialog dialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public interface Dialog {
        void onClick(int pos);
    }

    public Dialog getDialog() {
        return dialog;
    }

    public hastaAdapter (Context context, List<hastaModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hastalar, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.hastaAdi.setText(list.get(position).getHastaAdi());
        holder.hastaSoyadi.setText(list.get(position).getHastaSoyadi());
        holder.hastaEmail.setText(list.get(position).getHastaEmail());
        holder.hastaEgzersiz.setText(list.get(position).getHastaEgzersiz());
        holder.hastaTekrar.setText(list.get(position).getHastaTekrar());
        holder.hastaSonuc.setText(list.get(position).getHastaSonuc());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hastaAdi, hastaSoyadi, hastaEmail, hastaEgzersiz, hastaTekrar, hastaSonuc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hastaAdi = itemView.findViewById(R.id.rhastaAdi);
            hastaSoyadi= itemView.findViewById(R.id.rhastaSoyadi);
            hastaEmail  = itemView.findViewById(R.id.rhastaEmail);
            hastaEgzersiz = itemView.findViewById(R.id.rhastaEgzersiz);
            hastaTekrar = itemView.findViewById(R.id.rhastaTekrar);
            hastaSonuc = itemView.findViewById(R.id.rhastaSonuc);

        }

    }

}
