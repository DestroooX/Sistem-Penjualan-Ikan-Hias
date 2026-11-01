package com.mycompany.aquafishstore;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class AquafishStore {

    public static void cariIkan(ArrayList<Ikan> daftarIkan, String nama)
            throws IkanTidakDitemukanException {

        if (nama == null || nama.trim().isEmpty()) {
            throw new IkanTidakDitemukanException("Nama ikan tidak boleh kosong.");
        }

        boolean ditemukan = false;

        for (Ikan ikan : daftarIkan) {
            if (ikan.getNama().equalsIgnoreCase(nama)) {
                System.out.println("\nIkan ditemukan:");
                ikan.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            throw new IkanTidakDitemukanException(
                    "Ikan dengan nama tersebut tidak ditemukan.");
        }
    }

    public static void cariIkan(ArrayList<Ikan> daftarIkan, double harga)
            throws IkanTidakDitemukanException {

        if (harga <= 0) {
            throw new IkanTidakDitemukanException(
                    "Harga maksimal harus lebih dari 0.");
        }

        boolean ditemukan = false;

        for (Ikan ikan : daftarIkan) {
            if (ikan.getHarga() <= harga) {
                System.out.println("\nIkan dengan harga <= Rp" + harga);
                ikan.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            throw new IkanTidakDitemukanException(
                    "Tidak ada ikan dengan harga tersebut.");
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

    public static void tampilkanSemuaIkan(ArrayList<Ikan> daftarIkan) {
        System.out.println("\n========================================");
        System.out.println("          DAFTAR IKAN HIAS");
        System.out.println("========================================");

        if (daftarIkan.isEmpty()) {
            System.out.println("Belum ada data ikan.");
        } else {
            for (int i = 0; i < daftarIkan.size(); i++) {
                System.out.println("\nData ikan ke-" + (i + 1));
                daftarIkan.get(i).tampilkanInfo();
            }
        }
    }

    public static void tambahPelanggan(HashMap<String, String> daftarPelanggan,
            Scanner input) {

        System.out.println("\n--- TAMBAH DATA PELANGGAN ---");

        System.out.print("Masukkan ID pelanggan: ");
        String id = input.nextLine();

        if (id.trim().isEmpty()) {
            System.out.println("ID pelanggan tidak boleh kosong.");
            return;
        }

        if (daftarPelanggan.containsKey(id)) {
            System.out.println("ID pelanggan sudah digunakan.");
            return;
        }

        System.out.print("Masukkan nama pelanggan: ");
        String nama = input.nextLine();

        if (nama.trim().isEmpty()) {
            System.out.println("Nama pelanggan tidak boleh kosong.");
            return;
        }

        daftarPelanggan.put(id, nama);

        System.out.println("Data pelanggan berhasil ditambahkan.");
    }

    public static void cariPelanggan(HashMap<String, String> daftarPelanggan,
            Scanner input) {

        System.out.println("\n--- CARI DATA PELANGGAN ---");
        System.out.print("Masukkan ID pelanggan: ");
        String id = input.nextLine();

        if (daftarPelanggan.containsKey(id)) {
            System.out.println("Data pelanggan ditemukan.");
            System.out.println("ID pelanggan   : " + id);
            System.out.println("Nama pelanggan : " + daftarPelanggan.get(id));
        } else {
            System.out.println("Pelanggan dengan ID tersebut tidak ditemukan.");
        }
    }

    public static void tampilkanSemuaPelanggan(
            HashMap<String, String> daftarPelanggan) {

        System.out.println("\n========================================");
        System.out.println("          DAFTAR PELANGGAN");
        System.out.println("========================================");

        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
        } else {
            for (String id : daftarPelanggan.keySet()) {
                System.out.println("ID pelanggan   : " + id);
                System.out.println("Nama pelanggan : "
                        + daftarPelanggan.get(id));
                System.out.println("----------------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Ikan> daftarIkan = new ArrayList<>();

        HashMap<String, String> daftarPelanggan = new HashMap<>();

        int pilihan = 0;

        daftarIkan.add(new IkanAirTawar(
                "Ikan Cupang", 15000, 5, 10, "Air Tawar"));

        daftarIkan.add(new IkanAirLaut(
                "Ikan Badut", 35000, 7, 8, "Air Laut"));

        daftarIkan.add(new IkanAirPayau(
                "Ikan Belanak", 20000, 10, 6, "Air Payau"));

        daftarPelanggan.put("PL001", "Andi");
        daftarPelanggan.put("PL002", "Budi");

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
            System.out.println("8. Tambah Data Pelanggan");
            System.out.println("9. Cari Data Pelanggan");
            System.out.println("10. Tampilkan Seluruh Pelanggan");
            System.out.println("11. Keluar");
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
                            daftarIkan.add(new IkanAirTawar(
                                    nama, harga, ukuran, stok, "Air Tawar"));
                            System.out.println(
                                    "Data ikan air tawar berhasil ditambahkan.");
                        } else if (jenis == 2) {
                            daftarIkan.add(new IkanAirLaut(
                                    nama, harga, ukuran, stok, "Air Laut"));
                            System.out.println(
                                    "Data ikan air laut berhasil ditambahkan.");
                        } else if (jenis == 3) {
                            daftarIkan.add(new IkanAirPayau(
                                    nama, harga, ukuran, stok, "Air Payau"));
                            System.out.println(
                                    "Data ikan air payau berhasil ditambahkan.");
                        } else {
                            System.out.println("Jenis ikan tidak valid.");
                        }
                        break;

                    case 2:
                        tampilkanSemuaIkan(daftarIkan);
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
                            double hargaMaksimal = input.nextDouble();
                            input.nextLine();

                            cariIkan(daftarIkan, hargaMaksimal);
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                        break;

                    case 4:
                        System.out.println("\nTotal object ikan yang dibuat: "
                                + Ikan.getTotalIkan());
                        System.out.println("Jumlah data ikan saat ini: "
                                + daftarIkan.size());
                        break;

                    case 5:
                        if (daftarIkan.isEmpty()) {
                            System.out.println("Belum ada data ikan.");
                            break;
                        }

                        System.out.println("\n--- PEMBELIAN IKAN ---");

                        for (int i = 0; i < daftarIkan.size(); i++) {
                            System.out.println((i + 1) + ". "
                                    + daftarIkan.get(i).getNama()
                                    + " (stok: "
                                    + daftarIkan.get(i).getStok() + ")");
                        }

                        System.out.print("Pilih ikan: ");
                        int nomorBeli = input.nextInt();

                        if (nomorBeli < 1
                                || nomorBeli > daftarIkan.size()) {
                            System.out.println("Pilihan ikan tidak valid.");
                            break;
                        }

                        System.out.print("Jumlah yang dibeli: ");
                        int jumlahBeli = input.nextInt();
                        input.nextLine();

                        daftarIkan.get(nomorBeli - 1).beli(jumlahBeli);
                        break;

                    case 6:
                        if (daftarIkan.isEmpty()) {
                            System.out.println("Belum ada data ikan.");
                            break;
                        }

                        for (int i = 0; i < daftarIkan.size(); i++) {
                            System.out.println((i + 1) + ". "
                                    + daftarIkan.get(i).getNama());
                        }

                        System.out.print(
                                "Pilih ikan untuk melihat habitat ideal: ");
                        int pilihHabitat = input.nextInt();
                        input.nextLine();

                        if (pilihHabitat >= 1
                                && pilihHabitat <= daftarIkan.size()) {
                            prosesIkan(daftarIkan.get(pilihHabitat - 1));
                        } else {
                            System.out.println("Pilihan ikan tidak valid.");
                        }
                        break;

                    case 7:
                        if (daftarIkan.isEmpty()) {
                            System.out.println("Belum ada data ikan.");
                            break;
                        }

                        for (int i = 0; i < daftarIkan.size(); i++) {
                            System.out.println((i + 1) + ". "
                                    + daftarIkan.get(i).getNama());
                        }

                        System.out.print(
                                "Pilih ikan untuk menguji interface: ");
                        int pilihFitur = input.nextInt();
                        input.nextLine();

                        if (pilihFitur >= 1
                                && pilihFitur <= daftarIkan.size()) {
                            jalankanFiturInterface(
                                    daftarIkan.get(pilihFitur - 1));
                        } else {
                            System.out.println("Pilihan ikan tidak valid.");
                        }
                        break;

                    case 8:
                        tambahPelanggan(daftarPelanggan, input);
                        break;

                    case 9:
                        cariPelanggan(daftarPelanggan, input);
                        break;

                    case 10:
                        tampilkanSemuaPelanggan(daftarPelanggan);
                        break;

                    case 11:
                        System.out.println(
                                "\nTerima kasih telah menggunakan AquaFish Store!");
                        break;

                    default:
                        System.out.println("Pilihan menu tidak tersedia.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nTerjadi kesalahan input!");
                System.out.println(
                        "Error: Masukkan data angka sesuai jenis input.");
                input.nextLine();

            } catch (IkanTidakDitemukanException e) {
                System.out.println("\nTerjadi kesalahan pencarian!");
                System.out.println("Error: " + e.getMessage());

            } finally {
                System.out.println("\nProses menu selesai.");
            }

        } while (pilihan != 11);

        input.close();
    }
}