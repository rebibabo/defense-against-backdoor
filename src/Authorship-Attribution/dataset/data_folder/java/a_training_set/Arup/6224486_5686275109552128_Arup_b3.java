import java.util.*;
 
 public class b3 {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        
        for (int loop=1; loop<=numCases; loop++) {
 
            int n = stdin.nextInt();
            int[] vals = new int[n];
 
            
            for (int i=0; i<n; i++)
                vals[i] = stdin.nextInt();
            Arrays.sort(vals);
 
            
            System.out.println("Case #"+loop+": "+solve(vals));
        }
    }
 
    public static int solve(int[] arr) {
 
        int n = arr.length;
        int max = arr[n-1];
        int res = max;
        for (int limit=max-1; limit>=1; limit--) {
 
            int extra = 0;
            for (int i=0; i<n; i++)
                extra += ((arr[i]+limit-1)/limit-1);
            res = Math.min(res, extra+limit);
        }
        return res;
    }
 }