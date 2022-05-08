package com.bprj.stepapp.Category;

import com.bprj.stepapp.Item.item;

import java.util.List;

public class category {
    private String itembag, item_category;
    private List<item> items;

    public category(String item_category, String itembag, List<item> items){
        this.item_category = item_category;
        this.itembag = itembag;
        this.items = items;
    }

    public List<item> getItems() {
        return items;
    }

    public void setItems(List<item> items) {
        this.items = items;
    }

    public String getItembag() {
        return itembag;
    }

    public void setItembag(String itembag) {
        this.itembag = itembag;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }
}
