package com.mycompany.aquafishstore;

import java.util.ArrayList;
import java.util.HashMap;

public class TokoIkan {

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

    public Ikan cariIkan(String nama)
            throws IkanTidakDitemukanException {

        if (nama == null || nama.trim().isEmpty()) {
            throw new IkanTidakDitemukanException(
                    "Nama ikan tidak boleh kosong.");
        }

        for (Ikan ikan : daftarIkan) {
            if (ikan.getNama().equalsIgnoreCase(nama.trim())) {
                return ikan;
            }
        }

        throw new IkanTidakDitemukanException(
                "Ikan dengan nama tersebut tidak ditemukan.");
    }

    public void cariIkan(double harga)
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
}