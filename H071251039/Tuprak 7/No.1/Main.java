import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Gudang gudang = new Gudang(20);

        ExecutorService pemasokPool = Executors.newFixedThreadPool(2);
        ExecutorService kurirPool = Executors.newFixedThreadPool(3);
        Thread monitorThread = new Thread(new Monitoring(gudang));

        monitorThread.start();

        for (int i = 0; i < 2; i++) {
            pemasokPool.execute(new Pemasok(gudang));
        }

        for (int i = 0; i < 3; i++) {
            kurirPool.execute(new Kurir(gudang));
        }

        Thread.sleep(15000);

        pemasokPool.shutdownNow();
        kurirPool.shutdownNow();
        monitorThread.interrupt();

        pemasokPool.awaitTermination(2, TimeUnit.SECONDS);
        kurirPool.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("\nSistem dihentikan. Stok akhir: " + gudang.getStok());
    }
}
