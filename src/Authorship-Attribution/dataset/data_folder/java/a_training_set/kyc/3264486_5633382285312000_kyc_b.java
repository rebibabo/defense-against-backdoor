package codejam2017q;
 
 import org.junit.Test;
 import lib.CodeJamCommons;
 
 public class B extends CodeJamCommons {
 
     @Test
     public void run() throws Exception {
         file("B-small-attempt0");
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             String k = in.next();
             if (isTidy(k)) {
                 out.printf("Case #%d: ", n + 1);
                 out.println(k);
                 continue;
             }
             for (int until = k.length(); ; until--) {
                 String t = "";
                 for (int i = 0; i < until; i++)
                     t += k.charAt(i);
                 for (int i = until; i < k.length(); i++)
                     t += '0';
                 String u = Long.parseLong(t) - 1 + "";
                 if (isTidy(u)) {
                     out.printf("Case #%d: ", n + 1);
                     out.println(u);
                     break;
                 }
             }
         }
     }
 
     boolean isTidy(String u) {
         for (int i = 1; i < u.length(); i++)
             if (u.charAt(i) < u.charAt(i - 1))
                 return false;
         return true;
     }
 }
