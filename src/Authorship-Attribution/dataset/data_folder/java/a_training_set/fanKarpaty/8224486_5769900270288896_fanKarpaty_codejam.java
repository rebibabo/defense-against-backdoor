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
        
         int T = in.nextInt(); 
         for (int t = 1; t <= T; t++) {
            in.nextLine();
            int r = in.nextInt();
            int c = in.nextInt();
            int n = in.nextInt();
            int ret = 1000000000;
            for (int m = (1 << (r * c)) - 1; m >= 0; m--) {
                
                Set<Integer> set = new HashSet<Integer> ();
                set.clear();
                int count = 0;
                int s = 0;
                for (int b = r*c - 1; b>= 0; b--) {
                    if ((m & (1 << b)) != 0) {
                        count++;
                        if (b % c > 0 && set.contains(b-1))s++;
                        if (b % c < c - 1 && set.contains(b + 1))s++;
                        if (set.contains(b-c))s++;
                        if (set.contains(b+c))s++;
                        set.add(b);
                    }
                }
                if (count == n && s < ret) {
                    ret = s;
                }
            }
            
            System.out.println("Case #" + t + ": "  + ret);
            
         }
         
         
     }
  
 }