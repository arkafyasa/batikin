package com.batikin.vocomfest.batikin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String nama;
    private String email;
    private String password;
    private String photo;

    public User() {

    }

    public User(String nama, String email, String password, String photo) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.photo);
    }

    protected User(Parcel in) {
        this.nama = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
