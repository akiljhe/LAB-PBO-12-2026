import java.util.Scanner;

public class no5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] nums = {
            {1, 2, 3}, 
            {4, 5, 6}, 
            {7, 8, 9}
        };

        try {
            System.out.print("Input: ");
            int n = input.nextInt();
            boolean f = false;

            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums[i].length; j++) {
                    if (nums[i][j] == n) {
                        System.out.println("Found " + n + " at [" + i + "][" + j + "]");
                        f = true;
                        break;
                    }
                }
                if (f) break;
            }

            if (!f) System.out.println("Not Found");

        } catch (Exception e) {
            System.out.println("Error: Input harus angka");
        } finally {
            input.close();
        }
        
    }
}