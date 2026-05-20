import java.util.Random;

public class Pemasok implements Runnable {
    private final Gudang gudang;
    private final Random random = new Random();

    public Pemasok(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int jumlah = random.nextInt(5) + 1;
                gudang.tambahStok(jumlah);
                Thread.sleep((random.nextInt(1000)) + 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
