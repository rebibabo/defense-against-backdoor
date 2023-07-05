package gcj2017.r2.b;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Scanner;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "B-small-attempt0.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            fout.write(String.format("Case #%d: ", (t + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
    
    int solve1(int[] P, int[] B) {
        int M = P.length;
        List<Integer> X1 = new LinkedList<>();
        List<Integer> X2 = new LinkedList<>();
        List<Integer> Y1 = new LinkedList<>();
        List<Integer> Y2 = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            if (B[i] == 1) {
                if (P[i] == 1) X1.add(P[i]);
                else X2.add(P[i]);
            }
            else {
                if (P[i] == 1) Y1.add(P[i]);
                else Y2.add(P[i]);
            }
        }
        int ans = 0;
        ans += X1.size();
        ans += Y1.size();
        for (int i = 0; i < X1.size(); i++) if (Y2.size() > 0) Y2.remove(0);
        for (int i = 0; i < Y1.size(); i++) if (X2.size() > 0) X2.remove(0);
        ans += Math.max(X2.size(), Y2.size());
        return ans;
    }
    
    int solve2(int[] P, int[] B, int rides) {
        int M = P.length;
        List<Integer> X = new LinkedList<>();
        List<Integer> Y = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            if (B[i] == 1) {
                X.add(P[i]);
            }
            else {
                Y.add(P[i]);
            }
        }
        Collections.sort(X);
        Collections.sort(Y);
        int[][] t = new int[rides][2];
        int ans2 = 0;
        
        for (;;) {
            if (Y.size() == 0 && X.size() == 0) break;
            
            int b = 0;
            int p = 0;
            if (X.size() == 0) {
                p = Y.get(0); Y.remove(0);
                b = 1;
            } else if (Y.size() == 0) {
                p = X.get(0); X.remove(0);
                b = 0;
            } else {
                if (X.get(0) <= Y.get(0)) {
                    p = X.get(0); X.remove(0);
                    b = 0;
                } else {
                    p = Y.get(0); Y.remove(0);
                    b = 1;
                }
            }
            
            boolean success = false;
            for (int i = 0; i < rides; i++){
                if (t[i][b] != 0) continue;
                if (t[i][1-b] == p) continue;
                t[i][b] = p;
                success = true;
                break;
            }
            if (!success) {
                ans2++;
            }
            
        }
        
        return ans2;
    }
    void solve(Scanner sc, PrintWriter out) {
        int N = sc.nextInt();
        int C = sc.nextInt();
        int M = sc.nextInt();
        int[] P = new int[M];
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            P[i] = sc.nextInt();
            B[i] = sc.nextInt();
        }
        
        int ans = 0, ans2 = 0;
        if (C == 2) {
            ans = solve1(P, B);
            ans2 = solve2(P, B, ans);
            
            
        } else {
            ans = -999999999;
            ans2 = -999999999;
        }
        out.println(ans + " " + ans2);
    }
 
    
 }
