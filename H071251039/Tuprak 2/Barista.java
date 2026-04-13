class MesinKopi {
    public String merk;
    public int kondisi; 

    public MesinKopi(String merk, int kondisi) {
        this.merk = merk;
        this.kondisi = kondisi;
    }

    public void pakaiMesin() {
        if (kondisi > 0) {
            kondisi -= 10;
            System.out.println("Mesin " + merk + " lagi dipakai. Kondisi mesin sekarang: " + kondisi + "%");
        } else {
            System.out.println("Waduh, mesin " + merk + " rusak! Harus diservis dulu.");
        }
    }
}

public class Barista {
    
    public String nama;
    public int stamina;
    public MesinKopi mesinAndalan;

    public Barista() {
        this.nama = "Barista Junior";
        this.stamina = 100;
        this.mesinAndalan = new MesinKopi("Mesin Generik", 100);
    }

    public Barista(String nama, int stamina, MesinKopi mesinAndalan) {
        this.nama = nama;
        this.stamina = stamina;
        this.mesinAndalan = mesinAndalan;
    }

    public void buatPesanan(String menuKopi) {
        System.out.println(this.nama + " lagi ngeracik " + menuKopi + "...");
        this.stamina -= 15;
        
        this.mesinAndalan.pakaiMesin(); 
        System.out.println("Pesanan " + menuKopi + " udah jadi! Sisa stamina " + this.nama + ": " + this.stamina);
    }

    public void mintaBantuan(Barista a) {
        System.out.println(this.nama + " lagi keteteran dan minta tolong ke " + a.nama);
        
        if (a.stamina >= 20) {
            a.stamina -= 20;
            this.stamina += 20;
            System.out.println(a.nama + " turun tangan ngebantu! Stamina " + this.nama + " naik jadi " + this.stamina);
        } else {
            System.out.println(a.nama + " juga lagi tepar, nggak bisa bantu dapet orderan.");
        }
    }
}