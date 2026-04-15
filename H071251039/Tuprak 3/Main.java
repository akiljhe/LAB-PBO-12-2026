public class Main {
    public static void main(String[] args) {
        System.out.println("=== SIMULASI SISTEM DOMPET DIGITAL ===");
        
        DompetDigital dompetSaya = new DompetDigital("Andi", "987654321", "123456");
        System.out.println(dompetSaya.getpin());
        dompetSaya.setpin("String");
        System.out.println(dompetSaya.getpin());
        System.out.println("\n--- 1. Menampilkan Identitas Akun ---");
        System.out.println("Nama Nasabah : " + dompetSaya.getNamaNasabah());
        System.out.println("No. Rekening : " + dompetSaya.getNomorRekening());

        System.out.println("\n--- 2. Uji Coba Setter (Ubah PIN) salah ---");
        dompetSaya.ubahPin("000000", "654321");

        System.out.println("\n--- 3. Uji Coba Setter (Ubah PIN) benar ---");
        dompetSaya.ubahPin("123456", "654321");

        System.out.println("\n--- 4. Setor Tunai Valid ---");
        dompetSaya.setorTunai(500000);
        dompetSaya.setorTunai(500000);

        System.out.println("\n--- 5. Setor Tunai Ilegal ---");
        dompetSaya.setorTunai(-50000);

        System.out.println("\n--- 6. Tarik Tunai PIN Lama/Salah ---");
        dompetSaya.tarikTunai(100000, "123456");

        System.out.println("\n--- 7. Tarik Tunai PIN Baru/Benar ---");
        dompetSaya.tarikTunai(100000, "654321");

        System.out.println("\n--- 8. Sisa Saldo Akhir ---");
        System.out.println("Sisa Saldo Saat Ini: Rp" + dompetSaya.getSaldo());
    }
}
                