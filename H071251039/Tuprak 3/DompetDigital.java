

public class DompetDigital {
    private double saldo;
    private String pin;
    protected String namaNasabah;
    String nomorRekening;

    public DompetDigital(String namaNasabah, String nomorRekening, String pinAwal) {
        this.namaNasabah = namaNasabah;
        this.nomorRekening = nomorRekening;
        this.pin = pinAwal;
        this.saldo = 0; 
        cetakLog("Akun berhasil dibuat untuk nasabah: " + namaNasabah);
    }
    public String getNamaNasabah() {
        return this.namaNasabah;
    }
    public String getNomorRekening() {
        return this.nomorRekening;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setpin(String a){
        this.pin = a;
    }

    public String getpin() {
        return this.pin;
    }

    public void ubahPin(String pinLama, String pinBaru) {
        if (this.pin.equals(pinLama)) {
            if (pinBaru.length() == 6) {
                this.pin = pinBaru;
                System.out.println("SUKSES: PIN berhasil diubah.");
                cetakLog("Perubahan PIN berhasil.");
            } else {
                System.out.println("GAGAL: PIN baru harus memiliki panjang tepat 6 karakter.");
                cetakLog("Gagal ubah PIN: Panjang tidak 6 karakter.");
            }
        } else {
            System.out.println("GAGAL: PIN lama yang Anda masukkan salah.");
            cetakLog("Gagal ubah PIN: PIN lama tidak cocok.");
        }
    }

    public void setorTunai(double nominal) {
        if (nominal > 0) {
            this.saldo += nominal;
            System.out.println("SUKSES: Setor tunai Rp" + nominal + " berhasil.");
            cetakLog("Setor tunai berhasil (Rp" + nominal + ").");
        } else {
            System.out.println("GAGAL: Nominal setor tunai tidak boleh nol atau negatif.");
            cetakLog("Gagal setor tunai: Nominal ilegal (Rp" + nominal + ").");
        }
    }

    public void tarikTunai(double nominal, String pinInput) {
        if (this.pin.equals(pinInput)) {
            if (nominal > 0 && this.saldo >= nominal) {
                this.saldo -= nominal;
                System.out.println("SUKSES: Tarik tunai Rp" + nominal + " berhasil.");
                cetakLog("Tarik tunai berhasil (Rp" + nominal + ").");
            } else {
                System.out.println("GAGAL: Saldo tidak mencukupi atau nominal tidak valid.");
                cetakLog("Gagal tarik tunai: Saldo kurang.");
            }
        } else {
            System.out.println("GAGAL: PIN salah. Tarik tunai dibatalkan.");
            cetakLog("Gagal tarik tunai: Verifikasi PIN gagal.");
        }
    }

    private void cetakLog(String pesan) {
        System.out.println("[LOG SISTEM INTERNAL] " + pesan);
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}