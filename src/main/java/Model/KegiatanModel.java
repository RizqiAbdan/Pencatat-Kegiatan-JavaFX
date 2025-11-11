package Model;

import java.time.LocalDate;


public class KegiatanModel {
    private LocalDate Tanggal;
    private String namaKegiatan;
    private String tempatKegiatan;

    public KegiatanModel(LocalDate Tanggal, String namaKegiatan, String tempatKegiatan) {
        this.Tanggal = Tanggal;
        this.namaKegiatan = namaKegiatan;
        this.tempatKegiatan = tempatKegiatan;
    }

    public LocalDate getTanggal() {
        return Tanggal;
    }

    public void setTanggal(LocalDate Tanggal) {
        this.Tanggal = Tanggal;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getTempatKegiatan() {
        return tempatKegiatan;
    }

    public void setTempatKegiatan(String tempatKegiatan) {
        this.tempatKegiatan = tempatKegiatan;
    }  
}