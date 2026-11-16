package com.mycompany.aquafishstore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TokoIkan implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String FILE_DATA = "data_aquafish.dat";
    private static final String FILE_LOG = "log_transaksi.txt";

    private ArrayList<Ikan> daftarIkan;
    private HashMap<String, Pelanggan> daftarPelanggan;
    private Akuarium akuarium;

    public TokoIkan() {
        daftarIkan = new ArrayList<>();
        daftarPelanggan = new HashMap<>();
        akuarium = new Akuarium(
                "Akuarium AquaFish Store", 200, "Air Tawar dan Laut");
    }

    public void tambahIkan(Ikan ikan) {
        if (ikan != null) {
            daftarIkan.add(ikan);
            System.out.println("Data ikan berhasil ditambahkan.");
        }
    }

    public void lihatSemuaIkan() {
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

    public Ikan cariIkan(String nama) throws IkanTidakDitemukanException {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IkanTidakDitemukanException("Nama ikan tidak boleh kosong.");
        }

        for (Ikan ikan : daftarIkan) {
            if (ikan.getNama().equalsIgnoreCase(nama.trim())) {
                return ikan;
            }
        }

        throw new IkanTidakDitemukanException(
                "Ikan dengan nama tersebut tidak ditemukan.");
    }

    public void cariIkan(double harga) throws IkanTidakDitemukanException {
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

    public Ikan getIkan(int nomor) {
        if (nomor >= 1 && nomor <= daftarIkan.size()) {
            return daftarIkan.get(nomor - 1);
        }

        return null;
    }

    public int getJumlahIkan() {
        return daftarIkan.size();
    }

    public boolean isDaftarIkanKosong() {
        return daftarIkan.isEmpty();
    }

    public void tambahPelanggan(Pelanggan pelanggan) {
        if (pelanggan != null) {
            String id = pelanggan.getIdPelanggan();

            if (!daftarPelanggan.containsKey(id)) {
                daftarPelanggan.put(id, pelanggan);
                System.out.println("Data pelanggan berhasil ditambahkan.");
            } else {
                System.out.println("ID pelanggan sudah digunakan.");
            }
        }
    }

    public Pelanggan cariPelanggan(String id) {
        return daftarPelanggan.get(id);
    }

    public void lihatSemuaPelanggan() {
        System.out.println("\n========================================");
        System.out.println("          DAFTAR PELANGGAN");
        System.out.println("========================================");

        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
        } else {
            for (String id : daftarPelanggan.keySet()) {
                daftarPelanggan.get(id).tampilkanInfoPelanggan();
                System.out.println("----------------------------------------");
            }
        }
    }

    public int getJumlahPelanggan() {
        return daftarPelanggan.size();
    }

    public void lihatAkuarium() {
        akuarium.tampilkanInfoAkuarium();
    }

    public void simpanDataKeFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(FILE_DATA))) {

            output.writeObject(this);
            System.out.println("Data berhasil disimpan ke " + FILE_DATA);

        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    public void muatDataDariFile() {
        File file = new File(FILE_DATA);

        if (!file.exists()) {
            System.out.println(
                    "File data belum tersedia. Toko dimulai dengan data kosong.");
            return;
        }

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(file))) {

            TokoIkan dataTersimpan = (TokoIkan) input.readObject();

            this.daftarIkan = dataTersimpan.daftarIkan;
            this.daftarPelanggan = dataTersimpan.daftarPelanggan;
            this.akuarium = dataTersimpan.akuarium;

            Ikan.setTotalIkan(daftarIkan.size());

            System.out.println("Data berhasil dimuat dari " + FILE_DATA);

        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
    }

    public void catatLogTransaksi(String keterangan) {
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String waktu = LocalDateTime.now().format(format);

        try (FileWriter writer = new FileWriter(FILE_LOG, true)) {

            writer.write("[" + waktu + "] " + keterangan
                    + System.lineSeparator());

            System.out.println("Transaksi berhasil dicatat ke " + FILE_LOG);

        } catch (IOException e) {
            System.out.println("Gagal mencatat transaksi: " + e.getMessage());
        }
    }

    public void bacaLogTransaksi() {
        File file = new File(FILE_LOG);

        System.out.println("\n========================================");
        System.out.println("          LOG TRANSAKSI");
        System.out.println("========================================");

        if (!file.exists()) {
            System.out.println("Belum ada log transaksi.");
            return;
        }

        try (Scanner pembaca = new Scanner(file)) {

            if (!pembaca.hasNextLine()) {
                System.out.println("File log masih kosong.");
                return;
            }

            while (pembaca.hasNextLine()) {
                System.out.println(pembaca.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Gagal membaca log: " + e.getMessage());
        }
    }
}