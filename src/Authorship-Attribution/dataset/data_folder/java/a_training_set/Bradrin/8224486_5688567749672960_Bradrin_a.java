import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
    
    public void solve(Scanner scan, PrintWriter out) {
        long result = 0;
        long current = 1;
        long goal = scan.nextLong();
        
        for (int i = 0; i < length(goal); i++) {
            long v = 1;
            for (int j = 1; j <= i; j++) {
                v *= 10;
            }
            result += find(v, v/10);
        }
        for (int i = 1; i < length(goal); i++) {
            current *= 10;
        }
        if (current == goal) {
            System.out.println(result);
            out.println(result);
            return;
        }
        result += find(goal, current);
        System.out.println(result);
        out.println(result);
    }
    
    private long find(long v, long l) {
        long result = v - l;
        for (int i = 1; i <= length(v); i++) {
            long firstNReverse = reverse(firstN(v, i));
            if (l + firstNReverse == v) {
                result = Math.min(result, firstNReverse);
            } else {
                if (v >= reverse(l+firstNReverse)) {
                    System.out.println(firstNReverse + " + " + 1 + " + " + (v - reverse(l+firstNReverse)) + " = " + (firstNReverse + 1 + (v - reverse(l+firstNReverse))));
                    result = Math.min(result, firstNReverse + 1 + (v - reverse(l+firstNReverse)));
                }
            }
        }
        for (int i = 1; i <= length(v); i++) {
            long firstNReverse = reverse(firstN(v, i)-1);
            if (l + firstNReverse == v) {
                result = Math.min(result, firstNReverse);
            } else {
                if (v >= reverse(l+firstNReverse)) {
                    System.out.println(firstNReverse + " + " + 1 + " + " + (v - reverse(l+firstNReverse)) + " = " + (firstNReverse + 1 + (v - reverse(l+firstNReverse))));
                    result = Math.min(result, firstNReverse + 1 + (v - reverse(l+firstNReverse)));
                }
            }
        }
        
        return result;
    }
    
    private long firstN(long val, long n) {
        long length = length(val) - n;
        for (long i = 0; i < length; i++) {
            val /= 10;
        }
        return val;
    }
    
    private long length(long n) {
        long result = 0;
        while (n > 0) {
            result++;
            n /= 10;
        }
        return result;
    }
    
    private long reverse(long n) {
        long result = 0;
        while (n > 0) {
            result += n%10;
            result *= 10;
            n /= 10;
        }
        result /= 10;
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        String filename = "A-small-attempt3";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new A().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }