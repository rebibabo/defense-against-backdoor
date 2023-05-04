import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class CountingSheep {
 
     static int n;
     static String[][] a;
 
     private static long doit(long x) {
         Set<Integer> set = new HashSet<>();
         for (int i = 0; i < 10; i++) set.add(i);
         long hehe = 0;
         while (set.size() > 0) {
             hehe += x;
             String tmp = hehe + "";
             for (int j = 0; j < tmp.length(); j++) set.remove(tmp.charAt(j) - '0');
         }
         return hehe;
     }
 
     public static void main(String[] args) throws FileNotFoundException {
     Scanner cin = new Scanner(new File("A-small-attempt0.in"));
     PrintStream cout = new PrintStream("A-small-attempt0.out");
         
         
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             long n = cin.nextLong();
             StringBuilder ans = new StringBuilder();
             if (n == 0) ans.append("INSOMNIA");
             else ans.append(doit(n));
             cout.printf("Case #%d: %s%n", _case, ans.toString());
         }
 
         cin.close();
         cout.close();
     }
 }
