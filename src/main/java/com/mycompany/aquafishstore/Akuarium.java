package com.mycompany.aquafishstore;

public class Akuarium {
    private String namaAkuarium;
    private double kapasitas;
    private String jenisAir;

    public Akuarium(String namaAkuarium, double kapasitas,
                    String jenisAir) {
        this.namaAkuarium = namaAkuarium;
        this.kapasitas = kapasitas;
        this.jenisAir = jenisAir;
    }

    public String getNamaAkuarium() {
        return namaAkuarium;
    }

    public double getKapasitas() {
        return kapasitas;
    }

    public String getJenisAir() {
        return jenisAir;
    }

    public void tampilkanInfoAkuarium() {
        System.out.println("\n========================================");
        System.out.println("          INFORMASI AKUARIUM");
        System.out.println("========================================");
        System.out.println("Nama Akuarium : " + namaAkuarium);
        System.out.println("Kapasitas     : " + kapasitas + " Liter");
        System.out.println("Jenis Air     : " + jenisAir);
    }
}