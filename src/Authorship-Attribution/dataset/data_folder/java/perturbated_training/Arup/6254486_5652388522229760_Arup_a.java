import java.util.*;
 
 public class a {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();;
 
        for (int loop=1; loop<=numCases; loop++) {
 
            long n = stdin.nextLong();
            long res = solve(n);
            if (res == -1) System.out.println("Case #"+loop+": INSOMNIA");
            else System.out.println("Case #"+loop+": "+res);
        }
    }
 
    public static long solve(long n) {
 
        int mask = 0;
        long mult = 1;
        while (mask != 1023) {
 
            long term = n*mult;
 
            while (term > 0) {
                int digit = (int)(term%10);
                mask = mask | (1<<digit);
                term /= 10;
            }
 
            if (mask == 1023) return n*mult;
            if (mult == 10000000) break;
            mult++;
        }
 
        return -1;
 
    }
 }