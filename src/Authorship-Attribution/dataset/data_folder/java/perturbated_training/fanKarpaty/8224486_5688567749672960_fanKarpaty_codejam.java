import java.util.*;
 import java.io.*;
 import java.math.*;
 
 
 class Codejam {
 
     public static void main(String args[]) {
         Scanner in = null;
        try {
            in = new Scanner(new FileInputStream("input.txt"));
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        try {
            System.setOut(new PrintStream("output.txt"));
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        int ar[] = new int[1000003];
        ar[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            if (ar[i] > 0) {
                ar[i] = Math.min(ar[i], ar[i - 1] + 1);
            } else {
                ar[i] = ar[i - 1] + 1;
            }
            int x = i;
            int r = 0;
            while (x > 0) {
                r *= 10;
                r += x % 10;
                x /= 10;
            }
            if (r <= 1000000) {
                if (ar[r] == 0) {
                    ar[r] = ar[i] + 1;
                } else {
                    ar[r] = Math.min(ar[r], ar[i] + 1);
                }
            }
        }
         int T = in.nextInt(); 
         for (int t = 1; t <= T; t++) {
            in.nextLine();
            int n = in.nextInt();
            
            
            System.out.println("Case #" + t + ": " + ar[n]);
            
         }
         
         
     }
  
 }