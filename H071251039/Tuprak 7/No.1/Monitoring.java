public class Monitoring implements Runnable {
    private final Gudang gudang;

    public Monitoring(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int stok = gudang.getStok();
                int maks = gudang.getKapasitasMaksimal();
                int persen = (int) ((double) stok / maks * 100);
                int isi = persen / 5;
                int kosong = 20 - isi;

                StringBuilder bar = new StringBuilder("[");
                for (int i = 0; i < isi; i++) bar.append("#");
                for (int i = 0; i < kosong; i++) bar.append("-");
                bar.append("]");

                System.out.println("Status Gudang: " + bar + " " + persen + "%  (" + stok + "/" + maks + ")");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
