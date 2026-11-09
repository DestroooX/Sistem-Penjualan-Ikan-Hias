package com.mycompany.aquafishstore;

public class TransaksiPembelian {

    public void prosesTransaksi(Pelanggan pelanggan,
                                Ikan ikan, int jumlah) {

        if (pelanggan == null) {
            System.out.println("Data pelanggan tidak tersedia.");
            return;
        }

        if (ikan == null) {
            System.out.println("Data ikan tidak tersedia.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("          TRANSAKSI PEMBELIAN");
        System.out.println("========================================");

        System.out.println("\n--- DATA PELANGGAN ---");
        pelanggan.tampilkanInfoPelanggan();

        System.out.println("\n--- DATA IKAN ---");
        ikan.tampilkanInfo();

        System.out.println("\n--- DETAIL TRANSAKSI ---");

        if (jumlah > 0 && jumlah <= ikan.getStok()) {
            double totalHarga = ikan.getHarga() * jumlah;

            ikan.beli(jumlah);

            System.out.println("\n--- STRUK PEMBELIAN ---");
            System.out.println("Nama Pelanggan : "
                    + pelanggan.getNamaPelanggan());
            System.out.println("Nama Ikan      : " + ikan.getNama());
            System.out.println("Jumlah Beli    : " + jumlah);
            System.out.printf("Total Harga    : Rp%.0f%n", totalHarga);

            System.out.println("Transaksi berhasil diproses.");
        } else {
            System.out.println(
                    "Jumlah pembelian tidak valid atau stok tidak cukup.");
        }
    }
}