package com.example.hab_reboen.supports.for_user;

public class Habit{

    public String name;
    public String description;
    public String partner;
    public long date_start;
    public long date_end;

    public Habit(){}
    public Habit(String name, String description,String partner, long date_start, long date_end){
        this.name = name;
        this.description = description;
        this.partner = partner;
        this.date_start = date_start;
        this.date_end = date_end;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public long getDate_start() {
        return date_start;
    }

    public void setDate_start(long date_start) {
        this.date_start = date_start;
    }

    public long getDate_end() {
        return date_end;
    }

    public void setDate_end(long date_end) {
        this.date_end = date_end;
    }

}
