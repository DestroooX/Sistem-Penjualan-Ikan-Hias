package com.mycompany.aquafishstore;

import java.util.Scanner;

public class AquafishStore {
    public static void cariIkan(Ikan[] daftarIkan, String nama) {
        boolean ditemukan = false;
        for (Ikan ikan : daftarIkan) {
            if (ikan != null &&
                ikan.getNama().equalsIgnoreCase(nama)) {

                System.out.println("\nIkan ditemukan:");
                ikan.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Ikan dengan nama tersebut tidak ditemukan.");
        }
    }

    public static void cariIkan(Ikan[] daftarIkan, double harga) {
        boolean ditemukan = false;
        for (Ikan ikan : daftarIkan) {
            if (ikan != null &&
                ikan.getHarga() <= harga) {

                System.out.println("\nIkan dengan harga <= Rp" + harga);
                ikan.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada ikan dengan harga tersebut.");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Ikan[] daftarIkan = new Ikan[50];
        int jumlahIkan = 0;
        int pilihan;
        do {
            System.out.println("\n========================================");
            System.out.println("     AQUAFISH STORE");
            System.out.println("     TOKO IKAN HIAS");
            System.out.println("========================================");
            System.out.println("1. Tambah Data Ikan");
            System.out.println("2. Tampilkan Seluruh Data");
            System.out.println("3. Cari Ikan");
            System.out.println("4. Lihat Total Ikan");
            System.out.println("5. Beli Ikan");
            System.out.println("6. Keluar");
            System.out.println("========================================");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:

                    if (jumlahIkan >= daftarIkan.length) {
                        System.out.println("Data ikan sudah penuh.");
                        break;
                    }
                    
                    System.out.println("\n--- TAMBAH DATA IKAN ---");
                    System.out.print("Nama ikan: ");
                    String nama = input.nextLine();
                    System.out.print("Harga ikan: ");
                    double harga = input.nextDouble();
                    System.out.print("Ukuran ikan (cm): ");
                    double ukuran = input.nextDouble();
                    System.out.print("Stok ikan: ");
                    int stok = input.nextInt();

                    input.nextLine();

                    System.out.println("\nJenis ikan:");
                    System.out.println("1. Air Tawar");
                    System.out.println("2. Air Laut");
                    System.out.print("Pilih jenis: ");

                    int jenis = input.nextInt();
                    input.nextLine();

                    if (harga <= 0 || ukuran <= 0 || stok < 0) {
                        System.out.println(
                                "Data tidak valid. Periksa harga, ukuran, dan stok.");
                    } else {
                        if (jenis == 1) {
                            daftarIkan[jumlahIkan] =
                                    new IkanAirTawar(
                                            nama,
                                            harga,
                                            ukuran,
                                            stok,
                                            "Air Tawar"
                                    );

                            jumlahIkan++;

                            System.out.println(
                                    "Data ikan air tawar berhasil ditambahkan.");

                        } else if (jenis == 2) {
                            daftarIkan[jumlahIkan] =
                                    new IkanAirLaut(
                                            nama,
                                            harga,
                                            ukuran,
                                            stok,
                                            "Air Laut"
                                    );

                            jumlahIkan++;

                            System.out.println(
                                    "Data ikan air laut berhasil ditambahkan.");

                        } else {
                            System.out.println(
                                    "Jenis ikan tidak valid.");
                        }
                    }

                    break;

                case 2:

                    System.out.println("\n========================================");
                    System.out.println("          DAFTAR IKAN HIAS");
                    System.out.println("========================================");

                    if (jumlahIkan == 0) {
                        System.out.println("Belum ada data ikan.");
                    } else {

                        for (int i = 0; i < jumlahIkan; i++) {
                            System.out.println("\nData ikan ke-" + (i + 1));
                            daftarIkan[i].tampilkanInfo();
                        }
                    }

                    break;

                case 3:

                    System.out.println("\n--- PENCARIAN IKAN ---");
                    System.out.println("1. Cari berdasarkan nama");
                    System.out.println("2. Cari berdasarkan harga maksimal");
                    System.out.print("Pilih: ");

                    int cari = input.nextInt();
                    input.nextLine();

                    if (cari == 1) {
                        System.out.print("Masukkan nama ikan: ");
                        String cariNama = input.nextLine();
                        cariIkan(daftarIkan, cariNama);

                    } else if (cari == 2) {
                        System.out.print("Masukkan harga maksimal: ");
                        double cariHarga = input.nextDouble();
                        cariIkan(daftarIkan, cariHarga);

                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }

                    break;

                case 4:

                    System.out.println("\n--- TOTAL DATA IKAN ---");
                    System.out.println(
                            "Total object ikan yang dibuat: "
                            + Ikan.getTotalIkan());

                    break;

                case 5:

                    System.out.println("\n--- PEMBELIAN IKAN ---");
                    System.out.print("Masukkan nama ikan: ");
                    String namaBeli = input.nextLine();
                    boolean ditemukan = false;

                    for (int i = 0; i < jumlahIkan; i++) {
                        if (daftarIkan[i].getNama()
                                .equalsIgnoreCase(namaBeli)) {
                            ditemukan = true;
                            System.out.print("Jumlah yang dibeli: ");
                            int jumlahBeli = input.nextInt();
                            input.nextLine();
                            daftarIkan[i].beli(jumlahBeli);

                            break;
                        }
                    }

                    if (!ditemukan) {
                        System.out.println(
                                "Ikan tidak ditemukan.");
                    }

                    break;

                case 6:

                    System.out.println("\n================================");
                    System.out.println("Terima kasih telah menggunakan");
                    System.out.println("AquaFish Store!");
                    System.out.println("================================");

                    break;

                default:

                    System.out.println(
                            "Pilihan menu tidak tersedia.");
            }

        } while (pilihan != 6);

        input.close();
    }
}