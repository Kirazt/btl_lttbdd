package com.bprj.stepapp.Member;

public class member {
    private String gmail;
    private String password;
    public member(){}
    public member(String gmail, String password) {
        this.gmail = gmail;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
