package com.mycompany.aquafishstore;

public class IkanAirLaut extends Ikan implements DapatDibeli {
    private String jenisAir;

    public IkanAirLaut(String nama, double harga, double ukuran,
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
        System.out.println("        IKAN AIR LAUT");
        System.out.println("--------------------------------");
        super.tampilkanInfo();
        System.out.println("Jenis Air  : " + jenisAir);
    }

    @Override
    public void tampilkanHabitatIdeal() {
        System.out.println("Habitat ideal: Air laut dengan kadar garam dan kualitas air terjaga.");
    }

    @Override
    public void beli(int jumlah) {
        System.out.println("Proses pembelian ikan air laut.");
        super.beli(jumlah);
    }

    @Override
    public void prosesPembelian(int jumlah) {
        beli(jumlah);
    }
}
