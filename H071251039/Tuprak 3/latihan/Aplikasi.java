import keuangan.Tabungan;

public class Aplikasi {
    public static void main(String[] args) {
        Tabungan atm = new Tabungan(100000);

        atm.tambahSaldo(50000);
        atm.tarikSaldo(20000);
    }
}