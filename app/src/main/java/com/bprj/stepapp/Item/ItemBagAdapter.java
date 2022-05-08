package com.bprj.stepapp.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bprj.stepapp.IClickItemListener;
import com.bprj.stepapp.R;

import java.util.List;

public class ItemBagAdapter extends RecyclerView.Adapter<ItemBagAdapter.itemviewholder>{

    private Context mContext;
    private List<item> itemList;
    private IClickItemListener iClickItemListener;

    public ItemBagAdapter(Context mContext, IClickItemListener iClickItemListener){
        this.mContext = mContext;
        this.iClickItemListener = iClickItemListener;
    }
    public void setdata(List<item> list){
        this.itemList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new itemviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemviewholder holder, int position) {
        item it = itemList.get(position);
        if(it == null){
            return;
        }
        holder.Imgview.setImageResource(it.getResoureImg());
        holder.Imgview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                iClickItemListener.onClickItem(it);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(itemList!=null){
            return itemList.size();
        }
        return 0;
    }

    public class itemviewholder extends RecyclerView.ViewHolder{

        private ImageView Imgview;
        public itemviewholder(@NonNull View itemView) {
            super(itemView);

            Imgview = itemView.findViewById(R.id.item_view);
        }
    }
}
