package com.bprj.stepapp.User;

public class User {
    private String gmail;
    private String fullname;
    private String username;
    private String password;
    private int stepmove;

    public User() {
    }

    public User(String gmail, String fullname, String username, String password, int stepmove) {
        this.gmail = gmail;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.stepmove = stepmove;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStepmove() {
        return stepmove;
    }

    public void setStepmove(int stepmove) {
        this.stepmove = stepmove;
    }
}
