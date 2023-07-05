import java.io.File;
 import java.io.PrintStream;
 import java.util.HashMap;
 import java.util.Locale;
 import java.util.Map;
 import java.util.Scanner;
 
 
 public class C {
     @SuppressWarnings("FieldCanBeLocal")
     private static int caseNumber;
     private static Scanner scan;
 
     static int[] table = new int[256];
     static Prod[][] m = new Prod[][] {
             toProc("1,i,j,k".split(",")),
             toProc("i,1-,k,j-".split(",")),
             toProc("j,k-,1-,i".split(",")),
             toProc("k,j,i-,1-".split(","))
     };
 
     static {
         table['1'] = 0;
         table['i'] = 1;
         table['j'] = 2;
         table['k'] = 3;
     }
 
     static Prod[] toProc(String[] strs) {
         Prod[] prods = new Prod[strs.length];
         for (int i = 0; i < strs.length; i++) {
             String str = strs[i];
             prods[i] = new Prod(str.charAt(0), str.length() > 1 ? -1 : 1);
         }
 
         return prods;
     }
 
     Map<String, String> cache = new HashMap<String, String>();
     private char[] s;
 
     Prod mul(int start, int end) { 
         Prod res = new Prod(s[start]);
         while (++start < end) {
             int i1 = table[res.c];
             int i2 = table[s[start]];
 
             Prod p = m[i1][i2];
             res.c = p.c;
             res.sign *= p.sign;
         }
 
         return res;
     }
 
     Prod m(Prod m1, Prod m2) {
         int i1 = table[m1.c];
         int i2 = table[m2.c];
         return m[i1][i2];
     }
 
     Prod m(Prod m1, char m2) {
         int i1 = table[m1.c];
         int i2 = table[m2];
         return m[i1][i2];
     }
 
     Prod mul(Prod m1, Prod m2) {
         Prod p = m(m1, m2);
         Prod res = new Prod(p.c, p.sign);
         res.sign *= m1.sign * m2.sign;
         return res;
     }
 
     void mul(Prod m1, char m2) {
         Prod p = m(m1, m2);
         m1.c = p.c;
         m1.sign *= p.sign;
     }
 
     
     Prod findRightMul(Prod left, Prod prod) {
         Prod p = mul(left, prod);
         return new Prod(p.c, -p.sign);
     }
 
     boolean canBeReduced() {
         int len = s.length;
         if (len <= 2) {
             return false;
         }
 
         Prod all = mul(0, s.length);
         Prod l = new Prod(s[0], 1);
 
 
         for (int i1 = 1; i1 < len; ) {
             Prod cr = findRightMul(l, all);
 
             Prod c = new Prod(s[i1], 1);
             for (int i2 = i1 + 1; i2 < len; ) {
                 Prod r = findRightMul(c, cr);
 
                 if (l.c == 'i' && l.sign == 1
                     && c.c == 'j' && c.sign == 1
                     && r.c == 'k' && r.sign == 1) {
                     return true;
                 }
 
                 
                 
                 
                 
 
 
                 mul(c, s[i2]);
 
                 ++i2;
             }
 
             
             
             
             
 
 
             mul(l, s[i1]);
 
             ++i1;
         }
 
         return false;
     }
 
     void solve() {
         int length = scan.nextInt();
         int times = scan.nextInt();
 
         scan.nextLine();
         String word = scan.nextLine();
 
         StringBuilder str = new StringBuilder();
         for (int i = 0; i < times; ++i) {
             str.append(word);
         }
         s = str.toString().toCharArray();
 
         boolean ok = canBeReduced();
         System.out.printf("%s\n", ok ? "YES" : "NO");
     }
 
     static class Prod {
         char c;
         int sign = 1;
 
         Prod(char c) {
             this.c = c;
         }
 
         Prod(char c, int sign) {
             this.c = c;
             this.sign = sign;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         String file = "C-small-attempt0";
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         scan = new Scanner(new File(inFile));
 
         int cases = scan.nextInt();
         for (caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             new C().solve();
             System.out.flush();
         }
 
         scan.close();
     }
 
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 }
