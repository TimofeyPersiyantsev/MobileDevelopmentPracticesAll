package com.example.applicationallpz.models;

import android.os.Parcel;
import android.os.Parcelable;

// Parcelable версия
public class UserParcelable implements Parcelable {
    private String name;
    private String company;
    private int age;
    private String phone;

    public UserParcelable(String name, String company, int age, String phone) {
        this.name = name;
        this.company = company;
        this.age = age;
        this.phone = phone;
    }

    protected UserParcelable(Parcel in) {
        name = in.readString();
        company = in.readString();
        age = in.readInt();
        phone = in.readString();
    }

    public static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel in) {
            return new UserParcelable(in);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(company);
        dest.writeInt(age);
        dest.writeString(phone);
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getCompany() { return company; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
}
