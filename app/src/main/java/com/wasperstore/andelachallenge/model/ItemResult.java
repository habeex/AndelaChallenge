package com.wasperstore.andelachallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Habeex on 4/20/2017.
 */

public class ItemResult {
    //get items on APIData into List
    @SerializedName("items")
    @Expose
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
