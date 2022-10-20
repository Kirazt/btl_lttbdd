package com.bprj.stepapp.ranking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bprj.stepapp.R;

import java.util.List;

public class ranking_adapter extends RecyclerView.Adapter<ranking_adapter.rankViewHolder> {
    private List<rank> Lrank;
    private Context context;

    public void setData(List<rank> list) {
        this.Lrank = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public rankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.bprj.stepapp.R.layout.user_ranking, parent, false);
        return new rankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rankViewHolder holder, int position) {
        if (Lrank == null) {
            return;
        }
        rank r = Lrank.get(position);
        holder.username.setText(r.getUsername());
        holder.step.setText(String.valueOf(r.getStep()));
    }

    @Override
    public int getItemCount() {
        if (Lrank != null) {
            return Lrank.size();
        }
        return 0;
    }

    public class rankViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView step;

        public rankViewHolder(@NonNull View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.usernameranking);
            step = itemView.findViewById(R.id.userstepranking);
        }
    }
}
