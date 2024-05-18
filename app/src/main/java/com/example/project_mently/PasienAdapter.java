package com.example.project_mently;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kodeJava.Pasien;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.PasienViewHolder> {

    private Context mContext;
    private List<Pasien> mPasienList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Pasien pasien);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public PasienAdapter(Context context, List<Pasien> pasienList) {
        mContext = context;
        mPasienList = pasienList;
    }

    @NonNull
    @Override
    public PasienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pasien, parent, false);
        return new PasienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasienViewHolder holder, int position) {
        Pasien currentPasien = mPasienList.get(position);
        holder.txtNamaPasien.setText(currentPasien.getNama());
        holder.txtJenkelPasien.setText(currentPasien.getJenkel());
        holder.txtUmurPasien.setText(currentPasien.getUmur());
        holder.txtPasienAt.setText(currentPasien.getTanggalDibuat());
    }

    @Override
    public int getItemCount() {
        return mPasienList.size();
    }

    public class PasienViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaPasien, txtJenkelPasien, txtUmurPasien, txtPasienAt;

        public PasienViewHolder(View itemView) {
            super(itemView);
            txtNamaPasien = itemView.findViewById(R.id.txtNamaPasien);
            txtJenkelPasien = itemView.findViewById(R.id.txtJenkelPasien);
            txtUmurPasien = itemView.findViewById(R.id.txtUmurPasien);
            txtPasienAt = itemView.findViewById(R.id.txtPasienAt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(mPasienList.get(position));
                        }
                    }
                }
            });
        }
    }
}
