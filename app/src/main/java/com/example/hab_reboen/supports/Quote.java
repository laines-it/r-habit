package com.example.hab_reboen.supports;

public class Quote {
    String Text;
    String Author;
    String Country;
    int Year;
    int Rank;
    String About;
    public Quote(){}
    public Quote(String Text, String Author, String Country, int Rank, int Year, String About){
        this.Text = Text;
        this.Author = Author;
        this.Country = Country;
        this.Rank = Rank;
        this.Year = Year;
        this.About = About;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }
}
