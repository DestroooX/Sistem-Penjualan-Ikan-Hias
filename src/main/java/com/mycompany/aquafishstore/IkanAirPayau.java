package com.mycompany.aquafishstore;

public class IkanAirPayau extends Ikan implements DapatDirawat {
    private String jenisAir;

    public IkanAirPayau(String nama, double harga, double ukuran,
                        int stok, String jenisAir) {
        super(nama, harga, ukuran, stok);
        this.jenisAir = jenisAir;
    }

    public String getJenisAir() {
        return jenisAir;
    }

    public void setJenisAir(String jenisAir) {
        if (jenisAir != null && !jenisAir.isEmpty()) {
            this.jenisAir = jenisAir;
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("--------------------------------");
        System.out.println("       IKAN AIR PAYAU");
        System.out.println("--------------------------------");
        super.tampilkanInfo();
        System.out.println("Jenis Air  : " + jenisAir);
    }

    @Override
    public void tampilkanHabitatIdeal() {
        System.out.println("Habitat ideal: Air payau dengan salinitas yang sesuai.");
    }

    @Override
    public void beli(int jumlah) {
        System.out.println("Proses pembelian ikan air payau.");
        super.beli(jumlah);
    }

    @Override
    public void tampilkanTipsPerawatan() {
        System.out.println("Tips: Pantau salinitas air dan lakukan penggantian air secara teratur.");
    }
}
