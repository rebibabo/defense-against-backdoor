import java.util.*;
 
 public class a {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        
        for (int loop=1; loop<=numCases; loop++) {
 
            int n = stdin.nextInt();
            int[] vals = new int[n+1];
            String s = stdin.next();
 
            
            for (int i=0; i<s.length(); i++)
                vals[i] = s.charAt(i) - '0';
 
            
            
            
            int curUp = vals[0], res = 0;
            for (int i=1; i<vals.length; i++) {
                res = Math.max(res, i-curUp);
                curUp += vals[i];
            }
 
            
            System.out.println("Case #"+loop+": "+res);
        }
    }
 }