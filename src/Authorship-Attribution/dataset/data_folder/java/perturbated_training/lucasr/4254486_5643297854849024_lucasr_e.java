package round3;
 
 import java.util.Scanner;
 
 public class E {
    private static final int INF = 10000;
    static int N, D, best, curBest;
    static int[] leave; 
    
    static void backtrack(int pot, int posFrom) {
        if (best <= curBest) return;
        if (pot > D) {
            for (int i = 0; i < leave.length; i++) {
                if (leave[i] != leave[0]) return;
            }
            best = Math.min(best, curBest);
            return;
        }
        backtrack(pot * 2, 0);
        for (int pos = posFrom; pos < 2 * pot; pos++) {
            boolean can = true;
            for (int i = 0; i < N && can; i++) {
                int mod = i % (2*pot);
                if (mod < pot && leave[(pos + i)%N] == 0) can = false;
            }
            if (can) {
                for (int i = 0; i < N; i++) {
                    int mod = i % (2*pot);
                    if (mod < pot) leave[(pos + i)%N]--;
                }
                curBest++;
                backtrack(pot, pos);
                curBest--;
                for (int i = 0; i < N; i++) {
                    int mod = i % (2*pot);
                    if (mod < pot) leave[(pos + i)%N]++;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            N = sc.nextInt();
            D = sc.nextInt();
            leave = new int[N];
            for (int i = 0; i < leave.length; i++) {
                leave[i] = sc.nextInt();
            }
            best = INF;
            boolean can = true;
 
 
 
 
            if (can) backtrack(1, 0);
            System.out.println("Case #" + caze + ": " + (best == INF ? "CHEATERS!" : best +"") );
        }
    }
 
 }
