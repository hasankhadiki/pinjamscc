package studio.tehhutan.pinjamscc.model;

/**
 * Created by tehhutan on 02/10/17.
 */

public class BookingList {
    private String Departemen;
    private String Kegiatan;
    private String Nama;
    private String JamMulai;
    private String JamAkhir;

    public BookingList() {
    }

    public String getDepartemen() {
        return Departemen;
    }

    public void setDepartemen(String departemen) {
        Departemen = departemen;
    }

    public String getKegiatan() {
        return Kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        Kegiatan = kegiatan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJamMulai() {
        return JamMulai;
    }

    public void setJamMulai(String jamMulai) {
        JamMulai = jamMulai;
    }

    public String getJamAkhir() {
        return JamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        JamAkhir = jamAkhir;
    }

    public BookingList(String departemen, String kegiatan, String nama, String jamMulai, String jamAkhir) {

        Departemen = departemen;
        Kegiatan = kegiatan;
        Nama = nama;
        JamMulai = jamMulai;
        JamAkhir = jamAkhir;
    }
}
