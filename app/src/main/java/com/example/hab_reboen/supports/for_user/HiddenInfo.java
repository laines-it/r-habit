package com.example.hab_reboen.supports.for_user;

public class HiddenInfo {
    boolean sex_hide;
    boolean date_of_birth_hide;
    boolean city_hide;
    public HiddenInfo(){}
    public HiddenInfo(boolean sex_hide, boolean date_of_birth_hide, boolean city_hide){
        this.sex_hide = sex_hide;
        this.date_of_birth_hide = date_of_birth_hide;
        this.city_hide =city_hide;
    }

    public boolean isSex_hide() {
        return sex_hide;
    }

    public void setSex_hide(boolean sex_hide) {
        this.sex_hide = sex_hide;
    }

    public boolean isDate_of_birth_hide() {
        return date_of_birth_hide;
    }

    public void setDate_of_birth_hide(boolean date_of_birth_hide) {
        this.date_of_birth_hide = date_of_birth_hide;
    }

    public boolean isCity_hide() {
        return city_hide;
    }

    public void setCity_hide(boolean city_hide) {
        this.city_hide = city_hide;
    }
}
