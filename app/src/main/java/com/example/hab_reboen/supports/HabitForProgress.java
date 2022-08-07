package com.example.hab_reboen.supports;

public class HabitForProgress {
    int progress;
    String name_of_partner;
    String time;
    String name;

    public HabitForProgress(String name,int progress,String name_of_partner,String time){
        this.name = name;
        this.progress = progress;
        this.name_of_partner = name_of_partner;
        this.time = time;
    }
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getName_of_partner() {
        return name_of_partner;
    }

    public void setName_of_partner(String name_of_partner) {
        this.name_of_partner = name_of_partner;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
