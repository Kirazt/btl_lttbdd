package com.bprj.stepapp.ranking;

import java.io.Serializable;

public class rank implements Serializable {
    private String username;
    private int step;


    public rank(String username, int step) {
        this.username = username;
        this.step = step;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
