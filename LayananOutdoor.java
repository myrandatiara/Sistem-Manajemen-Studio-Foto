package com.mycompany.sistemmanajemenstudiofoto;

public class LayananOutdoor extends LayananFoto implements DapatDireservasi {

    private double biayaTransportasi;

    public LayananOutdoor(String namaPaket, double harga, int durasiJam, double biayaTransportasi) {
        super(namaPaket, harga, durasiJam);
        setBiayaTransportasi(biayaTransportasi);
    }

    public double getBiayaTransportasi() {
        return this.biayaTransportasi;
    }

    public void setBiayaTransportasi(double biayaTransportasi) {
        if (biayaTransportasi >= 0) {
            this.biayaTransportasi = biayaTransportasi;
        } else {
            System.out.println("[Peringatan] Biaya transpor tidak boleh negatif! Diatur ke 0.");
            this.biayaTransportasi = 0;
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[Outdoor Session] Nama: %-18s | Harga: Rp %-10.2f | Durasi: %d Jam | Transpor: Rp %.2f\n",
                getNamaPaket(), getHarga(), getDurasiJam(), this.biayaTransportasi);
    }

    @Override
    public void cetakKetentuan() {
        System.out.println("-> Ketentuan Outdoor: Jika cuaca hujan deras, sesi foto dapat dijadwal ulang tanpa denda.");
    }
    
    @Override
    public void hitungBiayaLayanan() {
        double total = getHarga() + this.biayaTransportasi;
        System.out.printf("-> Total Rincian Biaya Outdoor: Rp %.2f (Paket: Rp %.2f + Transportasi: Rp %.2f)\n", 
                total, getHarga(), this.biayaTransportasi);
    }
    
    @Override
    public void prosesReservasi(String tanggal) {
        System.out.println("-> [RESERVASI OUTDOOR] Lokasi outdoor & tim dipesan untuk tanggal: " + tanggal);
    }

}