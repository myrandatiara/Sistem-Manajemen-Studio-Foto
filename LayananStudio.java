package com.mycompany.sistemmanajemenstudiofoto;

public class LayananStudio extends LayananFoto {
    private String namaBackdrop;

    public LayananStudio(String namaPaket, double harga, int durasiJam, String namaBackdrop) {
        super(namaPaket, harga, durasiJam);
        setNamaBackdrop(namaBackdrop);
    }

    public String getNamaBackdrop() {
        return this.namaBackdrop;
    }

    public void setNamaBackdrop(String namaBackdrop) {
        if (namaBackdrop != null && !namaBackdrop.trim().isEmpty()) {
            this.namaBackdrop = namaBackdrop;
        } else {
            this.namaBackdrop = "Polos Putih";
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Studio Indoor]  Nama: %-18s | Harga: Rp %-10.2f | Durasi: %d Jam | Backdrop: %s\n",
                getNamaPaket(), getHarga(), getDurasiJam(), this.namaBackdrop);
    }

    @Override
    public void cetakKetentuan() {
        System.out.println("-> Ketentuan Studio: Maksimal 5 orang dalam studio, wajib melayani reservasi via jadwal indoor.");
    }
}