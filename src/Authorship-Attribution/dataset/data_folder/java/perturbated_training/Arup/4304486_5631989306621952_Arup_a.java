import java.util.*;
 
 public class a {
 
    public static void main(String[] args) {
 
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
 
        for (int loop=1; loop<=numCases; loop++) {
 
            String s = stdin.next();
 
            String res = ""+s.charAt(0);
 
            for (int i=1; i<s.length(); i++) {
 
                if (s.charAt(i) >= res.charAt(0))
                    res = s.charAt(i) + res;
                else
                    res = res + s.charAt(i);
 
            }
 
            System.out.println("Case #"+loop+": "+res);
        }
    }
 }