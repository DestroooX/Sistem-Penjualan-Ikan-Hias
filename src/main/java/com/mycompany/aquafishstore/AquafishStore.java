package com.mycompany.aquafishstore;

import java.util.Scanner;

public class AquafishStore {

    public static void cariIkan(Ikan[] daftarIkan, String nama) {
        boolean ditemukan = false;
        for (Ikan ikan : daftarIkan) {
            if (ikan != null && ikan.getNama().equalsIgnoreCase(nama)) {
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
            if (ikan != null && ikan.getHarga() <= harga) {
                System.out.println("\nIkan dengan harga <= Rp" + harga);
                ikan.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada ikan dengan harga tersebut.");
        }
    }

    public static void prosesIkan(Ikan ikan) {
        if (ikan != null) {
            System.out.println("\n--- PROSES IKAN ---");
            ikan.tampilkanInfo();
            ikan.tampilkanHabitatIdeal();
        } else {
            System.out.println("Data ikan tidak tersedia.");
        }
    }

    public static void jalankanFiturInterface(Ikan ikan) {
        if (ikan == null) {
            System.out.println("Data ikan tidak tersedia.");
            return;
        }

        System.out.println("\n--- FITUR INTERFACE ---");
        ikan.tampilkanInfo();

        if (ikan instanceof DapatDibeli) {
            DapatDibeli dapatDibeli = (DapatDibeli) ikan;
            System.out.println("\nObjek ini mendukung fitur pembelian.");
            dapatDibeli.prosesPembelian(1);
        }

        if (ikan instanceof DapatDirawat) {
            DapatDirawat dapatDirawat = (DapatDirawat) ikan;
            System.out.println("\nObjek ini memiliki tips perawatan.");
            dapatDirawat.tampilkanTipsPerawatan();
        }
    }

    public static void tampilkanSemuaIkan(Ikan[] daftarIkan, int jumlahIkan) {
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
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Ikan[] daftarIkan = new Ikan[50];
        int jumlahIkan = 0;
        int pilihan;

        daftarIkan[jumlahIkan++] = new IkanAirTawar("Ikan Cupang", 15000, 5, 10, "Air Tawar");
        daftarIkan[jumlahIkan++] = new IkanAirLaut("Ikan Badut", 35000, 7, 8, "Air Laut");
        daftarIkan[jumlahIkan++] = new IkanAirPayau("Ikan Belanak", 20000, 10, 6, "Air Payau");

        do {
            System.out.println("\n========================================");
            System.out.println("          AQUAFISH STORE");
            System.out.println("          TOKO IKAN HIAS");
            System.out.println("========================================");
            System.out.println("1. Tambah Data Ikan");
            System.out.println("2. Tampilkan Seluruh Data");
            System.out.println("3. Cari Ikan");
            System.out.println("4. Lihat Total Ikan");
            System.out.println("5. Beli Ikan");
            System.out.println("6. Simulasi Abstract Method");
            System.out.println("7. Uji Fitur Interface (instanceof)");
            System.out.println("8. Keluar");
            System.out.println("========================================");
            System.out.print("Pilih menu: ");

            if (!input.hasNextInt()) {
                System.out.println("Masukkan pilihan berupa angka.");
                input.nextLine();
                pilihan = 0;
                continue;
            }
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

                    System.out.println("\nJenis ikan:");
                    System.out.println("1. Air Tawar");
                    System.out.println("2. Air Laut");
                    System.out.println("3. Air Payau");
                    System.out.print("Pilih jenis: ");
                    int jenis = input.nextInt();
                    input.nextLine();

                    if (nama.trim().isEmpty() || harga <= 0 || ukuran <= 0 || stok < 0) {
                        System.out.println("Data tidak valid. Periksa kembali input.");
                    } else if (jenis == 1) {
                        daftarIkan[jumlahIkan++] =
                                new IkanAirTawar(nama, harga, ukuran, stok, "Air Tawar");
                        System.out.println("Data ikan air tawar berhasil ditambahkan.");
                    } else if (jenis == 2) {
                        daftarIkan[jumlahIkan++] =
                                new IkanAirLaut(nama, harga, ukuran, stok, "Air Laut");
                        System.out.println("Data ikan air laut berhasil ditambahkan.");
                    } else if (jenis == 3) {
                        daftarIkan[jumlahIkan++] =
                                new IkanAirPayau(nama, harga, ukuran, stok, "Air Payau");
                        System.out.println("Data ikan air payau berhasil ditambahkan.");
                    } else {
                        System.out.println("Jenis ikan tidak valid.");
                    }
                    break;

                case 2:
                    tampilkanSemuaIkan(daftarIkan, jumlahIkan);
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
                        cariIkan(daftarIkan, input.nextLine());
                    } else if (cari == 2) {
                        System.out.print("Masukkan harga maksimal: ");
                        cariIkan(daftarIkan, input.nextDouble());
                        input.nextLine();
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                    break;

                case 4:
                    System.out.println("\nTotal object ikan yang dibuat: " + Ikan.getTotalIkan());
                    break;

                case 5:
                    if (jumlahIkan == 0) {
                        System.out.println("Belum ada data ikan.");
                        break;
                    }
                    System.out.println("\n--- PEMBELIAN IKAN ---");
                    for (int i = 0; i < jumlahIkan; i++) {
                        System.out.println((i + 1) + ". " + daftarIkan[i].getNama()
                                + " (stok: " + daftarIkan[i].getStok() + ")");
                    }
                    System.out.print("Pilih ikan: ");
                    int nomorBeli = input.nextInt();
                    if (nomorBeli < 1 || nomorBeli > jumlahIkan) {
                        input.nextLine();
                        System.out.println("Pilihan ikan tidak valid.");
                        break;
                    }
                    System.out.print("Jumlah yang dibeli: ");
                    int jumlahBeli = input.nextInt();
                    input.nextLine();
                    daftarIkan[nomorBeli - 1].beli(jumlahBeli);
                    break;

                case 6:
                    if (jumlahIkan == 0) {
                        System.out.println("Belum ada data ikan.");
                        break;
                    }
                    for (int i = 0; i < jumlahIkan; i++) {
                        System.out.println((i + 1) + ". " + daftarIkan[i].getNama());
                    }
                    System.out.print("Pilih ikan untuk melihat habitat ideal: ");
                    int pilihHabitat = input.nextInt();
                    input.nextLine();
                    if (pilihHabitat >= 1 && pilihHabitat <= jumlahIkan) {
                        prosesIkan(daftarIkan[pilihHabitat - 1]);
                    } else {
                        System.out.println("Pilihan ikan tidak valid.");
                    }
                    break;

                case 7:
                    if (jumlahIkan == 0) {
                        System.out.println("Belum ada data ikan.");
                        break;
                    }
                    for (int i = 0; i < jumlahIkan; i++) {
                        System.out.println((i + 1) + ". " + daftarIkan[i].getNama());
                    }
                    System.out.print("Pilih ikan untuk menguji interface: ");
                    int pilihFitur = input.nextInt();
                    input.nextLine();
                    if (pilihFitur >= 1 && pilihFitur <= jumlahIkan) {
                        jalankanFiturInterface(daftarIkan[pilihFitur - 1]);
                    } else {
                        System.out.println("Pilihan ikan tidak valid.");
                    }
                    break;

                case 8:
                    System.out.println("\nTerima kasih telah menggunakan AquaFish Store!");
                    break;

                default:
                    System.out.println("Pilihan menu tidak tersedia.");
            }
        } while (pilihan != 8);

        input.close();
    }
}
