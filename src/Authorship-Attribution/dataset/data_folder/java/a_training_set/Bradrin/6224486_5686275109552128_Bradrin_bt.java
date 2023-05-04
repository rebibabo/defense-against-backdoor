import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class Bt {
    
    int N = 1000;
    
    public int split(int[] array, int time, int limit) {
        int[] t = new int[N];
        Arrays.sort(array);
        for (int j = 0; j < t.length; j++) {
            t[j] = array[j];
        }
        int max = t[N-1];
        if (time > limit) return time + max;
        int min = max + time;
        for (int i = 1; i <= t[N-1]/2; i++) {
            t[0] = i;
            t[N-1] -= i;
            min = Math.min(min, split(t, time + 1, limit));
            min = Math.min(min, time + 1 + t[N-1]);
            for (int j = 0; j < t.length; j++) {
                t[j] = array[j];
            }
        }
        return min;
    }
    
    public void solve(Scanner scan, PrintWriter out) {
        int length = scan.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < length; i++) {
            array[i] = scan.nextInt();
        }
        Arrays.sort(array);
        int min = split(array, 0, array[N-1]);
        System.out.println(min);
        out.println(min);
    }
    
    public static void main(String[] args) throws Exception {
         Scanner scan = new Scanner(new FileReader("B-small-attempt5.in"));
         PrintWriter out = new PrintWriter("B-small-attempt5.out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new Bt().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }