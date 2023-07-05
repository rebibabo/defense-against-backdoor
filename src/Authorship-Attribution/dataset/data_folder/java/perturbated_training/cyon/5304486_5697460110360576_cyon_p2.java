package roud1a;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
 import java.io.PrintWriter;
 
 public class p2 {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());
         int P = Integer.parseInt(st.nextToken());
         int[] ri = new int[N];
         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
             ri[i] = Integer.parseInt(st.nextToken());
         }
         int[][] qij = new int[N][P];
         for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             for (int j = 0; j < P; j++) {
                 qij[i][j] = Integer.parseInt(st.nextToken());
             }
         }
         List<Integer> perm = new ArrayList<>();
         for (int i = 0; i < P; i++) {
             perm.add(i);
         }
 
 
         if (N == 1) {
             int[] w = new int[P];
             int cnt = 0;
             for (int pi : perm) {
                 w[cnt++] = qij[0][pi];
             }
             return "" + check1(w, ri);
         }
 
         int maxPa = 0;
         do {
             if (N == 2) {
                 PA[] w = new PA[P];
                 int cnt = 0;
                 for (int pi : perm) {
                     w[cnt] = new PA(qij[0][cnt], qij[1][pi]);
                     cnt++;
                 }
                 maxPa = Math.max(maxPa, check2(w, ri));
             }
 
             perm = nextPerm(perm);
         } while (perm != null);
 
         return "" + maxPa;
     }
 
     private static int check2(PA[] w, int[] ri) {
         int ok = 0;
         for (int i = 0; i < w.length; i++) {
             int low0 = (int)Math.ceil(w[i].p1 / (1.1 * ri[0]) - 1.e-8);
             int high0 = (int)Math.floor(w[i].p1 / (0.9 * ri[0]) + 1.e-8);
 
             int low1 = (int)Math.ceil(w[i].p2 / (1.1 * ri[1]) - 1.e-8);
             int high1 = (int)Math.floor(w[i].p2 / (0.9 * ri[1]) + 1.e-8);
 
 
             if (high0 < low1 || high1 < low0) {
                 continue;
             }
 
             if (Math.max(low1, low0) <= Math.min(high0, high1)) {
                 ok++;
             }
         }
         return ok;
     }
 
     private static int check1(int[] w, int[] ri) {
         debug(w, ri);
         int ok = 0;
         for (int i = 0; i < w.length; i++) {
             int low = (int)Math.ceil(w[i] / (1.1 * ri[0]) - 1.e-8);
             int high = (int)Math.floor(w[i] / (0.9 * ri[0]) + 1.e-8);
             debug(i, low, high, 0.9 * ri[0], 1.1 * ri[0]);
             if (low <= high) {
                 ok++;
             }
         }
         return ok;
     }
 
     private static final class PA {
         int p1, p2;
 
         public PA(int p1, int p2) {
             this.p1 = p1;
             this.p2 = p2;
         }
     }
 
 
     public static <T extends Comparable<T>> List<T> nextPerm(List<T> currentPerm) {
         int size = currentPerm.size();
         int badIdx = size;
         for (int i = size - 2; i >= 0; i--) {
             if (currentPerm.get(i).compareTo(currentPerm.get(i+1)) < 0) {
                 badIdx = i;
                 break;
             }
         }
         if (badIdx == size) {
             return null;
         }
 
         T elem = currentPerm.get(badIdx);
         int spos = badIdx+1;
         T maxi = null;
         for (int i=spos; i < size; i++) {
             if (currentPerm.get(i).compareTo(elem) > 0 && (maxi == null || maxi.compareTo(currentPerm.get(i)) > 0)) {
                 maxi = currentPerm.get(i);
                 spos = i;
             }
         }
 
         List<T> nextPerm = new ArrayList<>(currentPerm);
         Collections.swap(nextPerm, badIdx, spos);
         Collections.sort(nextPerm.subList(badIdx + 1, size));
         return nextPerm;
     }
 
     public static void debug(Object...args) {
         
     }
 }
