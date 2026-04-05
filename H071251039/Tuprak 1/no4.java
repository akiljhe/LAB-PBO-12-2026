import java.util.Scanner;

public class no4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Input: ");
        int n = input.nextInt();
        
        System.out.println("Output: " + f(n));
        
        input.close();
    }

    public static int f(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * f(n - 1);

    }

}