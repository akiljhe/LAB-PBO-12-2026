import java.util.Random;

public class DataProcessor {
    private static final Random random = new Random();

    public int process(String fileName) throws InterruptedException {
        int delay = random.nextInt(1501) + 500;
        Thread.sleep(delay);
        int jumlahKata = random.nextInt(491) + 10;
        return jumlahKata;
    }
}
