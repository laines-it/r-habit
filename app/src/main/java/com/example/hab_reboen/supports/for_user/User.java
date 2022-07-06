package com.example.hab_reboen.supports.for_user;

import com.example.hab_reboen.supports.Quote;

import java.util.Date;

public class User{
    public int id;
    public String name;
    public String email;
    public String city;
    public Date date_birth;
    public int sex;
    public HiddenInfo hiddenInfo;
    public Quote quote;
//    public ArrayList habits;
    public User(){}
    public User(int id, String name, String email,String city, Date date_birth, int sex, HiddenInfo hiddenInfo,Quote quote){
        this.quote = quote;
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.date_birth = date_birth;
        this.sex = sex;
        this.hiddenInfo = hiddenInfo;
    }

    public HiddenInfo getHiddenInfo() {
        return hiddenInfo;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void setHiddenInfo(HiddenInfo hiddenInfo) {
        this.hiddenInfo = hiddenInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
