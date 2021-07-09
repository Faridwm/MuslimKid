package com.fwmubarok.muslimkid.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fwmubarok.muslimkid.Model.DailyDoa;
import com.fwmubarok.muslimkid.Model.Doa;
import com.fwmubarok.muslimkid.R;

import java.util.ArrayList;
import java.util.List;

public class DailyDoaAdapter extends RecyclerView.Adapter<DailyDoaAdapter.ListViewHolder> {

    private List<Doa> doas = new ArrayList<>();
    private OnDailyDoaListener onDailyDoaListener;

    public DailyDoaAdapter(List<Doa> doas, OnDailyDoaListener onDailyDoaListener) {
        this.doas = doas;
        this.onDailyDoaListener = onDailyDoaListener;
    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_doa, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view, onDailyDoaListener);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyDoaAdapter.ListViewHolder holder, int position) {
        holder.tv_judul_doa.setText(doas.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDailyDoaListener.OnDailyDoaClick(position);
            }
        });

        if (position % 2 == 0) {
//            holder.tv_judul_doa.setBackgroundColor(Color.parseColor("#FF8B3D"));
            holder.tv_judul_doa.setBackgroundColor(ContextCompat.getColor(holder.tv_judul_doa.getContext(), R.color.primary_orange));
        } else {
            holder.tv_judul_doa.setBackgroundColor(ContextCompat.getColor(holder.tv_judul_doa.getContext(), R.color.primary_blue));
        }
    }

    @Override
    public int getItemCount() {
        return doas.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Text View
        CardView rv_card;
        TextView tv_judul_doa;
        OnDailyDoaListener onDailyDoaListener;

        public ListViewHolder(@NonNull View itemView, OnDailyDoaListener onDailyDoaListener) {
            super(itemView);
            tv_judul_doa = itemView.findViewById(R.id.tv_nama_doa);
            rv_card = itemView.findViewById(R.id.rv_card_layout);

            this.onDailyDoaListener = onDailyDoaListener;
        }

        @Override
        public void onClick(View v) {
            onDailyDoaListener.OnDailyDoaClick(getBindingAdapterPosition());
        }
    }

    public interface OnDailyDoaListener {
        void OnDailyDoaClick(int position);
    }
}
