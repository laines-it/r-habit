package com.example.hab_reboen.supports;

import java.util.List;

public class Article{
    String Title;
    String Text;
    String Imageres;
    List<String> Hashtags;
    String Source;
    Integer Year;
    public Article(){}
    public Article(String Title, String Text, String Imageres,List<String> Hashtags, String Source,Integer Year){
        this.Title = Title;
        this.Text = Text;
        this.Imageres = Imageres;
        this.Hashtags = Hashtags;
        this.Source = Source;
        this.Year = Year;
    }

    public Integer getYear(){
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public void setImageres(String Imageres) {
        this.Imageres = Imageres;
    }

    public String getTitle() {
        return Title;
    }

    public String getText() {
        return Text;
    }

    public String getImageres() {
        return Imageres;
    }

    public List<String> getHashtags(){return Hashtags;}

    public String getSource(){return Source;}
}
