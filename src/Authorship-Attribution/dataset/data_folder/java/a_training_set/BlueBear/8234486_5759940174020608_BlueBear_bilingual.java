import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class Bilingual {
 
   static int n;
   static String[][] a;
 
   public static void main(String[] args) throws FileNotFoundException {
     Scanner cin = new Scanner(new File("C-small-attempt0.in"));
     PrintStream cout = new PrintStream("C-small-attempt0.out");
     
     
     
     
 
     int _case = 0;
 
     for (int T = cin.nextInt(); T > 0; T--) {
       _case++;
 
       n = cin.nextInt();
       cin.nextLine();
       a = new String[n][];
       a[0] = cin.nextLine().split("[ ]+");
       a[1] = cin.nextLine().split("[ ]+");
       for (int i = 2; i < n; i++) {
         a[i] = cin.nextLine().split("[ ]+");
       }
       Set<String> e = new HashSet<>();
       Set<String> f = new HashSet<>();
       for (String str : a[0])
         e.add(str);
       for (String str : a[1])
         f.add(str);
 
       Set<String> hehe = new HashSet<>();
       int ans = 0;
       for (String str : e)
         if (f.contains(str)) {
           ans++;
           hehe.add(str);
         }
 
       if (n > 2) {
         int min = Integer.MAX_VALUE;
 
         for (int mask = 0; mask < (1 << (n - 2)); mask++) {
           Set<String> ee = new HashSet<>();
           Set<String> ff = new HashSet<>();
           for (int i = 2; i < n; i++) {
             if ((mask & (1 << (i - 2))) == 0) {
               
               for (String str : a[i]) {
                 ee.add(str);
               }
             } else {
               
               for (String str : a[i]) {
                 ff.add(str);
               }
             }
           }
 
           Set<String> haha = new HashSet<>();
           for (String str : ee)
             if ((f.contains(str) || ff.contains(str)) && !hehe.contains(str) && !haha.contains(str))
               haha.add(str);
           for (String str : ff)
             if ((e.contains(str) || ee.contains(str)) && !hehe.contains(str) && !haha.contains(str))
               haha.add(str);
 
           min = Math.min(min, haha.size());
 
           
           
           
           
         }
 
         ans += min;
       }
 
       cout.printf("Case #%d: %d%n", _case, ans);
     }
 
     cin.close();
     cout.close();
   }
 }
