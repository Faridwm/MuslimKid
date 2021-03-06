package com.fwmubarok.muslimkid.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.Collection;
import java.util.List;

public class DailyDoaAdapter extends RecyclerView.Adapter<DailyDoaAdapter.ListViewHolder> implements Filterable {
    private static final String TAG = "DailyDoaAdapter";

    private List<Doa> doas = new ArrayList<>();
    private List<Doa> doasFull;
    private OnDailyDoaListener onDailyDoaListener;

    public DailyDoaAdapter(List<Doa> doas, OnDailyDoaListener onDailyDoaListener) {
        this.doas = doas;
        this.onDailyDoaListener = onDailyDoaListener;
        doasFull = new ArrayList<>(doas);
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

        if (position % 4 == 0) {
            holder.tv_judul_doa.setBackgroundColor(ContextCompat.getColor(holder.tv_judul_doa.getContext(), R.color.primary_red));
        } else if (position % 4 == 1) {
            holder.tv_judul_doa.setBackgroundColor(ContextCompat.getColor(holder.tv_judul_doa.getContext(), R.color.primary_blue));
        } else if (position % 4 == 2) {
            holder.tv_judul_doa.setBackgroundColor(ContextCompat.getColor(holder.tv_judul_doa.getContext(), R.color.primary_green));
        } else if (position % 4 == 3) {
            holder.tv_judul_doa.setBackgroundColor(ContextCompat.getColor(holder.tv_judul_doa.getContext(), R.color.primary_orange));
        } else {
            holder.tv_judul_doa.setBackgroundColor(ContextCompat.getColor(holder.tv_judul_doa.getContext(), R.color.disable_gray));
        }
    }

    @Override
    public int getItemCount() {
        return doas.size();
    }

    @Override
    public Filter getFilter() {
        return doaFiltered;
    }

    private Filter doaFiltered = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Doa> filDoa = new ArrayList<>();

            if (constraint.toString().isEmpty()) {
                filDoa.addAll(doasFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                Log.d(TAG, "performFiltering: Pattern " + filterPattern);

                for (Doa doa : doasFull) {
                    if (doa.getTitle().toLowerCase().contains(filterPattern)) {
                        filDoa.add(doa);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filDoa;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            doas.clear();
            doas.addAll((Collection<? extends Doa>) results.values);
            notifyDataSetChanged();
        }
    };

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
