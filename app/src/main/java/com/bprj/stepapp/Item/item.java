package com.bprj.stepapp.Item;

import java.io.Serializable;

public class item implements Serializable {

    private int resoureImg;
    private int id;

    public item(int id,int resoureImg ){
        this.id = id;
        this.resoureImg = resoureImg;
    }

    public int getResoureImg() {
        return resoureImg;
    }

    public void setResoureImg(int resoureImg) {
        this.resoureImg = resoureImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "item{" +
                "resoureImg=" + resoureImg +
                ", id=" + id +
                '}';
    }
}
