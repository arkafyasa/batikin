package com.batikin.vocomfest.batikin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PemesananModel implements Parcelable {
    private String kategori;
    private String model;
    private String ukuran;
    private String motif;
    private String harga;

    public PemesananModel() {
    }

    public PemesananModel(String kategori, String model, String ukuran, String motif, String harga) {
        this.kategori = kategori;
        this.model = model;
        this.ukuran = ukuran;
        this.motif = motif;
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kategori);
        dest.writeString(this.model);
        dest.writeString(this.ukuran);
        dest.writeString(this.motif);
        dest.writeString(this.harga);
    }

    public PemesananModel(Parcel in) {
        this.kategori = in.readString();
        this.model = in.readString();
        this.ukuran = in.readString();
        this.motif = in.readString();
        this.harga = in.readString();
    }

    public static final Parcelable.Creator<PemesananModel> CREATOR = new Parcelable.Creator<PemesananModel>() {
        @Override
        public PemesananModel createFromParcel(Parcel source) {
            return new PemesananModel(source);
        }

        @Override
        public PemesananModel[] newArray(int size) {
            return new PemesananModel[size];
        }
    };
}
