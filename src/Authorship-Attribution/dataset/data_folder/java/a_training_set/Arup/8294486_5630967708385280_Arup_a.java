import java.util.*;
 
 public class a {
    
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        
        for (int loop=1; loop<=numCases; loop++) {
            
            int d = stdin.nextInt();
            int n = stdin.nextInt();
            
            double maxReach = 0;
            for (int i=0; i<n; i++) {
                int mark = stdin.nextInt();
                int speed = stdin.nextInt();
                double reach = (double)(d-mark)/speed;
                maxReach = Math.max(maxReach, reach);
            }
            
            double maxS = (double)d/maxReach;
            
            System.out.println("Case #"+loop+": "+maxS);
        }
    }
 }