package qualy;
 
 import java.util.Scanner;
 
 public class C {
    private static int J, N;
    private static int[] primes, vals;
    
    private static int composite(int[] number, int base) {
        for (int i = 0; i < primes.length; i++) {
            int rem = 0;
            for (int j = 0; j < number.length; j++) {
                rem *= base;
                rem += number[j];
                rem %= primes[i];
            }
            if (rem == 0) return primes[i];
        }
        return -1;
    }
    
    private static void printComposite(int[] number, int pos) {
        if (J <= 0) return;
        if (pos == N - 1) {
            boolean ok = true;
            for (int base = 2; base <= 10; base++) {
                if ((vals[base] = composite(number, base)) == -1) ok = false;
            }
            if (ok) {
                for (int i = 0; i < number.length; i++) {
                    System.out.print(number[i]);
                }
                for (int b = 2; b <= 10; b++) {
                    System.out.print(" " + vals[b]);
                }
                System.out.println();
                J--;
            }
        } else {
            number[pos] = 0;
            printComposite(number, pos + 1);
            number[pos] = 1;
            printComposite(number, pos + 1);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            System.out.println("Case #" + caze + ":");
            N = sc.nextInt();
            J = sc.nextInt();
            vals = new int[11];
            primes = new int[] {2, 3, 5, 7, 11, 13, 17, 19} ;
            int[] number = new int[N];
            number[0] = number[number.length-1] = 1;
            printComposite(number, 1);
        }
    }
 
 }
