package kodeJava;

public class Konsul {
    String penyakit, gejala, solusi, resepObat, tingkatStress, tanggal;

    public Konsul() {
    }

    public Konsul(String penyakit, String gejala, String solusi, String resepObat, String tingkatStress, String tanggal) {
        this.penyakit = penyakit;
        this.gejala = gejala;
        this.solusi = solusi;
        this.resepObat = resepObat;
        this.tingkatStress = tingkatStress;
        this.tanggal = tanggal;
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
}
