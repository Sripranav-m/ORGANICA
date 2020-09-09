package com.example.organica;

public class ItemInfo {
    public String seller_username;
    public String item_name;
    public String item_rate;
    public String available_units;
    public String item_category;
    public String item_image_url;
    public String item_buy_count;
    public String id;

    public ItemInfo(String seller_username,String item_name,String item_rate,String available_units,String item_category,String item_image_url,String item_buy_count,String id){
        this.seller_username=seller_username;
        this.item_name=item_name;
        this.item_rate=item_rate;
        this.available_units=available_units;
        this.item_category=item_category;
        this.item_image_url=item_image_url;
        this.item_buy_count=item_buy_count;
        this.id=id;
    }
    public String getseller_username(){
        return seller_username;
    }
    public String getid(){
        return id;
    }

    public String getitem_name(){
        return item_name;
    }
    public String getitem_buy_count(){
        return item_buy_count;
    }
    public String getavailable_units(){

        return available_units;
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
    public void setseller_username(String seller_username){
        this.seller_username=seller_username;
    }
    public void setid(String id){
        this.id=id;
    }
    public void setitem_name(String item_name){

        this.item_name=item_name;

    }
    public void setitem_buy_count(String item_buy_count){

        this.item_buy_count=item_buy_count;

    }
    public void setavailable_units(String available_units){

        this.available_units=available_units;

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
