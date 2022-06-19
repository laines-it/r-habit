package com.example.hab_reboen.supports;

import java.util.ArrayList;
import java.util.Date;

public class User{
    public String id;
    public String name;
    public String email;
    public String city;
    public Date date_birth;
    public String soc_status;
    public ArrayList habits;
    public User(String id, String name, String email,String city, Date date_birth, String soc_status){
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.date_birth = date_birth;
        this.soc_status = soc_status;
    }
}
