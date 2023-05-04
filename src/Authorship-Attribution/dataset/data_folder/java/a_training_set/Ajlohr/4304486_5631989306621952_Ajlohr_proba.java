package Round1A;
 
 import java.util.Scanner;
 
 public class ProbA {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
            String s = sc.next();
            String wb = "";
            char[] c = s.toCharArray();
            wb = wb+c[0];
            for(int i=1;i<c.length;i++)
            {
                if(c[i]<wb.charAt(0))
                    wb = wb+c[i];
                else
                    wb = c[i]+wb;
            }
                System.out.println("Case #"+curcase+": "+wb);
        
        }
        
    }
 }
