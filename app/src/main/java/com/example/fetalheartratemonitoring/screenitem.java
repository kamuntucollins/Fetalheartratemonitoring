package com.example.fetalheartratemonitoring;

public class screenitem {
    String Title,Discription;
    int Screenimg;

    public screenitem(String title, String discription, int screenimg) {
        Title = title;
        Discription = discription;
        Screenimg = screenimg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public void setScreenimg(int screenimg) {
        Screenimg = screenimg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDiscription() {
        return Discription;
    }

    public int getScreenimg() {
        return Screenimg;
    }
}
