package com.bprj.stepapp.Category;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bprj.stepapp.IClickItemListener;
import com.bprj.stepapp.Item.ItemAdapter;
import com.bprj.stepapp.Item.item;
import com.bprj.stepapp.R;
import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.CategoryViewHolder>{

    private final Context mcontext;
    private List<category> categoryList;
    private final IClickItemListener iClickItemListener;
    List<item> list ;
    Dialog dialog;


    public Category_Adapter(Context mcontext, IClickItemListener iClickItemListener){
        this.mcontext = mcontext;
        this.iClickItemListener = iClickItemListener;
    }


    public void setData(List<category> list){
        this.categoryList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        dialog = new Dialog(view.getContext());
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        category category = categoryList.get(position);
        if(category==null){
            return;
        }

        holder.itembag.setText(category.getItembag());
        holder.item_category.setText(category.getItem_category());

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(mcontext,RecyclerView.HORIZONTAL,false);
        holder.recyclerView.setItemViewCacheSize(100);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        holder.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
            }
        });
        holder.itembag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemListener.onClickCate(category);
            }
        });

        ItemAdapter itemAdapter = new ItemAdapter(list, new IClickItemListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClickItem(item it) {
            }

            @Override
            public void onClickCate(com.bprj.stepapp.Category.category ct) {

            }
            @Override
            public void onCancelitem(item it) {
            }
        });

        itemAdapter.setdata(category.getItems());
        holder.recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();

    }



    @Override
    public int getItemCount() {
        if(categoryList!=null){
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView item_category;
        private Button itembag, useitm, cancelitm;
        private RecyclerView recyclerView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            useitm = (Button) itemView.findViewById(R.id.useitembtn);
            cancelitm = (Button) itemView.findViewById(R.id.cancelitembtn);
            itembag = itemView.findViewById(R.id.itembag);
            item_category = itemView.findViewById(R.id.item_category);
            recyclerView = itemView.findViewById(R.id.showitemlist);
        }
    }
}
