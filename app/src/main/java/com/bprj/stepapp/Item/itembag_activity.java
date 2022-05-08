package com.bprj.stepapp.Item;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bprj.stepapp.Category.category;
import com.bprj.stepapp.IClickItemListener;
import com.bprj.stepapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class itembag_activity extends Fragment {

    TextView backbtn;
    private RecyclerView recyclerViewview;
    LinearLayout selectitembag;
    BottomNavigationView navbott;
    private com.bprj.stepapp.Item.ItemBagAdapter ItemBagAdapter;
    Button cancelbtn, usebtn, okbtn;
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.itembag_activity, container, false);
        cancelbtn = view.findViewById(R.id.cancel_button);
        usebtn = view.findViewById(R.id.usebtn);
        okbtn = view.findViewById(R.id.ok_button);
        selectitembag = view.findViewById(R.id.selectitembag);
        navbott = getActivity().findViewById(R.id.navbottom);
        backbtn = view.findViewById(R.id.backitembtn);
        img = view.findViewById(R.id.item_view);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(navbott!= null) {
                    if (navbott.getVisibility() == View.GONE) {
                        cancelbtn.setVisibility(View.GONE);
                        okbtn.setVisibility(View.GONE);
                        usebtn.setVisibility(View.VISIBLE);
                        navbott.setVisibility(View.VISIBLE);
                        selectitembag.setVisibility(View.GONE);
                    }
                }
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(navbott!= null) {
                    if (navbott.getVisibility() == View.GONE) {
                        cancelbtn.setVisibility(View.GONE);
                        okbtn.setVisibility(View.GONE);
                        usebtn.setVisibility(View.VISIBLE);
                        navbott.setVisibility(View.VISIBLE);
                        selectitembag.setVisibility(View.GONE);
                    }
                }getActivity().onBackPressed();
            }
        });

        recyclerViewview = view.findViewById(R.id.itemcontainer);
        ItemBagAdapter = new ItemBagAdapter(getActivity(), new IClickItemListener() {
            @Override
            public void onClickItem(item it) {
                navbott.setVisibility(View.GONE);
                usebtn.setVisibility(View.GONE);
                cancelbtn.setVisibility(View.VISIBLE);
                okbtn.setVisibility(View.VISIBLE);
                selectitembag.setVisibility(View.VISIBLE);

            }
            @Override
            public void onClickCate(category ct) {

            }
            @Override
            public void onCancelitem(item it) {

            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerViewview.setLayoutManager(gridLayoutManager);
        ItemBagAdapter.setdata(getlistitem());
        recyclerViewview.setAdapter(ItemBagAdapter);
        return  view;
    }

    private List<item> getlistitem() {
        List<item> list = new ArrayList<>();
        list.add(new item(1,R.drawable.gray_item_ico));
        list.add(new item(2,R.drawable.gray_item_ico));
        list.add(new item(3,R.drawable.ograne_item_ico));
        list.add(new item(4,R.drawable.gray_item_ico));
        list.add(new item(5,R.drawable.gray_item_ico));
        list.add(new item(6,R.drawable.puprle_item_ico));
        list.add(new item(7,R.drawable.gray_item_ico));
        list.add(new item(8,R.drawable.gray_item_ico));
        list.add(new item(9,R.drawable.puprle_item_ico));
        list.add(new item(10,R.drawable.gray_item_ico));
        list.add(new item(11,R.drawable.ograne_item_ico));
        list.add(new item(12,R.drawable.gray_item_ico));
        list.add(new item(13,R.drawable.gray_item_ico));
        list.add(new item(14,R.drawable.ograne_item_ico));
        list.add(new item(15,R.drawable.gray_item_ico));
        list.add(new item(16,R.drawable.gray_item_ico));
        list.add(new item(17,R.drawable.gray_item_ico));
        list.add(new item(18,R.drawable.ograne_item_ico));
        list.add(new item(19,R.drawable.gray_item_ico));
        list.add(new item(20,R.drawable.gray_item_ico));
        list.add(new item(21,R.drawable.puprle_item_ico));
        list.add(new item(22,R.drawable.gray_item_ico));
        list.add(new item(23,R.drawable.gray_item_ico));
        list.add(new item(24,R.drawable.puprle_item_ico));
        list.add(new item(25,R.drawable.gray_item_ico));
        list.add(new item(26,R.drawable.ograne_item_ico));
        list.add(new item(27,R.drawable.gray_item_ico));
        list.add(new item(28,R.drawable.gray_item_ico));
        list.add(new item(29,R.drawable.ograne_item_ico));
        list.add(new item(30,R.drawable.gray_item_ico));

        return list;
    }
}