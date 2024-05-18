package kodeJava;

public class Konsul {
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
