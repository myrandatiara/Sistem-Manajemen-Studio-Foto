package com.mycompany.sistemmanajemenstudiofoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemManajemenStudioFoto {
    
    public static void simulasiTransaksi(LayananFoto item){
        System.out.println("\n SIMULASI PROSES KETENTUAN LAYANAN ");
        item.tampilkanInfo();
        item.cetakKetentuan();
        System.out.println("-------------------------------------");
    }

    public static void cariLayanan(String nama, ArrayList<LayananFoto> daftar) throws LayananTidakDitemukanException {
        System.out.println("\n--- Hasil Pencarian dengan Nama Paket: \"" + nama + "\" ---");
        boolean ditemukan = false;
        for (LayananFoto item : daftar) {
            if (item.getNamaPaket().equalsIgnoreCase(nama)) {
                System.out.print("- Ditemukan: ");
                item.tampilkanInfo();
                item.cetakKetentuan();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new LayananTidakDitemukanException("Gagal: Layanan foto dengan nama '" + nama + "' tidak ditemukan di sistem!");
        }
    }

    public static void cariLayanan(double hargaMax, ArrayList<LayananFoto> daftar) {
        System.out.println("\n--- Hasil Filter Layanan dengan Harga <= Rp " + String.format("%.2f", hargaMax) + " ---");
        boolean ditemukan = false;
        for (LayananFoto item : daftar) {
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

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ArrayList<LayananFoto> daftarLayanan = new ArrayList<>();
            HashMap<String, String> dataPelanggan = new HashMap<>();
            boolean isRunning = true;
            
            try{
                daftarLayanan.add( new LayananStudio("Wisuda Ceria", 250000, 1, "Vintage Wood"));
                daftarLayanan.add( new LayananStudio("Foto Family Premium", 500000, 2, "Minimalis Modern"));
                daftarLayanan.add( new LayananOutdoor("Prewedding Nature", 1200000, 4, 150000));
                daftarLayanan.add( new LayananDokumentasi("Wedding Day Full", 3500000, 8, 3));
            } catch (Exception e) {
                System.out.println("Gagal menambahkan data awal: " + e.getMessage());
            }
            dataPelanggan.put("PLG001", "Ahmad Kholifah");
            dataPelanggan.put("PLG002", "Tiara Mira");
            dataPelanggan.put("PLG003", "Bunga Susanti");

            System.out.println("=================================================");
            System.out.println("    SELAMAT DATANG DI SISTEM STUDIO FOTO KREATIF ");
            System.out.println("          (Aplikasi Manajemen Layanan)          ");
            System.out.println("=================================================");

            while (isRunning) {
                System.out.println("\nMENU UTAMA:");
                System.out.println("1. Tambah Paket Layanan Foto Baru");
                System.out.println("2. Tampilkan Seluruh Daftar Layanan Foto");
                System.out.println("3. Cari / Filter Layanan (Fitur Overloading)");
                System.out.println("4. Simulasi Layanan Transaksi");
                System.out.println("5. Data Pelanggan (HashMap)");
                System.out.println("6. Keluar");
                System.out.print("Pilih Menu (1-6): ");
                
                try{ 
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();

                    switch (pilihan) {
                        case 1 -> {
                            if (daftarLayanan.size() < 15) {
                                System.out.println("\n-- Pilih Tipe Subclass Layanan --");
                                System.out.println("1. Layanan Studio Indoor");
                                System.out.println("2. Layanan Outdoor Session");
                                System.out.println("3. Layanan Dokumentasi Event");
                                System.out.print("Pilihan (1/2/3): ");

                                int jenis = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Masukkan Nama Paket Foto: ");
                                String namaPaket = scanner.nextLine();

                                System.out.print("Masukkan Harga Paket (Rp): ");
                                double harga = scanner.nextDouble();

                                System.out.print("Masukkan Durasi (Jam): ");
                                int durasi = scanner.nextInt();
                                scanner.nextLine();

                                if (jenis == 1) {
                                    System.out.print("Masukkan Theme/Jenis Backdrop Studio: ");
                                    String backdrop = scanner.nextLine();
                                    daftarLayanan.add( new LayananStudio(namaPaket, harga, durasi, backdrop));
                                    System.out.println("[Sukses] Layanan Studio Indoor berhasil ditambahkan!");
                                } else if (jenis == 2) {
                                    System.out.print("Masukkan Biaya Transportasi Tambahan (Rp): ");
                                    double transpor = scanner.nextDouble();
                                    scanner.nextLine();
                                    daftarLayanan.add( new LayananOutdoor(namaPaket, harga, durasi, transpor));
                                    System.out.println("[Sukses] Layanan Outdoor Session berhasil ditambahkan!");
                                }else if (jenis == 3) {
                                    System.out.print("Masukkan Jumlah Kamera yang Digunakan: ");
                                    int kamera = scanner.nextInt();
                                    scanner.nextLine();
                                    daftarLayanan.add( new LayananDokumentasi(namaPaket, harga, durasi, kamera));
                                    System.out.println("[Sukses] Layanan Dokumentasi Event berhasil ditambahkan!");
                                }else {
                                    System.out.println("Tipe layanan tidak valid! Penambahan dibatalkan.");
                                }
                            } else {
                                System.out.println("Maaf, kapasitas penyimpanan daftar layanan sudah penuh!");
                            }
                        }

                        case 2 -> {
                            System.out.println("\n==========================================================================================");
                            System.out.println("                               DAFTAR LAYANAN STUDIO FOTO                                 ");
                            System.out.println("==========================================================================================");

                            if (daftarLayanan.isEmpty()) {
                                System.out.println("Belum ada layanan foto yang terdaftar.");
                            } else {
                                for (int i = 0; i < daftarLayanan.size(); i++) {
                                    System.out.print((i + 1) + ". ");
                                    daftarLayanan.get(i).tampilkanInfo();
                                    daftarLayanan.get(i).cetakKetentuan();
                                    daftarLayanan.get(i).hitungBiayaLayanan();

                                    if (daftarLayanan.get(i) instanceof DapatDireservasi) {
                                        DapatDireservasi itemReservasi = (DapatDireservasi) daftarLayanan.get(i);
                                        itemReservasi.prosesReservasi("25 Oktober 2026");
                                    }
                                    if (daftarLayanan.get(i) instanceof DapatDinilai) {
                                        DapatDinilai itemBisaDinilai = (DapatDinilai) daftarLayanan.get(i);
                                        itemBisaDinilai.beriRating(5);
                                    }

                                    System.out.println("------------------------------------------------------------------------------------------");
                                }
                                System.out.println("\n* Total Objek Layanan yang Berhasil Dibuat: " + LayananFoto.getTotalLayananBerhasilDibuat());
                            }

                            System.out.print("\nTekan Enter untuk kembali ke menu...");
                            scanner.nextLine();
                        }

                        case 3 -> {
                            System.out.println("\n-- Fitur Cari & Filter Layanan (Method Overloading) --");
                            System.out.println("1. Cari berdasarkan Nama Paket (String)");
                            System.out.println("2. Filter berdasarkan Batas Harga Maksimal (Double)");
                            System.out.print("Pilih opsi (1/2): ");

                            int modeCari = scanner.nextInt();
                            scanner.nextLine();

                            if (modeCari == 1) {
                                System.out.print("Masukkan Nama Paket yang dicari: ");
                                String kataKunci = scanner.nextLine();
                                try{
                                    cariLayanan(kataKunci, daftarLayanan);
                                }catch (LayananTidakDitemukanException e) {
                                    System.out.println("[PERINGATAN EXCEPTION] " + e.getMessage());
                                } finally {
                                    System.out.println("-> Sesi pencarian nama paket telah selesai dieksekusi.");
                                }
                            } else if (modeCari == 2) {
                                System.out.print("Masukkan Batas Harga Maksimal (Rp): ");
                                double hargaKunci = scanner.nextDouble();
                                scanner.nextLine();
                                cariLayanan(hargaKunci, daftarLayanan);
                            } else {
                                System.out.println("Pilihan mode pencarian tidak valid.");
                            }
                            System.out.print("\nTekan Enter untuk kembali ke menu...");
                            scanner.nextLine();
                        }

                        case 4 -> {
                            System.out.println("\n-- Simulasi Transaksi Layanan --");
                            if (daftarLayanan.isEmpty()) {
                                System.out.println("Belum ada layanan foto yang terdaftar.");
                            } else {
                                for (int i = 0; i < daftarLayanan.size(); i++) {
                                    System.out.println((i + 1) + ". " + daftarLayanan.get(i).getNamaPaket());
                                }
                                System.out.print("Pilih nomor paket untuk disimulasikan (1-" + daftarLayanan.size() + "): ");
                                int idx = scanner.nextInt() - 1;
                                scanner.nextLine();

                                if (idx >= 0 && idx < daftarLayanan.size()) {
                                    simulasiTransaksi(daftarLayanan.get(idx));
                                } else {
                                    System.out.println("Nomor paket tidak valid!");
                                }
                            System.out.print("\nTekan Enter untuk kembali ke menu...");
                            scanner.nextLine();
                            }
                        }
                        case 5 -> {
                            System.out.println("\n-- Kelola Data Pelanggan (Demo HashMap) --");
                            System.out.println("1. Tambah Pelanggan Baru");
                            System.out.println("2. Cari Pelanggan via ID");
                            System.out.println("3. Tampilkan Semua Pelanggan");
                            System.out.print("Pilih (1-3): ");

                            int menuPelanggan = scanner.nextInt();
                            scanner.nextLine();

                            if (menuPelanggan == 1) {
                                System.out.print("Masukkan ID Pelanggan (Key): ");
                                String id = scanner.nextLine();
                                System.out.print("Masukkan Nama Pelanggan (Value): ");
                                String nama = scanner.nextLine();
                                dataPelanggan.put(id, nama);
                                System.out.println("Berhasil menambahkan pelanggan!");
                            } else if (menuPelanggan == 2) {
                                System.out.print("Masukkan ID Pelanggan yang dicari: ");
                                String searchId = scanner.nextLine();
                                if (dataPelanggan.containsKey(searchId)) {
                                    System.out.println("Ditemukan! Nama Pelanggan: " + dataPelanggan.get(searchId));
                                } else {
                                    System.out.println("Pelanggan dengan ID tersebut tidak terdaftar.");
                                }
                            } else if (menuPelanggan == 3) {
                                System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
                                for (String keyId : dataPelanggan.keySet()) {
                                    System.out.println("- ID: " + keyId + " | Nama: " + dataPelanggan.get(keyId));
                                }
                            }

                            System.out.print("\nTekan Enter untuk kembali ke menu...");
                            scanner.nextLine();
                        }
                        case 6 -> {
                            System.out.println("Terima kasih telah menggunakan Sistem Studio Foto!");
                            isRunning = false;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n[ERROR SYSTEM] Input tidak valid! Anda harus memasukkan angka.");
                    scanner.nextLine(); // Membersihkan buffer scanner
                } catch (Exception e) {
                    System.out.println("\n[ERROR SYSTEM] Terjadi kesalahan: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}