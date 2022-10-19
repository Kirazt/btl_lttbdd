package com.bprj.stepapp.ranking;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bprj.stepapp.Item.item;
import com.bprj.stepapp.User.User;
import com.bprj.stepapp.ranking.rank;
import com.bprj.stepapp.R;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private ranking_adapter mRankingUser;
    public void setConfig(RecyclerView recyclerView, Context context, List<User> users, List<String> keys){
        mContext = context;
        mRankingUser = new ranking_adapter(users, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRankingUser);
    }
    class ranking_adapter extends RecyclerView.Adapter<RankingUser> {
        private List<User> mUserlist;
        private List<String> mKeys;

        public ranking_adapter(List<User> mUserlist, List<String> mKeys) {
            this.mUserlist = mUserlist;
            this.mKeys = mKeys;
        }

        @Override
        public RankingUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RankingUser(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RankingUser holder, int position) {
            holder.bind(mUserlist.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserlist.size();
        }
    }
    class RankingUser extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView step;

        private String key;

        public RankingUser(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_ranking, parent, false));

            username = itemView.findViewById(R.id.usernameranking);
            step = itemView.findViewById(R.id.userstepranking);
        }

        public void bind(User user, String key) {
            username.setText(user.getUsername());
            step.setText(user.getStepmove());
            this.key = key;
        }
    }
}
