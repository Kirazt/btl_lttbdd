package com.bprj.stepapp.Item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bprj.stepapp.IClickItemListener;
import com.bprj.stepapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.itemviewholder>{
    private List<item> itemList;
    private List<String> positionhide = new ArrayList<>();
    private final IClickItemListener iClickItemListener;
    public static final String SHARED_PREFS = "sharedPrefs";
    Dialog dialog;
    int buffquantiy;
    private Context mcontext;
    SharedPreferences sharedPreferences;

    public ItemAdapter(List<item> list,IClickItemListener iClickItemListener){
        this.itemList = list;
        this.iClickItemListener = iClickItemListener;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setdata(List<item> list){
        this.itemList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemAdapter.itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem_horizontal,parent,false);
        dialog = new Dialog(view.getContext());
        mcontext = view.getContext();
        sharedPreferences = mcontext.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        return new ItemAdapter.itemviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.itemviewholder holder, int position) {
        item it = itemList.get(position);
        loadData();
        if(it == null){
            return;
        }

        holder.Imgview.setImageResource(it.getResoureImg());


        if(!positionhide.isEmpty()) {
            for (int i = 0; i < positionhide.size(); i++) {
                Log.e("can run", positionhide.get(i).toString());
                if (positionhide.get(i).toString() .equals( String.valueOf(it.getId()))) {
                    holder.useitembtn.setVisibility(View.GONE);
                    holder.cancelitembtn.setVisibility(View.VISIBLE);
                }
            }
        }

        holder.useitembtn.setOnClickListener(view -> {
            onClickUseItem(holder,position, String.valueOf(it.getId()));
        });
        holder.cancelitembtn.setOnClickListener(view -> {
            loadData();
            holder.cancelitembtn.setVisibility(View.GONE);
            holder.useitembtn.setVisibility(View.VISIBLE);
            if(buffquantiy>-1)buffquantiy-=1;
            for (int i=0; i<positionhide.size();i++){
                if(positionhide.get(i).toString().equals(String.valueOf(it.getId()))){
                    Log.e("asdas",positionhide.get(i).toString());
                    positionhide.remove(i);
                }
            }
            Log.e("List",positionhide.toString());
            pushData();
        });

    }
    private void onClickUseItem(@NonNull ItemAdapter.itemviewholder holder, int  position, String it) {
        dialog.setContentView(R.layout.useitem_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.closeuseitemdialog);
        Button accpetitem= dialog.findViewById(R.id.useitembtndialog);
        Button cancel = dialog.findViewById(R.id.cancelbtn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        accpetitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                holder.useitembtn.setVisibility(View.GONE);
                holder.cancelitembtn.setVisibility(View.VISIBLE);
                buffquantiy+=1;
                positionhide.add(it);
                pushData();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void pushData(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(positionhide);
        editor.putStringSet("listbtn", set);
        editor.putInt("buffquantity",buffquantiy);
        editor.apply();
        editor.commit();

    }

    public void  loadData(){
            Set<String> set = sharedPreferences.getStringSet("listbtn",  new HashSet<String>());
            buffquantiy = sharedPreferences.getInt("buffquantity",0);
            positionhide = new ArrayList<>(set);
            Log.e("List", positionhide.toString());
    }

    @Override
    public int getItemCount() {
        if(itemList!=null){
            return itemList.size();
        }
        return 0;
    }


    public static class itemviewholder extends RecyclerView.ViewHolder{

        private Button useitembtn,cancelitembtn;
        private final ImageView Imgview;
        public itemviewholder(@NonNull View itemView) {
            super(itemView);

            cancelitembtn = itemView.findViewById(R.id.cancelitembtn);
            Imgview = itemView.findViewById(R.id.item_view);
            useitembtn = itemView.findViewById(R.id.useitembtn);
        }
    }
}
