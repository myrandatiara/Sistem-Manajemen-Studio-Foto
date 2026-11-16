package com.mycompany.sistemmanajemenstudiofoto;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

public class StudioFoto {
    private String namaStudio;
    private RuangStudio ruangUtama;
    private ArrayList<LayananFoto> daftarLayanan;
    private HashMap<String, Pelanggan> dataPelanggan;

    public StudioFoto(String namaStudio) {
        this.namaStudio = namaStudio;
        this.ruangUtama = new RuangStudio("Ruang Studio Utama (Indoor)", 10);
        this.daftarLayanan = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
    }

    public void bukaStudio() {
        System.out.println("\nMenginisialisasi Sistem " + this.namaStudio + "");
        ruangUtama.bukaRuangan();
    }

    public void tambahLayanan(LayananFoto l) {
        daftarLayanan.add(l);
        System.out.println("[Sukses] Paket layanan berhasil ditambahkan!");
    }

    public void lihatSemuaLayanan() {
        if (daftarLayanan.isEmpty()) {
            System.out.println("Belum ada layanan foto yang terdaftar.");
            return;
        }
        for (int i = 0; i < daftarLayanan.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarLayanan.get(i).tampilkanInfo();
            daftarLayanan.get(i).cetakKetentuan();
            daftarLayanan.get(i).hitungBiayaLayanan();
            System.out.println("------------------------------------------------------------------------------------------");
        }
    }

    public void cariLayanan(String nama) throws LayananTidakDitemukanException {
        System.out.println("\n--- Hasil Pencarian dengan Nama Paket: \"" + nama + "\" ---");
        boolean ditemukan = false;
        for (LayananFoto item : daftarLayanan) {
            if (item.getNamaPaket().equalsIgnoreCase(nama)) {
                System.out.print("- Ditemukan: ");
                item.tampilkanInfo();
                item.cetakKetentuan();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new LayananTidakDitemukanException("Gagal: Layanan foto dengan nama '" + nama + "' tidak ditemukan!");
        }
    }

    public void cariLayanan(double hargaMax) {
        System.out.println("\n--- Hasil Filter Layanan dengan Harga <= Rp " + String.format("%.2f", hargaMax) + " ---");
        boolean ditemukan = false;
        for (LayananFoto item : daftarLayanan) {
            if (item.getHarga() <= hargaMax) {
                System.out.print("- ");
                item.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada layanan dengan harga di bawah Rp " + String.format("%.2f", hargaMax));
        }
    }

    public void tambahPelanggan(Pelanggan p) {
        dataPelanggan.put(p.getIdPelanggan(), p);
        System.out.println("Berhasil menambahkan pelanggan!");
    }

    public void cariPelanggan(String id) {
        if (dataPelanggan.containsKey(id)) {
            System.out.println("Ditemukan. Nama Pelanggan: " + dataPelanggan.get(id).getNama());
        } else {
            System.out.println("Pelanggan dengan ID tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaPelanggan() {
        System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
        for (Pelanggan p : dataPelanggan.values()) {
            System.out.println("- ID: " + p.getIdPelanggan() + " | Nama: " + p.getNama());
        }
    }

    public Pelanggan getPelanggan(String id) { return dataPelanggan.get(id); }
    public LayananFoto getLayananInt(int index) { return daftarLayanan.get(index); }
    public int getJumlahLayanan() { return daftarLayanan.size(); }
    
    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream("data_studio.dat");
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);
            mesinPembeku.writeObject(this.daftarLayanan);
            mesinPembeku.writeObject(this.dataPelanggan);
            mesinPembeku.close();
            System.out.println("-> [SYSTEM LOG] Seluruh data berhasil disimpan (Serialization) ke dalam Harddisk!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public boolean muatDataDariFile() {
        try {
            File cekFile = new File("data_studio.dat");
            if (!cekFile.exists()) return false;

            FileInputStream fileMasuk = new FileInputStream(cekFile);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);
            this.daftarLayanan = (ArrayList<LayananFoto>) mesinPembangkit.readObject();
            this.dataPelanggan = (HashMap<String, Pelanggan>) mesinPembangkit.readObject();
            LayananFoto.setTotalLayananBerhasilDibuat(this.daftarLayanan.size());
            mesinPembangkit.close();
            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang dari penyimpanan.");
            return true;
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
            return false;
        }
    }

    public void bacaLogTransaksi() {
        System.out.println("\n--- Riwayat Log Transaksi ---");
        try {
            File fileLog = new File("log_transaksi.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada log transaksi studio foto.");
                return;
            }
            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log.");
        }
    }
}