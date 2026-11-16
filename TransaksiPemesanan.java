package com.mycompany.sistemmanajemenstudiofoto;
import java.io.FileWriter;
import java.io.IOException;

public class TransaksiPemesanan {
    public static void prosesPinjam(Pelanggan pemesan, LayananFoto item) {
        System.out.println("\n===== STRUK TRANSAKSI PEMESANAN =====");
        System.out.println("Pelanggan : " + pemesan.getNama() + " (" + pemesan.getIdPelanggan() + ")");
        System.out.println("Item Paket: " + item.getNamaPaket());
        System.out.println("Status    : Berhasil dicatat di sistem.");
        
        item.hitungBiayaLayanan();
        if (item instanceof DapatDireservasi) {
            ((DapatDireservasi) item).prosesReservasi("8 November 2026");
        }
        System.out.println("=====================================");
        try (FileWriter pipaTulis = new FileWriter("log_transaksi.txt", true)) {
            String log = "Pelanggan: " + pemesan.getNama() + " (" + pemesan.getIdPelanggan() + ") | Paket: " + item.getNamaPaket() + " | Harga: Rp " + item.getHarga() + "\n";
            pipaTulis.write(log);
        } catch (IOException e) {
            System.out.println("Peringatan: Gagal menulis log transaksi ke file. " + e.getMessage());
        }
    }
}