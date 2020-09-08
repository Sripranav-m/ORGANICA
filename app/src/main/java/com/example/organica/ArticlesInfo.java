package com.example.organica;

public class ArticlesInfo {
    public String username;
    public String heading;
    public String content;
    public String imageurl;

    public ArticlesInfo() {
    }

    public ArticlesInfo(String username, String heading, String content, String imageurl){
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
    public void setusername(String username){
        this.username=username;
    }
    public void setheading(String heading){

        this.heading=heading;

    }
    public void setcontent(String content){

        this.content=content;

    }
    public void setimageurl(String imageurl){

        this.imageurl=imageurl;
    }
}
