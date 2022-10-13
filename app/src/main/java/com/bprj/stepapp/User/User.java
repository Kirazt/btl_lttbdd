package com.bprj.stepapp.User;

public class User {
    private String gmail;
    private String fullname;
    private String username;
    private String password;
    private String stepmove;

    public User() {
    }

    public User(String gmail, String fullname, String username, String password, String stepmove) {
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

    public String getStepmove() {
        return stepmove;
    }

    public void setStepmove(String stepmove) {
        this.stepmove = stepmove;
    }
}
