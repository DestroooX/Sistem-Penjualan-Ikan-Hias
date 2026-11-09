package com.mycompany.aquafishstore;

import java.util.Scanner;
import java.util.InputMismatchException;

public class AquafishStore {

    // Method untuk menjalankan abstract method
    public static void prosesIkan(Ikan ikan) {
        if (ikan != null) {
            System.out.println("\n--- SIMULASI ABSTRACT METHOD ---");
            ikan.tampilkanInfo();
            ikan.tampilkanHabitatIdeal();
        } else {
            System.out.println("Data ikan tidak tersedia.");
        }
    }

    // Method untuk menguji interface menggunakan instanceof
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

    // Method untuk memilih ikan
    public static Ikan pilihIkan(TokoIkan toko, Scanner input) {
        if (toko.isDaftarIkanKosong()) {
            System.out.println("Belum ada data ikan.");
            return null;
        }

        System.out.println("\n--- PILIH IKAN ---");

        for (int i = 0; i < toko.getJumlahIkan(); i++) {
            Ikan ikan = toko.getIkan(i + 1);

            System.out.println((i + 1) + ". " + ikan.getNama()
                    + " (stok: " + ikan.getStok() + ")");
        }

        System.out.print("Pilih nomor ikan: ");
        int nomor = input.nextInt();
        input.nextLine();

        Ikan ikanDipilih = toko.getIkan(nomor);

        if (ikanDipilih == null) {
            System.out.println("Pilihan ikan tidak valid.");
        }

        return ikanDipilih;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        TokoIkan toko = new TokoIkan();

        TransaksiPembelian transaksi = new TransaksiPembelian();

        int pilihan = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("          AQUAFISH STORE");
            System.out.println("          TOKO IKAN HIAS");
            System.out.println("========================================");
            System.out.println("1. Tambah Data Ikan");
            System.out.println("2. Tampilkan Seluruh Data Ikan");
            System.out.println("3. Cari Ikan");
            System.out.println("4. Lihat Total Ikan");
            System.out.println("5. Simulasi Abstract Method");
            System.out.println("6. Uji Fitur Interface (instanceof)");
            System.out.println("7. Tambah Data Pelanggan");
            System.out.println("8. Cari Data Pelanggan");
            System.out.println("9. Tampilkan Seluruh Pelanggan");
            System.out.println("10. Informasi Akuarium (Composition)");
            System.out.println("11. Transaksi Pembelian (Association)");
            System.out.println("12. Keluar");
            System.out.println("========================================");
            System.out.print("Pilih menu: ");

            try {
                pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {

                    case 1:
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

                        if (nama.trim().isEmpty() || harga <= 0
                                || ukuran <= 0 || stok < 0) {

                            System.out.println(
                                    "Data tidak valid. Periksa kembali input.");

                        } else if (jenis == 1) {

                            toko.tambahIkan(new IkanAirTawar(
                                    nama, harga, ukuran, stok, "Air Tawar"));

                        } else if (jenis == 2) {

                            toko.tambahIkan(new IkanAirLaut(
                                    nama, harga, ukuran, stok, "Air Laut"));

                        } else if (jenis == 3) {

                            toko.tambahIkan(new IkanAirPayau(
                                    nama, harga, ukuran, stok, "Air Payau"));

                        } else {
                            System.out.println("Jenis ikan tidak valid.");
                        }
                        break;

                    case 2:
                        toko.lihatSemuaIkan();
                        break;

                    case 3:
                        if (toko.isDaftarIkanKosong()) {
                            System.out.println("Belum ada data ikan.");
                            break;
                        }

                        System.out.println("\n--- PENCARIAN IKAN ---");
                        System.out.println("1. Cari berdasarkan nama");
                        System.out.println("2. Cari berdasarkan harga maksimal");
                        System.out.print("Pilih: ");

                        int cari = input.nextInt();
                        input.nextLine();

                        try {
                            if (cari == 1) {
                                System.out.print("Masukkan nama ikan: ");
                                String namaCari = input.nextLine();

                                Ikan hasil = toko.cariIkan(namaCari);

                                System.out.println("\nIkan ditemukan:");
                                hasil.tampilkanInfo();

                            } else if (cari == 2) {
                                System.out.print("Masukkan harga maksimal: ");
                                double hargaMaksimal = input.nextDouble();
                                input.nextLine();

                                toko.cariIkan(hargaMaksimal);

                            } else {
                                System.out.println("Pilihan tidak valid.");
                            }

                        } catch (IkanTidakDitemukanException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("\n--- TOTAL DATA IKAN ---");
                        System.out.println("Total object ikan yang dibuat: "
                                + Ikan.getTotalIkan());
                        System.out.println("Jumlah ikan di toko saat ini: "
                                + toko.getJumlahIkan());
                        break;

                    case 5:
                        System.out.println("\n--- SIMULASI ABSTRACT METHOD ---");

                        Ikan ikanHabitat = pilihIkan(toko, input);

                        if (ikanHabitat != null) {
                            prosesIkan(ikanHabitat);
                        }
                        break;

                    case 6:
                        System.out.println("\n--- UJI FITUR INTERFACE ---");

                        Ikan ikanInterface = pilihIkan(toko, input);

                        if (ikanInterface != null) {
                            jalankanFiturInterface(ikanInterface);
                        }
                        break;

                    case 7:
                        System.out.println("\n--- TAMBAH DATA PELANGGAN ---");

                        System.out.print("Masukkan ID pelanggan: ");
                        String id = input.nextLine();

                        System.out.print("Masukkan nama pelanggan: ");
                        String namaPelanggan = input.nextLine();

                        if (id.trim().isEmpty()
                                || namaPelanggan.trim().isEmpty()) {

                            System.out.println(
                                    "ID dan nama pelanggan tidak boleh kosong.");

                        } else if (toko.cariPelanggan(id) != null) {

                            System.out.println(
                                    "ID pelanggan sudah digunakan.");

                        } else {

                            Pelanggan pelanggan = new Pelanggan(
                                    id, namaPelanggan);

                            toko.tambahPelanggan(pelanggan);
                        }
                        break;

                    case 8:
                        System.out.println("\n--- CARI DATA PELANGGAN ---");

                        System.out.print("Masukkan ID pelanggan: ");
                        String idCari = input.nextLine();

                        Pelanggan hasilPelanggan =
                                toko.cariPelanggan(idCari);

                        if (hasilPelanggan != null) {
                            System.out.println("\nData pelanggan ditemukan.");
                            hasilPelanggan.tampilkanInfoPelanggan();
                        } else {
                            System.out.println(
                                    "Pelanggan dengan ID tersebut tidak ditemukan.");
                        }
                        break;

                    case 9:
                        toko.lihatSemuaPelanggan();
                        break;

                    case 10:
                        toko.lihatAkuarium();
                        break;

                    case 11:
                        if (toko.getJumlahPelanggan() == 0) {
                            System.out.println(
                                    "Belum ada data pelanggan.");
                            break;
                        }

                        if (toko.isDaftarIkanKosong()) {
                            System.out.println("Belum ada data ikan.");
                            break;
                        }

                        System.out.println("\n--- TRANSAKSI PEMBELIAN ---");

                        System.out.print("Masukkan ID pelanggan: ");
                        String idTransaksi = input.nextLine();

                        Pelanggan pembeli =
                                toko.cariPelanggan(idTransaksi);

                        if (pembeli == null) {
                            System.out.println(
                                    "Pelanggan tidak ditemukan.");
                            break;
                        }

                        Ikan ikanTransaksi = pilihIkan(toko, input);

                        if (ikanTransaksi == null) {
                            break;
                        }

                        System.out.print("Jumlah yang dibeli: ");
                        int jumlahBeli = input.nextInt();
                        input.nextLine();

                        transaksi.prosesTransaksi(
                                pembeli, ikanTransaksi, jumlahBeli);

                        break;

                    case 12:
                        System.out.println(
                                "\nTerima kasih telah menggunakan AquaFish Store!");
                        break;

                    default:
                        System.out.println("Pilihan menu tidak tersedia.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nTerjadi kesalahan input!");
                System.out.println(
                        "Error: Masukkan angka sesuai jenis input.");
                input.nextLine();

            }

        } while (pilihan != 12);

        input.close();
    }
}