package kodeJava;

public class Pasien {

    private String nama, email, umur, jenkel, tanggalDibuat;


    public Pasien() {
    }

    public Pasien(String nama, String email, String umur, String jenkel, String tanggalDibuat) {
        this.nama = nama;
        this.email = email;
        this.umur = umur;
        this.jenkel = jenkel;
        this.tanggalDibuat = tanggalDibuat;
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

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }
    public String getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(String tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

}
