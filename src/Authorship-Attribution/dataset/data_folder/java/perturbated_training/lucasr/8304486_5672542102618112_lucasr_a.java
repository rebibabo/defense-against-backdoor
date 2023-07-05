package round3;
 
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 
 public class A {
     
     static int[] cant;
     static int acum;
     static int L;
     
     static void initChildren(int pos) {
         if (pos == L) {
             if (acum == 0) return;
             int[] next = next(cant);
             if (!Arrays.equals(next, cant)) {
                 int from = toInt(cant);
                 int to = toInt(next);
                 List<Integer> ccc = children[L].get(to);
                 if (ccc == null) {
                     ccc = new ArrayList<>();
                     children[L].put(to, ccc);
                 }
                 ccc.add(from);
             }
             return;
         }
         for (int i = 0; i <= L - acum; i++) {
             acum += i;
             cant[pos] = i;
             initChildren(pos+1);
             acum -= i;
         }
     }
     
     static int number(int n, int L) {
         long ret = 1;
         while (n > 0) {
             int cant = n%10;
             for (int i = 1; i <= cant;i++) {
                 ret *= L;
                 ret /= i;
                 L--;
             }
             n/=10;
         }
         return (int)ret;
     }
     
     static int[] next(int[] val) {
         int[] ret = new int[val.length];
         for (int i = 0; i < val.length; i++) {
             if (val[i] > 0) ret[val[i] - 1]++;
         }
         return ret;
     }
     
     static int toInt(int[] digs) {
         int ret = 0;
         for (int i = 0; i < digs.length; i++) {
             ret *= 10;
             ret += digs[i];
         }
         return ret;
     }
     
     static boolean ok(int[] vals) {
         int sum = 0;
         for (int i = 0; i < vals.length; i++) {
             sum += vals[i];
         }
         return sum <= vals.length;
     }
     
     static int MAX = 10;
     
     static Map<Integer, Integer>[] cache = new Map[MAX];
     static Map<Integer, List<Integer>>[] children = new Map[MAX];
     
     static int calc(int n, int L) {
         Integer ret = cache[L].get(n);
         if (ret != null) return ret;
         int val = 1;
         List<Integer> cc = children[L].get(n);
         if (cc == null || cc.isEmpty()) {
             if (n != 1 || L != 1) val += number(n, L);
         } else {
             for (int child : cc) {
                 val += calc(child, L);
             }
         }
 
         cache[L].put(n, val);
         return val;
     }
 
     
     
     public static void main(String[] args) {
         for (int i = 1; i < MAX; i++) {
             cache[i] = new HashMap<>();
             children[i] = new HashMap<>();
             L = i;
             acum = 0;
             cant = new int[L];
             initChildren(0);
         }
         Scanner sc = new Scanner(System.in);
         int cases = sc.nextInt();
         for (int caze = 1; caze <= cases; caze++) {
             char[] elemTmp = sc.next().toCharArray();
             int[] elem = new int[elemTmp.length];
             for (int i = 0; i < elem.length; i++) {
                 elem[i] = elemTmp[i] - '0';
             }
             
             System.out.println("Case #" + caze + ": " + (ok(elem) ? calc(toInt(elem), elem.length) : 1));
         }
     }
 }
