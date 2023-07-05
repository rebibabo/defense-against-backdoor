package codejam2017q;
 
 import org.junit.Test;
 import lib.CodeJamCommons;
 
 public class A extends CodeJamCommons {
 
     @Test
     public void run() throws Exception {
         file("A-small-attempt0");
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             char[] cs = in.next().toCharArray();
             int k = in.nextInt();
             int ans = 0;
             for (int i = 0; i + k <= cs.length; i++)
                 if (cs[i] == '-') {
                     for (int j = 0; j < k; j++)
                         cs[i + j] = cs[i + j] == '+' ? '-' : '+';
                     ans++;
                 }
             boolean good = true;
             for (char c : cs)
                 if (c == '-')
                     good = false;
             out.printf("Case #%d: ", n + 1);
             out.println(good ? ans + "" : "IMPOSSIBLE");
         }
     }
 }
