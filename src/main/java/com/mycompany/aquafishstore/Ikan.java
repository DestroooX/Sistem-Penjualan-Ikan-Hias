package com.mycompany.aquafishstore;
public class Ikan{
    private String nama;
    private double harga;
    private double ukuran;
    private int stok;

    private static int totalIkan = 0;

    public Ikan(String nama, double harga, double ukuran, int stok) {
        this.nama = nama;
        this.setHarga(harga);
        this.setUkuran(ukuran);
        this.setStok(stok);
        totalIkan++;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isEmpty()) {
            this.nama = nama;
        }
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        }
    }

    public double getUkuran() {
        return ukuran;
    }

    public void setUkuran(double ukuran) {
        if (ukuran > 0) {
            this.ukuran = ukuran;
        }
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok >= 0) {
            this.stok = stok;
        }
    }

    public static int getTotalIkan() {
        return totalIkan;
    }

    public void tampilkanInfo() {
        System.out.println("Nama       : " + nama);
        System.out.printf("Harga      : Rp%.0f%n", harga);
        System.out.println("Ukuran     : " + ukuran + " cm");
        System.out.println("Stok       : " + stok);
    }

    public void beli(int jumlah) {
        if (jumlah > 0 && jumlah <= stok) {
            stok -= jumlah;
            System.out.println("Pembelian berhasil!");
            System.out.println("Jumlah dibeli : " + jumlah);
            System.out.println("Total harga   : Rp" + (harga * jumlah));
            System.out.println("Sisa stok     : " + stok);
        } else {
            System.out.println("Jumlah pembelian tidak valid.");
        }
    }
}
