package com.example.organica;


public class OrderInfo {
    //    public String buyer_username;
    public String item_name;
    public String item_rate;
    public String item_category;
    public String item_image_url;

    public OrderInfo() {
    }
//    public String order_id;

    public OrderInfo(String item_name,String item_rate,String item_category,String item_image_url){
        this.item_name=item_name;
        this.item_rate=item_rate;
        this.item_category=item_category;
        this.item_image_url=item_image_url;
    }

    public String getitem_name(){
        return item_name;
    }
    public String getitem_category(){
        return item_category;
    }
    public String getitem_rate(){

        return item_rate;
    }
    public String getitem_image_url(){

        return item_image_url;
    }


    public void setitem_name(String item_name){

        this.item_name=item_name;

    }

    public void setitem_category(String item_category){

        this.item_category=item_category;

    }
    public void setitem_rate(String item_rate){

        this.item_rate=item_rate;

    }
    public void setitem_image_url(String item_image_url){

        this.item_image_url=item_image_url;
    }
}
