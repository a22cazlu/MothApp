package com.example.mothapp;

import com.google.gson.annotations.SerializedName;

public class Moth {
    private String ID;
    private String name;
    private String type;
    @SerializedName("company")
    private String family;
    private String location;
    @SerializedName("category")
    private String wings;
    private int size;
    private int cost;
    private Auxdata auxdata;

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getLocation() {
        return location;
    }

    public String getWings() {
        return wings;
    }

    /*@Override
    public String toString(){
        return name;
    }*/

}

