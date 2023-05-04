import java.util.*;
 
 public class c {
 
     public static HashSet<String> eWords;
     public static HashSet<String> fWords;
 
     public static void main(String[] args) {
 
         Scanner stdin = new Scanner(System.in);
         int numCases = Integer.parseInt(stdin.nextLine());
 
         for (int loop=1; loop<=numCases; loop++) {
 
             int n = Integer.parseInt(stdin.nextLine());
             String english = stdin.nextLine();
             String french = stdin.nextLine();
             eWords = new HashSet<String>();
             fWords = new HashSet<String>();
             StringTokenizer tok = new StringTokenizer(english);
             while (tok.hasMoreTokens())
                 eWords.add(tok.nextToken());
             tok = new StringTokenizer(french);
             while (tok.hasMoreTokens())
                 fWords.add(tok.nextToken());
 
             String[] rest = new String[n-2];
             for (int i=0; i<n-2; i++)
                 rest[i] = stdin.nextLine();
 
             int res = 10000000;
 
             
             for (int mask=0; mask<(1<<(n-2)); mask++)
                 res = Math.min(res, solve(rest, mask));
 
             System.out.println("Case #"+loop+": "+res);
         }
     }
 
     public static int solve(String[] rest, int mask) {
 
         HashSet<String> french = new HashSet<String>();
         HashSet<String> english = new HashSet<String>();
 
         for (int i=0; i<rest.length; i++) {
 
             
             if ((mask & (1 << i)) > 0) {
                 StringTokenizer tok = new StringTokenizer(rest[i]);
                 while (tok.hasMoreTokens())
                     french.add(tok.nextToken());
             }
 
             
             else {
                 StringTokenizer tok = new StringTokenizer(rest[i]);
                 while (tok.hasMoreTokens())
                     english.add(tok.nextToken());
             }
         }
 
         int cnt = 0;
         for (String s: eWords) {
             if (fWords.contains(s) || french.contains(s))
                 cnt++;
 
         }
         for (String s: english) {
             if (!eWords.contains(s) && (french.contains(s) || fWords.contains(s)))
                 cnt++;
         }
         return cnt;
     }
 }
