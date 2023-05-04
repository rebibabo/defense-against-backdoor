import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class Googlements {
 
     static Map<String, Integer> f = new HashMap<>();
 
     static void f(String s) {
         f.put(s, f.getOrDefault(s, 0) + 1);
 
 
         int len = s.length();
         int[] cnt = new int[len];
         for (int i = 0; i < len; i++)
             if (s.charAt(i) != '0')
                 cnt[s.charAt(i) - '1']++;
 
         String m = "";
         for (int i = 0; i < len; i++)
             m += (char)('0' + cnt[i]);
         if (!m.equals(s))
             f(m);
     }
 
     static boolean isValid(String s) {
         int len = s.length();
         for (int i = 0; i < len; i++)
             if (s.charAt(i) > ('0' + len))
                 return false;
         return true;
     }
 
     static void dfs(int len, String s) {
         if (s.length() >= len) {
             if (!s.matches("0+")) {
 
                 f(s);
             }
         } else {
             for (int i = 0; i <= len; i++)
                 dfs(len, s + (char) (i + '0'));
         }
     }
 
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("C:\\Users\\Yuan\\Desktop\\A-small-attempt0.in"));
         PrintStream cout = new PrintStream("A-small-attempt0.out");
 
 
 
 
 
         for (int i=1; i<=5; i++)
             dfs(i, "");
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             String s = cin.next();
             String ans = "" + f.getOrDefault(s, 0);
 
             cout.printf("Case #%d: %s%n", _case, ans);
         }
 
         cin.close();
         cout.close();
     }
 }
