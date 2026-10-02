package com.mycompany.sistemmanajemenstudiofoto;
public class LayananDokumentasi extends LayananFoto {
    private int jumlahKamera;

    public LayananDokumentasi(String namaPaket, double harga, int durasiJam, int jumlahKamera) {
        super(namaPaket, harga, durasiJam);
        setJumlahKamera(jumlahKamera);
    }

    public int getJumlahKamera() {
        return this.jumlahKamera;
    }

    public void setJumlahKamera(int jumlahKamera) {
        if (jumlahKamera > 0) {
            this.jumlahKamera = jumlahKamera;
        } else {
            System.out.println("[Peringatan] Jumlah kamera minimal 1! Diatur ke default 1.");
            this.jumlahKamera = 1;
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Dokumentasi Event] Nama: %-16s | Harga: Rp %-10.2f | Durasi: %d Jam | Kamera: %d Unit\n",
                getNamaPaket(), getHarga(), getDurasiJam(), this.jumlahKamera);
    }

    @Override
    public void cetakKetentuan() {
        System.out.println("-> Ketentuan Dokumentasi: Meliputi foto & video. File mentah diserahkan via Flashdisk/Drive.");
    }
}