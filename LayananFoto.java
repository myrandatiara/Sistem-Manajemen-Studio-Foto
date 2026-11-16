package com.mycompany.sistemmanajemenstudiofoto;
import java.io.Serializable;

public abstract class LayananFoto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String namaPaket;
    private double harga;
    private int durasiJam;
    private static int totalLayananBerhasilDibuat = 0;

    public LayananFoto(String namaPaket, double harga, int durasiJam) throws Exception {
        if (harga <= 0) {
            throw new Exception("Harga paket harus lebih dari Rp 0!");
        }
        if (durasiJam <= 0) {
            throw new Exception("Durasi jam layanan minimal 1 jam!");
        }
        if (namaPaket == null || namaPaket.trim().isEmpty()) {
            throw new Exception("Nama paket tidak boleh kosong!");
        }
        
        this.namaPaket = namaPaket;
        this.harga = harga;
        this.durasiJam = durasiJam;
        totalLayananBerhasilDibuat++;
    }

    public static int getTotalLayananBerhasilDibuat() {
        return totalLayananBerhasilDibuat;
    }
    
    public static void setTotalLayananBerhasilDibuat(int total) {
        totalLayananBerhasilDibuat = total;
    }

    public String getNamaPaket() {
        return this.namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        if (namaPaket != null && !namaPaket.trim().isEmpty()) {
            this.namaPaket = namaPaket;
        } else {
            this.namaPaket = "Paket Standar";
        }
    }

    public double getHarga() {
        return this.harga;
    }

    public void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        } else {
            System.out.println("[Peringatan] Harga harus lebih dari 0! Diatur ke default Rp 100.000");
            this.harga = 100000;
        }
    }

    public int getDurasiJam() {
        return this.durasiJam;
    }

    public void setDurasiJam(int durasiJam) {
        if (durasiJam > 0) {
            this.durasiJam = durasiJam;
        } else {
            System.out.println("[Peringatan] Durasi harus minimal 1 jam! Diatur ke default 1 jam.");
            this.durasiJam = 1;
        }
    }


    public void tampilkanInfo() {
        System.out.printf("Nama Paket: %-20s | Harga: Rp %-10.2f | Durasi: %d Jam\n", 
                this.namaPaket, this.harga, this.durasiJam);
    }

    public void cetakKetentuan() {
        System.out.println("-> Ketentuan Umum: Pembatalan H-1 dikenakan denda 50%.");
    }
    public abstract void hitungBiayaLayanan();
}