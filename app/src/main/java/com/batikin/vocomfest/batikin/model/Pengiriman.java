package com.batikin.vocomfest.batikin.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Pengiriman implements Parcelable {
    private String etNama;
    private String etNomorTelp;
    private String etEmail;
    private String etAlamatPengirian;
    private String provinsi;
    private String kotakab;
    private String kec;
    private String kodePos;
    private String pengiriman;
    private String sudahSelesai;

    public Pengiriman() {
    }

    public Pengiriman(String etNama, String etNomorTelp, String etEmail, String etAlamatPengirian, String provinsi, String kotakab, String kec, String kodePos, String pengiriman, String sudahSelesai) {
        this.etNama = etNama;
        this.etNomorTelp = etNomorTelp;
        this.etEmail = etEmail;
        this.etAlamatPengirian = etAlamatPengirian;
        this.provinsi = provinsi;
        this.kotakab = kotakab;
        this.kec = kec;
        this.kodePos = kodePos;
        this.pengiriman = pengiriman;
        this.sudahSelesai = sudahSelesai;
    }

    public String getEtNama() {
        return etNama;
    }

    public void setEtNama(String etNama) {
        this.etNama = etNama;
    }

    public String getEtNomorTelp() {
        return etNomorTelp;
    }

    public void setEtNomorTelp(String etNomorTelp) {
        this.etNomorTelp = etNomorTelp;
    }

    public String getEtEmail() {
        return etEmail;
    }

    public void setEtEmail(String etEmail) {
        this.etEmail = etEmail;
    }

    public String getEtAlamatPengirian() {
        return etAlamatPengirian;
    }

    public void setEtAlamatPengirian(String etAlamatPengirian) {
        this.etAlamatPengirian = etAlamatPengirian;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKotakab() {
        return kotakab;
    }

    public void setKotakab(String kotakab) {
        this.kotakab = kotakab;
    }

    public String getKec() {
        return kec;
    }

    public void setKec(String kec) {
        this.kec = kec;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getPengiriman() {
        return pengiriman;
    }

    public void setPengiriman(String pengiriman) {
        this.pengiriman = pengiriman;
    }

    public String getSudahSelesai() {
        return sudahSelesai;
    }

    public void setSudahSelesai(String sudahSelesai) {
        this.sudahSelesai = sudahSelesai;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.etNama);
        dest.writeString(this.etNomorTelp);
        dest.writeString(this.etEmail);
        dest.writeString(this.etAlamatPengirian);
        dest.writeString(this.provinsi);
        dest.writeString(this.kotakab);
        dest.writeString(this.kec);
        dest.writeString(this.kodePos);
        dest.writeString(this.pengiriman);
        dest.writeString(this.sudahSelesai);
    }

    protected Pengiriman(Parcel in) {
        this.etNama = in.readString();
        this.etNomorTelp = in.readString();
        this.etEmail = in.readString();
        this.etAlamatPengirian = in.readString();
        this.provinsi = in.readString();
        this.kotakab = in.readString();
        this.kec = in.readString();
        this.kodePos = in.readString();
        this.pengiriman = in.readString();
        this.sudahSelesai = in.readString();
    }

    public static final Parcelable.Creator<Pengiriman> CREATOR = new Parcelable.Creator<Pengiriman>() {
        @Override
        public Pengiriman createFromParcel(Parcel source) {
            return new Pengiriman(source);
        }

        @Override
        public Pengiriman[] newArray(int size) {
            return new Pengiriman[size];
        }
    };
}
