package com.example.applicationallpz.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

// Serializable версия
public class UserSerializable implements Serializable {
    private String name;
    private String company;
    private int age;
    private String phone; // новое поле

    public UserSerializable(String name, String company, int age, String phone) {
        this.name = name;
        this.company = company;
        this.age = age;
        this.phone = phone;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

