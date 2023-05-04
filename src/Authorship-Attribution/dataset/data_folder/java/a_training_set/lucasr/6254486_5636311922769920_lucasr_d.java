package qualy;
 
 import java.util.Scanner;
 
 public class D {
    
    private static int K;
    
    private static long nextPos(long current, int needed) {
        return current * K + needed; 
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            K = sc.nextInt();
            int C = sc.nextInt();
            int S = sc.nextInt();
            System.out.print("Case #" + caze + ":");
            if (C * S >= K) {
                int current = 0;
                for (int i = 0; i < (K + C - 1) / C; i++) {
                    long val = 0;
                    for (int j = 0; j < C; j++) {
                        val = nextPos(val, current);
                        current++;
                        current %= K;
                    }
                    System.out.print(" " + (val + 1));
                }
                System.out.println();
            } else {
                System.out.println(" IMPOSSIBLE");
            }
        }
    }
 
 }
