import java.util.Scanner;
 
 
 public class ProbDsmall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        for(int curcase = 1; curcase<=numcases; curcase++)
        {
            int R =sc.nextInt();
            int C = sc.nextInt();
            if(R==2)
            {
                if(C%3==0)
                {
                    System.out.println("Case #"+curcase+": 2");
                }else{
                System.out.println("Case #"+curcase+": 1");
                }
            continue;
            }if(R==3)
            {
                System.out.println("Case #"+curcase+": 2");
                continue;
                
            }
            if(R==4)
            {
                System.out.println("Case #"+curcase+": "+(1+((C%3==0)?2:0)));
                continue;
                
            }
            if(R==5)
            {
                System.out.println("Case #"+curcase+": 1");
                continue;
                
            }
            if(R==6)
            {
                if(C%3==0)
                {
                    int count =0;
                    count+=2;
                    count++;
                    count+=3;
                    System.out.println("Case #"+curcase+": "+count);
                }else{
                    
                System.out.println("Case #"+curcase+": 2");
                }
                continue;
                
            }
        }
    }
 
 }
