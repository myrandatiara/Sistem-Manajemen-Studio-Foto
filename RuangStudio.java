package com.mycompany.sistemmanajemenstudiofoto;

public class RuangStudio {
    private String namaRuangan;
    private int kapasitasOrang;

    public RuangStudio(String namaRuangan, int kapasitasOrang) {
        this.namaRuangan = namaRuangan;
        this.kapasitasOrang = kapasitasOrang;
    }

    public void bukaRuangan() {
        System.out.println("-> [" + namaRuangan + "] Siap Digunakan. Kapasitas maksimum: " + kapasitasOrang + " orang.");
    }
}