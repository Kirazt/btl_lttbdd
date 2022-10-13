package com.bprj.stepapp.ranking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bprj.stepapp.Category.category;
import com.bprj.stepapp.IClickItemListener;
import com.bprj.stepapp.Item.item;
import com.bprj.stepapp.R;

import java.util.ArrayList;
import java.util.List;

public class ranking_activity extends Fragment {
    ranking_adapter ranking_adapter;
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ranking_activity, container, false);
        RecyclerView recyclerViewview = view.findViewById(R.id.rankinguser);
        ranking_adapter = new ranking_adapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerViewview.setLayoutManager(linearLayoutManager);
        ranking_adapter.setData(getListRank());
        recyclerViewview.setAdapter(ranking_adapter);
        return view;
    }
    public List<rank> getListRank(){
        List<rank> r = new ArrayList<>();
        r.add(new rank("cc",10));
        r.add(new rank("cc",0));
        r.add(new rank("cc",0));
        r.add(new rank("cc",0));
        r.add(new rank("cc",0));
        return r;
    }
}
