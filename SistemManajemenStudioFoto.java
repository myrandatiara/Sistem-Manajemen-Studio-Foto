package com.mycompany.sistemmanajemenstudiofoto;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemManajemenStudioFoto {
    
    public static void simulasiTransaksi(LayananFoto item){
        System.out.println("\n SIMULASI PROSES KETENTUAN LAYANAN ");
        item.tampilkanInfo();
        item.cetakKetentuan();
        System.out.println("-------------------------------------");
    }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            StudioFoto studio = new StudioFoto("Studio Foto Kreatif");
            studio.bukaStudio();
            
            try{
                studio.tambahLayanan( new LayananStudio("Wisuda Ceria", 250000, 1, "Vintage Wood"));
                studio.tambahLayanan( new LayananStudio("Foto Family Premium", 500000, 2, "Minimalis Modern"));
                studio.tambahLayanan( new LayananOutdoor("Prewedding Nature", 1200000, 4, 150000));
                studio.tambahLayanan( new LayananDokumentasi("Wedding Day Full", 3500000, 8, 3));
            } catch (Exception e) {
                System.out.println("Gagal menambahkan data awal: " + e.getMessage());
            }
            studio.tambahPelanggan(new Pelanggan("PLG001", "Ahmad Kholifah"));
            studio.tambahPelanggan(new Pelanggan("PLG002", "Tiara Mira"));
            studio.tambahPelanggan(new Pelanggan("PLG003", "Bunga Susanti"));
            
            boolean isRunning = true;

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
                                studio.tambahLayanan( new LayananStudio(namaPaket, harga, durasi, backdrop));
                                System.out.println("[Sukses] Layanan Studio Indoor berhasil ditambahkan!");
                            } else if (jenis == 2) {
                                System.out.print("Masukkan Biaya Transportasi Tambahan (Rp): ");
                                double transpor = scanner.nextDouble();
                                scanner.nextLine();
                                studio.tambahLayanan( new LayananOutdoor(namaPaket, harga, durasi, transpor));
                                System.out.println("[Sukses] Layanan Outdoor Session berhasil ditambahkan!");
                            }else if (jenis == 3) {
                                System.out.print("Masukkan Jumlah Kamera yang Digunakan: ");
                                int kamera = scanner.nextInt();
                                scanner.nextLine();
                                studio.tambahLayanan( new LayananDokumentasi(namaPaket, harga, durasi, kamera));
                                System.out.println("[Sukses] Layanan Dokumentasi Event berhasil ditambahkan!");
                            }else {
                                System.out.println("Tipe layanan tidak valid! Penambahan dibatalkan.");
                            }
                            }

                        case 2 -> {
                            System.out.println("\n==========================================================================================");
                            System.out.println("                               DAFTAR LAYANAN STUDIO FOTO                                 ");
                            System.out.println("==========================================================================================");

                            studio.lihatSemuaLayanan();
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
                                    studio.cariLayanan(kataKunci);
                                }catch (LayananTidakDitemukanException e) {
                                    System.out.println("[PERINGATAN EXCEPTION] " + e.getMessage());
                                } finally {
                                    System.out.println("-> Sesi pencarian nama paket telah selesai dieksekusi.");
                                }
                            } else if (modeCari == 2) {
                                System.out.print("Masukkan Batas Harga Maksimal (Rp): ");
                                double hargaKunci = scanner.nextDouble();
                                scanner.nextLine();
                                studio.cariLayanan(hargaKunci);
                            } else {
                                System.out.println("Pilihan mode pencarian tidak valid.");
                            }
                            System.out.print("\nTekan Enter untuk kembali ke menu...");
                            scanner.nextLine();
                        }

                        case 4 -> {
                            System.out.println("\n-- Sistem Pemesanan (Asosiasi) --");
                            System.out.print("Masukkan ID Pelanggan: ");
                            String inputId = scanner.nextLine();
                            
                            Pelanggan pemesan = studio.getPelanggan(inputId);
                            
                            if (pemesan == null) {
                                System.out.println("Gagal: Pelanggan belum terdaftar!");
                            } else if (studio.getJumlahLayanan() == 0) {
                                System.out.println("Gagal: Belum ada layanan yang tersedia.");
                            } else {
                                studio.lihatSemuaLayanan();
                                System.out.print("Pilih Nomor Paket yg ingin dipesan: ");
                                int noPaket = scanner.nextInt() - 1;
                                scanner.nextLine();

                                if (noPaket >= 0 && noPaket < studio.getJumlahLayanan()) {
                                    LayananFoto itemPesan = studio.getLayananInt(noPaket);
                                    TransaksiPemesanan.prosesPinjam(pemesan, itemPesan);
                                } else {
                                        System.out.println("Nomor paket tidak valid!");
                                    }
                            System.out.print("\nTekan Enter untuk kembali ke menu...");
                            scanner.nextLine();
                            }
                        }
                        case 5 -> {
                            System.out.println("\n-- Kelola Data Pelanggan --");
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
                                studio.tambahPelanggan(new Pelanggan(id, nama));
                                System.out.println("Berhasil menambahkan pelanggan!");
                            } else if (menuPelanggan == 2) {
                                System.out.print("Masukkan ID Pelanggan yang dicari: ");
                                String searchId = scanner.nextLine();
                                studio.cariPelanggan(scanner.nextLine());
                            } else if (menuPelanggan == 3) {
                                studio.lihatSemuaPelanggan();
                                
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
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("\n[ERROR SYSTEM] Terjadi kesalahan: " + e.getMessage());
            }
        } 
        
        scanner.close();
    }
}