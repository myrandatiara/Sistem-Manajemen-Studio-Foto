package com.mycompany.sistemmanajemenstudiofoto;
import java.util.Scanner;

public class SistemManajemenStudioFoto {

    public static void cariLayanan(String nama, LayananFoto[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Pencarian dengan Nama Paket: \"" + nama + "\" ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getNamaPaket().equalsIgnoreCase(nama)) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                daftar[i].cetakKetentuan();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Paket layanan tidak ditemukan.");
        }
    }

    public static void cariLayanan(double hargaMax, LayananFoto[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Filter Layanan dengan Harga <= Rp " + String.format("%.2f", hargaMax) + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getHarga() <= hargaMax) {
                System.out.print("- ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada layanan dengan harga di bawah Rp " + String.format("%.2f", hargaMax));
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LayananFoto[] daftarLayanan = new LayananFoto[15];
            int jumlahLayanan = 0;
            boolean isRunning = true;

            daftarLayanan[jumlahLayanan++] = new LayananStudio("Wisuda Ceria", 250000, 1, "Vintage Wood");
            daftarLayanan[jumlahLayanan++] = new LayananStudio("Foto Family Premium", 500000, 2, "Minimalis Modern");
            daftarLayanan[jumlahLayanan++] = new LayananOutdoor("Prewedding Nature", 1200000, 4, 150000);
            daftarLayanan[jumlahLayanan++] = new LayananOutdoor("Street Portrait", 350000, 2, 50000);

            System.out.println("=================================================");
            System.out.println("    SELAMAT DATANG DI SISTEM STUDIO FOTO KREATIF ");
            System.out.println("          (Aplikasi Manajemen Layanan)          ");
            System.out.println("=================================================");

            while (isRunning) {
                System.out.println("\nMENU UTAMA:");
                System.out.println("1. Tambah Paket Layanan Foto Baru");
                System.out.println("2. Tampilkan Seluruh Daftar Layanan Foto");
                System.out.println("3. Cari / Filter Layanan (Fitur Overloading)");
                System.out.println("4. Keluar");
                System.out.print("Pilih Menu (1-4): ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        if (jumlahLayanan < daftarLayanan.length) {
                            System.out.println("\n-- Pilih Tipe Subclass Layanan --");
                            System.out.println("1. Layanan Studio Indoor");
                            System.out.println("2. Layanan Outdoor Session");
                            System.out.print("Pilihan (1/2): ");

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
                                daftarLayanan[jumlahLayanan++] = new LayananStudio(namaPaket, harga, durasi, backdrop);
                                System.out.println("[Sukses] Layanan Studio Indoor berhasil ditambahkan!");
                            } else if (jenis == 2) {
                                System.out.print("Masukkan Biaya Transportasi Tambahan (Rp): ");
                                double transpor = scanner.nextDouble();
                                scanner.nextLine();
                                daftarLayanan[jumlahLayanan++] = new LayananOutdoor(namaPaket, harga, durasi, transpor);
                                System.out.println("[Sukses] Layanan Outdoor Session berhasil ditambahkan!");
                            } else {
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

                        if (jumlahLayanan == 0) {
                            System.out.println("Belum ada layanan foto yang terdaftar.");
                        } else {
                            for (int i = 0; i < jumlahLayanan; i++) {
                                System.out.print((i + 1) + ". ");
                                daftarLayanan[i].tampilkanInfo();
                                daftarLayanan[i].cetakKetentuan();
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
                            cariLayanan(kataKunci, daftarLayanan, jumlahLayanan);
                        } else if (modeCari == 2) {
                            System.out.print("Masukkan Batas Harga Maksimal (Rp): ");
                            double hargaKunci = scanner.nextDouble();
                            scanner.nextLine();
                            cariLayanan(hargaKunci, daftarLayanan, jumlahLayanan);
                        } else {
                            System.out.println("Pilihan mode pencarian tidak valid.");
                        }

                        System.out.print("\nTekan Enter untuk kembali ke menu...");
                        scanner.nextLine();
                    }

                    case 4 -> {
                        System.out.println("Terima kasih telah menggunakan Sistem Studio Foto!");
                        isRunning = false;
                    }

                    default -> System.out.println("Pilihan tidak valid. Silakan pilih angka 1-4.");
                }
            }
        }
    }
}