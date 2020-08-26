package com.example.organica;

public class ArticlesInfo {
    public String username;
    public String heading;
    public String content;
    public String imageurl;

    public ArticlesInfo(String username,String heading,String content,String imageurl){
        this.username=username;
        this.heading=heading;
        this.content=content;
        this.imageurl=imageurl;
    }
    public String getusername(){
        return username;
    }
    public String getheading(){
        return heading;
    }
    public String getcontent(){
        return content;
    }
    public String getimageurl(){
        return imageurl;
    }
}
