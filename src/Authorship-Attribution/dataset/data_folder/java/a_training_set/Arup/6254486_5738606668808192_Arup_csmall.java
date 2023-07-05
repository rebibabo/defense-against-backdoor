import java.util.*;
 
 public class csmall {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        int n = stdin.nextInt();
        int max = stdin.nextInt();
        int printed = 0;
 
        for (int i=(1<<(n-1))+1; i<(1<<n); i+=2) {
 
            if (printed == max) break;
 
            int[] factors = new int[11];
            for (int base=2; base<=10; base++)
                factors[base] = getFactor(i, n, base);
 
            boolean add = true;
            for (int j=2; j<=10; j++)
                if (factors[j] < 0)
                    add = false;
 
            if (add) {
                System.out.print(Integer.toBinaryString(i));
                for (int j=2; j<=10; j++)
                    System.out.print(" "+factors[j]);
                System.out.println();
                printed++;
            }
        }
    }
 
    public static int getFactor(int num, int size, int base) {
 
        long val = 0;
        long x = 1L;
        for (int i=0; i<size; i++) {
            val += x*((num >> i)&1);
            x *= base;
        }
 
        for (int i=2; i<Math.sqrt(val)+1; i++)
            if (val%i == 0)
                return i;
        return -1;
    }
 }
