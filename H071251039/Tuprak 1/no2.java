import java.util.Scanner;

public class no2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input: ");
        String s = input.nextLine();

        String[] p = s.split("-");
        
        int d = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        String y = p[2];

        if (m < 1 || m > 12 || d < 1 || d > 31) {
            System.out.println("Format tanggal atau bulan salah!");
            input.close();
            return;
        }

        String b = "";
        switch (m) {
            case 1: b = "Januari"; break;
            case 2: b = "Februari"; break;
            case 3: b = "Maret"; break;
            case 4: b = "April"; break;
            case 5: b = "Mei"; break;
            case 6: b = "Juni"; break;
            case 7: b = "Juli"; break;
            case 8: b = "Agustus"; break;
            case 9: b = "September"; break;
            case 10: b = "Oktober"; break;
            case 11: b = "November"; break;
            case 12: b = "Desember"; break;
            default: System.out.println("Format bulan salah!");
                
        }

        String f;
        if (Integer.parseInt(y) > 26) {
            f = "19" + y;
        } else {
            f = "20" + y;
        }

        System.out.println("Output:");
        System.out.println(d + " " + b + " " + f);

        input.close();
    }
}