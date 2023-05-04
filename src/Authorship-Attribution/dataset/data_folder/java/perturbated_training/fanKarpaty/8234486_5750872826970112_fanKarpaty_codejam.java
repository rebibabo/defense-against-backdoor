
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 
 
 class Codejam {
    
    public static double THR = 0.0000005;
    
     public static void main(String args[]) {
         Scanner in = null;
        try {
            in = new Scanner(new FileInputStream("input.txt"));
            System.setOut(new PrintStream(new File("output.txt")));
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        Map<Character, Integer> map = new HashMap<Character, Integer> ();
        map.put('<', 1);
        map.put('^', 2);
        map.put('>', 4);
        map.put('v', 8);
        
        int T = in.nextInt();
        in.nextLine();
        for (int t = 1; t <= T; t++) {
            String s = in.nextLine();
            String[] ss = s.split(" ");
            int N = Integer.parseInt(ss[0]);
            double V = Double.parseDouble(ss[1]);
            double X = Double.parseDouble(ss[2]);
        
            double[] r = new double[N];
            double c[] = new double[N];
            
            for (int i = 0; i < N; i++) {
                s = in.nextLine();
                ss = s.split(" ");
                r[i] = Double.parseDouble(ss[0]);
                c[i] = Double.parseDouble(ss[1]);
            }
            
            double ans = -1;
            if (N == 1) {
                if (Math.abs(X - c[0]) < THR) {
                    ans = V / r[0];
                }
            } else {
                if (c[0] > c[1]) {
                    double tmp = r[0];
                    r[0] = r[1];
                    r[1] = tmp;
                    tmp = c[0];
                    c[0] = c[1];
                    c[1] = tmp;
                }
                
                if (c[0] < X - THR && c[1] > X - THR) {
                    if (Math.abs(X - c[1]) < THR) {
                        ans = V / r[1];
                    } else {
                        double v1 = V * (X - c[0]) / (c[1] - c[0]);
                        double v0 = V - v1;
                        ans = Math.max(v0 / r[0], v1 / r[1]);
                    }
                } else if (Math.abs(X - c[0]) < THR) {
                    if (c[1] > X + THR) {
                        ans = V / r[0];
                    } else {
                        ans = V / (r[0] + r[1]);
                    }
                }
            }
            
            
            
            System.out.println("Case #" + t + ": " + (ans < -0.5 ? "IMPOSSIBLE" : ans));
        }
     }
  
 }