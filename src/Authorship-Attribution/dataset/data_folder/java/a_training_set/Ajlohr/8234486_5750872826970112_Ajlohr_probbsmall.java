import java.util.Scanner;
 
 
 public class ProbBsmall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        for(int curcase = 1; curcase<=numcases; curcase++)
        {
            int N = sc.nextInt();
            double V = sc.nextDouble();
            double X = sc.nextDouble();
            double[] R = new double[N];
            double[] S = new double[N];
            for(int i=0;i<N;i++)
            {
                R[i] =sc.nextDouble();
                S[i] = sc.nextDouble();
            }
            if(N==1)
            {
                if(X == S[0])
                System.out.println("Case #"+curcase+": "+(V/R[0]));
                else
                System.out.println("Case #"+curcase+": IMPOSSIBLE");
            }else
            {
            if(S[0] == S[1])
            {
                if(X == S[0])
                System.out.println("Case #"+curcase+": "+(V/(R[0]+R[1])));
                else
                System.out.println("Case #"+curcase+": IMPOSSIBLE");    
                continue;
            }
            if(S[0]==X)
            {
                System.out.println("Case #"+curcase+": "+(V/(R[0])));
                continue;
            }
            if(S[1]==X)
            {
                System.out.println("Case #"+curcase+": "+(V/(R[1])));
                continue;
            }
            
            if(((X>S[0])&&(X>S[1]))||((X<S[0])&&(X<S[1])))
                {System.out.println("Case #"+curcase+": IMPOSSIBLE");   continue;}
            
 
            double t2 = V*(X-S[0])/(R[1]*(S[1]-S[0]));
            double t1 = (V - R[1]*t2)/R[0];
            System.out.println("Case #"+curcase+": "+Math.max(t1, t2));
                    
            }
        }
    }
 }
