package layanan;

import model.Koleksi;
import eksepsi.KoleksiSedangDipinjamException;
import eksepsi.KoleksiTidakLayakException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KatalogPustaka {
    private List<Koleksi> daftarKoleksi;
    private List<String> koleksiDipinjam;

    public KatalogPustaka() {
        this.daftarKoleksi = new ArrayList<>();
        this.koleksiDipinjam = new ArrayList<>();
    }

    public void tambahKoleksi(Koleksi koleksi) {
        daftarKoleksi.add(koleksi);
    }

    public List<Koleksi> getDaftarKoleksi() {
        return daftarKoleksi;
    }

    public void setKoleksiDipinjam(String kodeKoleksi) {
        this.koleksiDipinjam.add(kodeKoleksi);
    }

    public void cetakStatus() {
        System.out.println("==================================================================================");
        System.out.println("                        STATUS KATALOG KOLEKSI PERPUSTAKAAN                       ");
        System.out.println("==================================================================================");
        for (Koleksi k : daftarKoleksi) {
            String kelayakan = k.isLayakPinjam() ? "[LAYAK PINJAM]" : "[TIDAK LAYAK PINJAM]";
            System.out.println("- " + kelayakan + " " + k.deskripsi());
        }
        System.out.println("==================================================================================\n");
    }

    public void urut(Comparator<Koleksi> comparator) {
        Collections.sort(daftarKoleksi, comparator);
    }

    public void pinjam(String kodeKoleksi) throws KoleksiSedangDipinjamException, KoleksiTidakLayakException {
        Koleksi target = null;
        for (Koleksi k : daftarKoleksi) {
            if (k.getKodeKoleksi().equalsIgnoreCase(kodeKoleksi)) {
                target = k;
                break;
            }
        }

        if (target == null) {
            System.out.println("Koleksi dengan kode " + kodeKoleksi + " tidak ditemukan.");
            return;
        }

        if (koleksiDipinjam.contains(target.getKodeKoleksi())) {
            throw new KoleksiSedangDipinjamException(target.getJudul());
        }

        if (!target.isLayakPinjam()) {
            throw new KoleksiTidakLayakException(target.getJudul());
        }

        koleksiDipinjam.add(target.getKodeKoleksi());
        System.out.println("SUKSES: Peminjaman koleksi \"" + target.getJudul() + "\" berhasil dilakukan.");
    }
}