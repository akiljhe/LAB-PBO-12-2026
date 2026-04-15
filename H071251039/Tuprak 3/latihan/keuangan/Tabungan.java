package keuangan;

public class Tabungan {
    private int saldo;

    public Tabungan(int saldoAwal) {
        this.saldo = saldoAwal;
    }

    public void tambahSaldo(int jumlah) {
        saldo += jumlah;
        System.out.println("Saldo terakhir: " + saldo);
    }

    public void tarikSaldo(int jumlah) {
        if (jumlah <= saldo) {
            saldo -= jumlah;
            System.out.println("Saldo setelah ditarik: " + saldo);
        } else {
            System.out.println("Saldo tidak mencukupi.");
        }
    }
}