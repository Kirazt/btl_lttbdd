package com.bprj.stepapp.Item;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bprj.stepapp.Category.Category_Adapter;
import com.bprj.stepapp.Category.category;
import com.bprj.stepapp.IClickItemListener;
import com.bprj.stepapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class items_activity extends Fragment{

    Button sneakerbtn, clockbtn, useitembtn;
    Dialog dialog;
    List<item> list ;
    List<item> clocklist;
    int buff = 20;
    private RecyclerView recyclerViewview;
    public static final String SHARED_PREFS = "sharedPrefs";
    private List<String> positionhide = new ArrayList<>();
    private Category_Adapter category_adapter;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.items_activity, container, false);
        useitembtn = view.findViewById(R.id.useitembtn);
        dialog = new Dialog(getActivity());


        recyclerViewview = view.findViewById(R.id.itemcategory);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        category_adapter = new Category_Adapter(getContext(), new IClickItemListener() {
            @Override
            public void onClickItem(item it) {
            }
            @Override
            public void onClickCate(category ct) {
                Fragment fragment = new itembag_activity();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
                fragmentTransaction.replace(R.id.itemcontainer,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            @Override
            public void onCancelitem(item it) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerViewview.setLayoutManager(linearLayoutManager);
        category_adapter.setData(getListCategory());
        recyclerViewview.setAdapter(category_adapter);

        return view;
    }


    private List<category> getListCategory() {
        List<category> catelist = new ArrayList<>();
        list = new ArrayList<>();
        clocklist = new ArrayList<>();
        //
        list.add(new item(1,R.drawable.gray_item));
        list.add(new item(2,R.drawable.gray_item));
        list.add(new item(3,R.drawable.organe_item));
        list.add(new item(4,R.drawable.gray_item));
        list.add(new item(5,R.drawable.gray_item));
        list.add(new item(6,R.drawable.puprle_item));
        list.add(new item(7,R.drawable.gray_item));
        list.add(new item(8,R.drawable.gray_item));
        list.add(new item(9,R.drawable.puprle_item));
        list.add(new item(10,R.drawable.gray_item));
        list.add(new item(11,R.drawable.organe_item));
        list.add(new item(12,R.drawable.gray_item));
        list.add(new item(13,R.drawable.gray_item));
        list.add(new item(14,R.drawable.organe_item));
        list.add(new item(15,R.drawable.gray_item));
        list.add(new item(16,R.drawable.gray_item));
        list.add(new item(17,R.drawable.gray_item));
        list.add(new item(18,R.drawable.organe_item));
        list.add(new item(19,R.drawable.gray_item));
        list.add(new item(20,R.drawable.gray_item));
        list.add(new item(21,R.drawable.puprle_item));
        list.add(new item(22,R.drawable.gray_item));
        list.add(new item(23,R.drawable.gray_item));
        list.add(new item(24,R.drawable.puprle_item));
        list.add(new item(25,R.drawable.gray_item));
        list.add(new item(26,R.drawable.organe_item));
        list.add(new item(27,R.drawable.gray_item));
        list.add(new item(28,R.drawable.gray_item));
        list.add(new item(29,R.drawable.organe_item));
        list.add(new item(30,R.drawable.gray_item));

        clocklist.add(new item(31,R.drawable.gray_clock));
        clocklist.add(new item(32,R.drawable.gray_clock));
        clocklist.add(new item(33,R.drawable.puprle_clock));
        clocklist.add(new item(34,R.drawable.gray_clock));
        clocklist.add(new item(35,R.drawable.puprle_clock));
        clocklist.add(new item(36,R.drawable.gray_clock));
        clocklist.add(new item(37,R.drawable.gray_clock));
        clocklist.add(new item(38,R.drawable.organe_clock));
        clocklist.add(new item(39,R.drawable.gray_clock));
        clocklist.add(new item(40,R.drawable.gray_clock));
        clocklist.add(new item(41,R.drawable.puprle_clock));
        clocklist.add(new item(42,R.drawable.gray_clock));
        clocklist.add(new item(43,R.drawable.organe_clock));
        clocklist.add(new item(44,R.drawable.gray_clock));
        clocklist.add(new item(45,R.drawable.gray_clock));
        catelist.add(new category("Sneaker","All sneaker",list));
        catelist.add(new category("Clock","All clock",clocklist));
        return catelist;
    }



}