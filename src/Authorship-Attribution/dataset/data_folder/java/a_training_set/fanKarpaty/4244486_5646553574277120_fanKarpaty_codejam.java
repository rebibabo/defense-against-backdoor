
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 
 class Codejam {
    public static boolean has[] = new boolean[34];
    public static boolean sum[] = new boolean[100];
    
    public static void allSums(int[] coins) {
        for (int i = 0; i < 34; i++)sum[i] = false;
        int l = coins.length;
        for (int m = (1<<l) - 1; m > 0; m--) {
            int s = 0;
            for (int b = 0; b < l; b++) {
                if ((m & (1<<b)) != 0) {
                    s += coins[b];
                }
            }
            if (s < sum.length) {
                sum[s] = true;
            }
        }
    }
    
     public static void main(String args[]) {
         Scanner in = null;
        try {
            in = new Scanner(new FileInputStream("input.txt"));
            System.setOut(new PrintStream(new File("output.txt")));
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            
            int C = in.nextInt();
            int D = in.nextInt();
            int V = in.nextInt();
            int coins[] = new int[D];
            for (int i = 0; i < D; i++)coins[i] = in.nextInt();
            int ans = 0;
            while (true) {
                allSums(coins);
                int i = 1;
                for (;i <= V; i++) {
                    if (!sum[i])break;
                }
                if (i > V) {
                    break;
                } else {
                    ans++;
                    int[] tmp = coins;
                    coins = new int[D + ans];
                    for (int j = 0; j < tmp.length; j++) {
                        coins[j] = tmp[j];
                    }
                    coins[D + ans - 1] = i;
                }
            }
            
            System.out.println("Case #" + t + ": " + ans);
        }
     }
  
 }