package kodeJava;

import android.os.Parcel;
import android.os.Parcelable;

public class Konsul implements Parcelable {
    String idKonsul, penyakit, gejala, solusi, resepObat, tingkatStress, tanggal, namaPasien;

    public Konsul() {
    }

    public Konsul(String idKonsul, String penyakit, String gejala, String solusi, String resepObat, String tingkatStress, String tanggal, String namaPasien) {
        this.penyakit = penyakit;
        this.gejala = gejala;
        this.solusi = solusi;
        this.resepObat = resepObat;
        this.tingkatStress = tingkatStress;
        this.tanggal = tanggal;
        this.namaPasien = namaPasien;
        this.idKonsul = idKonsul;
    }

    protected Konsul(Parcel in) {
        idKonsul = in.readString();
        penyakit = in.readString();
        gejala = in.readString();
        solusi = in.readString();
        resepObat = in.readString();
        tingkatStress = in.readString();
        tanggal = in.readString();
        namaPasien = in.readString();
    }

    public static final Creator<Konsul> CREATOR = new Creator<Konsul>() {
        @Override
        public Konsul createFromParcel(Parcel in) {
            return new Konsul(in);
        }

        @Override
        public Konsul[] newArray(int size) {
            return new Konsul[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idKonsul);
        dest.writeString(penyakit);
        dest.writeString(gejala);
        dest.writeString(solusi);
        dest.writeString(resepObat);
        dest.writeString(tingkatStress);
        dest.writeString(tanggal);
        dest.writeString(namaPasien);
    }

    // Getter dan Setter...

    public String getPenyakit() {
        return penyakit;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }

    public String getResepObat() {
        return resepObat;
    }

    public void setResepObat(String resepObat) {
        this.resepObat = resepObat;
    }

    public String getTingkatStress() {
        return tingkatStress;
    }

    public void setTingkatStress(String tingkatStress) {
        this.tingkatStress = tingkatStress;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaPasien() {
        return namaPasien;
    }
    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getIdKonsul() {
        return idKonsul;
    }

    public void setIdKonsul(String idKonsul) {
        this.idKonsul = idKonsul;
    }
}
