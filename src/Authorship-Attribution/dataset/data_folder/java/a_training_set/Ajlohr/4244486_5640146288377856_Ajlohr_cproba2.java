package codejam2015;
 
 import java.util.Scanner;
 
 public class CProbA2 {
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            int numcases = sc.nextInt();
            ML:
            for(int cas  = 1;cas <= numcases;cas++ )
            {
                System.out.print("Case #"+cas+": ");
                int R = sc.nextInt();
                int C = sc.nextInt();
                int W = sc.nextInt();
                System.out.println(recsolv(C,W,R));
            }
        }
        public static int recsolv(int C,int W,int R)
        {
            int hitway = R-1+ ((C>W)?W+1:W);
            if(C<2*W)
            {
                return hitway;
            }
            int missway = R+Math.min(recsolv(W,W,R)+recsolv(C-W-1,W,R),recsolv(C-W,W,R));
            return Math.max(hitway, missway);
        }
 }
