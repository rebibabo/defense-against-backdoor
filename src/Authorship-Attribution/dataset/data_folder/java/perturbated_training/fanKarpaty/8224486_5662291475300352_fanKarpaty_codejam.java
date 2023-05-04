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
            int n = in.nextInt();
            int c = 0;
            long d[] = new long[2];
            long m[] = new long[2];
            for (int i = 0; i < n; i++) {
                in.nextLine();
                d[c] = in.nextLong();
                int h = in.nextInt();
                m[c] = in.nextLong();
                c += h;
                if (h == 2) {
                    d[1] = d[0];
                    m[1] = m[0] + 1;
                }
            }
            
            if (m[0] < m[1]) {
                long z = d[0];
                d[0] = d[1];
                d[1] = z;
                z = m[0];
                m[0] = m[1];
                m[1] = z;
            }
            
            int ret = 0;
            
            if (c > 1) {
                if ((360L - d[0]) * m[0] >= (720L - d[1]) * m[1]) ret = 1;
            }
            
            System.out.println("Case #" + t + ": " + ret);
            
         }
         
         
     }
  
 }