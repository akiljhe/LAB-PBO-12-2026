public class Gudang {
    private int stok;
    private final int kapasitasMaksimal;

    public Gudang(int kapasitasMaksimal) {
        this.kapasitasMaksimal = kapasitasMaksimal;
        this.stok = 0;
    }

    public synchronized void tambahStok(int jumlah) throws InterruptedException {
        while (stok + jumlah > kapasitasMaksimal) {
            System.out.println("[Pemasok] Gudang penuh (" + stok + "/" + kapasitasMaksimal + "), menunggu...");
            wait();
        }
        stok += jumlah;
        System.out.println("[Pemasok] Menambah " + jumlah + " barang. Stok sekarang: " + stok + "/" + kapasitasMaksimal);
        notifyAll();
    }

    public synchronized void ambilStok(int jumlah) throws InterruptedException {
        while (stok < jumlah) {
            System.out.println("[Kurir] Stok tidak cukup (" + stok + "/" + kapasitasMaksimal + "), menunggu...");
            wait();
        }
        stok -= jumlah;
        System.out.println("[Kurir] Mengambil " + jumlah + " barang. Stok sekarang: " + stok + "/" + kapasitasMaksimal);
        notifyAll();
    }

    public int getStok() {
        return stok;
    }

    public int getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }
}
