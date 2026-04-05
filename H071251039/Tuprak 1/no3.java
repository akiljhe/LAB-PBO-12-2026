import java.util.Scanner;

public class no3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Input Password: ");
        String p = input.nextLine();

        boolean pjg = p.length() >= 8;
        boolean bsr = false;
        boolean kcl = false;
        boolean ngr = false;

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (Character.isUpperCase(c)) bsr = true;
            if (Character.isLowerCase(c)) kcl = true;
            if (Character.isDigit(c)) ngr = true;
        }

        if (pjg && bsr && kcl && ngr) {
            System.out.println("Password Valid");
        } else {
            System.out.println("Password Tidak Valid");
        }

        input.close();
    }
}