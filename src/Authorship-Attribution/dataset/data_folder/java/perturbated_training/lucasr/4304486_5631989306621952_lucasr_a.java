package round1a;
 
 import java.util.Scanner;
 
 public class A {
    static void putBest(char[] S, char[] res, int from, int to) {
        int bestIdx = -1;
        for (int i = 0; i < to; i++) {
            if (bestIdx == -1 || S[i] >= S[bestIdx]){
                bestIdx = i;
            }
        }
        if (bestIdx != -1) {
            res[from] = S[bestIdx];
            for (int i = bestIdx; i < to; i++) {
                res[from + i] = S[i];
            }
        }
        if (bestIdx > 0) putBest(S, res, from + 1, bestIdx);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            char[] S = sc.next().toCharArray();
            char[] res = new char[S.length];
            putBest(S, res, 0, S.length);
            String ans = new String(res);
            System.out.println("Case #" + caze + ": " + ans);
        }
    }
 
 }
