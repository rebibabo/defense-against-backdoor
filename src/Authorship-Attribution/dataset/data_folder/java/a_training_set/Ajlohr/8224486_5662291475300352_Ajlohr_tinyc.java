import java.util.Scanner;
 
 
 public class tinyC {
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        int numcases = sc.nextInt();
        for(int curcase = 1;curcase <=numcases;curcase++)
        {
            int N = sc.nextInt();
            int h1pos;
            int h2pos;
            int h1speed;
            int h2speed;
            if(N==1)
            {
                int D1 = sc.nextInt();
                int numhike = sc.nextInt();
                if(numhike ==1)
                {
                    System.out.println("Case #"+curcase+": 0");
                    continue;
                }
                h1speed = sc.nextInt();
                h2speed = h1speed+1;
                h1pos = D1;
                h2pos =D1;
                
                System.out.println("Case #"+curcase+": 0");
                continue;               
            }else
            {
                h1pos = sc.nextInt();
                sc.nextInt();
                h1speed =sc.nextInt();
                h2pos = sc.nextInt();
                sc.nextInt();
                h2speed = sc.nextInt();
 
                if(h1pos<h2pos)
                {
                    int swap = h1pos;
                    h1pos = h2pos;
                    h2pos = swap;
                    swap  =h1speed;
                    h1speed = h2speed;
                    h2speed = swap;
                }
                if(h1speed == h2speed)
                {
                    System.out.println("Case #"+curcase+": 0");
                    continue;                               
                }
                double disttwo = (h1pos - h2pos)/360;
                double timeone = (1+disttwo)*h1speed*h2speed/(h1speed-h2speed);
                double hittime;
                if(timeone>0)
                    hittime = timeone;
                else
                    hittime = (360 - disttwo)*h1speed*h2speed/(h2speed-h1speed);
                double h1time = (360-h1pos)/360*h1speed;
                double h2time = (360-h2pos)/360*h2speed;
                if((h1time>= hittime)||(h2time>=hittime))
                {
                    System.out.println("Case #"+curcase+": 1");
                    continue;                       
                }else
                {
                    System.out.println("Case #"+curcase+": 0");
                    continue;                       
                    
                }
            }
            
        }
    }
 
 }
