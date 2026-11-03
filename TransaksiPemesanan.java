package com.mycompany.sistemmanajemenstudiofoto;

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
    }
}