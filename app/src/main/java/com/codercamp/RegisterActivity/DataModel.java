package com.codercamp.RegisterActivity;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties

public class DataModel implements Serializable {

    private String FullName,Email, Mobiles, Bold, Address;

    public DataModel() {
    }

    public DataModel(String name, String email, String mobiles, String bold,  String address) {
        FullName = name;
        Email = email;
        Mobiles = mobiles;
        Bold = bold;
        Address = address;
    }

    public void setName(String name) {
        FullName = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setMobiles(String mobiles) {
        Mobiles = mobiles;
    }

    public void setBold(String bold) {
        Bold = bold;
    }


    public void setAddress(String address) {
        Address = address;
    }

    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobiles() {
        return Mobiles;
    }

    public String getBold() {
        return Bold;
    }

    public String getAddress() {
        return Address;
    }
}
