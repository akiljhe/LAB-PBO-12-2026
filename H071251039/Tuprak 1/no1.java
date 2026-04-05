import java.util.Scanner;

public class no1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Masukkan Judul Film: ");
        String judul = input.nextLine();

        String[] kataKata = judul.split(" ");
        String hasil = "";

        for (String k : kataKata) {
            if (!k.isEmpty()) {
                String kapital = k.substring(0, 1).toUpperCase() + k.substring(1).toLowerCase();
                hasil += kapital + " ";
            }
        }
        System.out.println(hasil);
        input.close();
    }
}