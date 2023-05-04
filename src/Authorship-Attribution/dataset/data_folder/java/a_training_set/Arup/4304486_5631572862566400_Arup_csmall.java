import java.util.*;
 
 public class csmall {
 
    public static int n;
    public static int[] bff;
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
 
 
            n = stdin.nextInt();
            bff = new int[n];
            for (int i=0; i<n; i++)
                bff[i] = stdin.nextInt()-1;
            boolean[] used = new boolean[n];
            int[] perm = new int[n];
 
            System.out.println("Case #"+loop+": "+solveRec(perm, used, 0));
 
        }
    }
 
    public static int solveRec(int[] perm, boolean[] used, int k) {
 
        int best = eval(perm, k);
        if (k == n) return best;
 
        for (int i=0; i<n; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[k] = i;
                int tmp = solveRec(perm, used, k+1);
                best = Math.max(best,tmp);
                used[i] = false;
            }
        }
 
        return best;
    }
 
    public static int eval(int[] perm, int k) {
 
        for (int i=0; i<k; i++) {
            if (bff[perm[i]] != perm[(i+1)%k] && bff[perm[i]] != perm[(i+k-1)%k])
                return 0;
        }
        return k;
    }
 }