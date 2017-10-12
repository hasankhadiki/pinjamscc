package studio.tehhutan.pinjamscc.model;

/**
 * Created by tehhutan on 02/10/17.
 */

public class BookingList {
    private  String Departemen;
    private  String Kegiatan;
    private  String Nama;

    public BookingList() {
    }

    public BookingList(String departemen, String kegiatan, String nama) {
        Departemen = departemen;
        Kegiatan = kegiatan;
        Nama = nama;
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




}
