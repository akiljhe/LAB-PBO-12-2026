import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> dokumen = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            dokumen.add("Dokumen_" + (char) ('A' + i - 1) + ".txt");
        }

        int jumlahThread = 4;
        ExecutorService executor = Executors.newFixedThreadPool(jumlahThread);
        ConcurrentHashMap<String, Integer> hasilProses = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> threadProses = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Long> durasiProses = new ConcurrentHashMap<>();
        CountDownLatch latch = new CountDownLatch(dokumen.size());

        System.out.println("=== Memulai pemrosesan " + dokumen.size() + " dokumen dengan " + jumlahThread + " thread ===\n");

        for (String namaFile : dokumen) {
            executor.execute(() -> {
                DataProcessor processor = new DataProcessor();
                String namaThread = Thread.currentThread().getName();
                long mulai = System.currentTimeMillis();
                try {
                    int jumlahKata = processor.process(namaFile);
                    long selesai = System.currentTimeMillis();
                    long durasi = selesai - mulai;

                    hasilProses.put(namaFile, jumlahKata);
                    threadProses.put(namaFile, namaThread);
                    durasiProses.put(namaFile, durasi);
X
                    System.out.println("[" + namaThread + "] Selesai memproses " + namaFile + " (" + jumlahKata + " kata) - " + durasi + "ms");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        System.out.println("\n=== HASIL AKHIR - KLASEMEN PEMROSESAN DOKUMEN ===");
        System.out.println(String.format("%-20s %-20s %-15s %s", "Nama Dokumen", "Thread", "Jumlah Kata", "Durasi (ms)"));
        System.out.println("-".repeat(70));

        int totalKata = 0;
        long totalDurasi = 0;

        for (String nama : dokumen) {
            int kata = hasilProses.getOrDefault(nama, 0);
            String thread = threadProses.getOrDefault(nama, "-");
            long durasi = durasiProses.getOrDefault(nama, 0L);
            totalKata += kata;
            totalDurasi += durasi;
            System.out.println(String.format("%-20s %-20s %-15d %d", nama, thread, kata, durasi));
        }

        System.out.println("-".repeat(70));
        System.out.println("Total Kata Keseluruhan : " + totalKata);
        System.out.printf("Rata-rata Waktu Proses : %.2f ms%n", (double) totalDurasi / dokumen.size());
    }
}
