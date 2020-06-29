package com.example.misreportapartment.Model;

public class User {
    private int id;
    private String userName;
    private String phone;
    private String password;

    public User() {
    }

    public User(int id, String userName, String phone, String password) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.password = password;
    }

    public User(int id, String userName, String phone) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
    }

    public User(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ID: " + id + " ,USERNAME: " + userName + " ,PHONE: " + phone + " ,PASSWORD: "+ password;
    }
}
