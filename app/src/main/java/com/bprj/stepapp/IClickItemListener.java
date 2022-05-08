package com.bprj.stepapp;

import com.bprj.stepapp.Item.item;
import com.bprj.stepapp.Category.category;

public interface IClickItemListener {
    void onClickItem(item it);
    void onClickCate(category ct);
    void onCancelitem(item it);


}
