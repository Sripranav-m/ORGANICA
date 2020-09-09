package com.example.organica;

public class BuyedHelper implements Comparable<BuyedHelper> {
    String item_image_url;
    String item_name;
    int item_buy_count;

    public BuyedHelper() {
    }

    public BuyedHelper(String item_image_url, String item_name, int item_buy_count) {
        this.item_image_url = item_image_url;
        this.item_name = item_name;
        this.item_buy_count = item_buy_count;
    }

    public String getItem_image_url() {
        return item_image_url;
    }

    public void setItem_image_url(String item_image_url) {
        this.item_image_url = item_image_url;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_buy_count() {
        return item_buy_count;
    }

    public void setItem_buy_count(int item_buy_count) {
        this.item_buy_count = item_buy_count;
    }

    @Override
    public int compareTo(BuyedHelper buyedHelper) {
        return buyedHelper.getItem_buy_count() - this.item_buy_count;
    }
}