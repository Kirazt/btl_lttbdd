package com.bprj.stepapp.ranking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bprj.stepapp.Category.category;
import com.bprj.stepapp.FirebaseDatabaseHelper;
import com.bprj.stepapp.IClickItemListener;
import com.bprj.stepapp.Item.item;
import com.bprj.stepapp.R;
import com.bprj.stepapp.User.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ranking_activity extends Fragment {
    ranking_adapter ranking_adapter;
    List<rank> r = new ArrayList<>();
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ranking_activity, container, false);
        Button btn = view.findViewById(R.id.update);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query mquery = FirebaseDatabase.getInstance().getReference().child("user").orderByChild("stepmove").limitToLast(5);
                mquery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        r.clear();
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            String name = postSnapshot.child("fullname").getValue(String.class);
                            int stepmove = postSnapshot.child("stepmove").getValue(int.class);
                            r.add(new rank(name, stepmove));
                        }
                        ranking_adapter.setData(r);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btn.performClick();
        RecyclerView recyclerViewview = view.findViewById(R.id.rankinguser);
        ranking_adapter = new ranking_adapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewview.setLayoutManager(linearLayoutManager);
        ranking_adapter.setData(r);
        recyclerViewview.setAdapter(ranking_adapter);
        return view;
    }
}
