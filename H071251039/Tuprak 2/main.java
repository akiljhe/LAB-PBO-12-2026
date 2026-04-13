public class main {
    public static void main(String[] args) {
        MesinKopi mesinA = new MesinKopi("La Marzocco", 100);
        Barista b1 = new Barista("Rendi", 100, mesinA);
        Barista b2 = new Barista("Bagas", 80, new MesinKopi("Simonelli", 90));

        b1.buatPesanan("Americano");
        b1.buatPesanan("Latte");
        
        System.out.println();
        
        b1.mintaBantuan(b2);
    }
}

