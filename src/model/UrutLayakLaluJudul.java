package model;

import java.util.Comparator;

public class UrutLayakLaluJudul implements Comparator<Koleksi> {
    @Override
    public int compare(Koleksi k1, Koleksi k2) {
        Boolean layak1 = k1.isLayakPinjam();
        Boolean layak2 = k2.isLayakPinjam();
        
        int hasilBandingLayak = layak2.compareTo(layak1);
        if (hasilBandingLayak != 0) {
            return hasilBandingLayak;
        }
        return k1.getJudul().compareTo(k2.getJudul());
    }
}