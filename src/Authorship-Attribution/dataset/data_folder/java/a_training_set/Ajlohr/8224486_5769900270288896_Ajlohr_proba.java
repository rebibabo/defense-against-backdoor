import java.util.Scanner;
 
 
 public class ProbA {
 public static void main(String[] args)
 {
    Scanner sc= new Scanner(System.in);
    int numcases = sc.nextInt();
    for(int curcase = 1;curcase <=numcases;curcase++)
    {
        int N = sc.nextInt();
        System.out.println("Case #"+curcase+": "+compute(N));
    }
 }
 public static int compute(int N)
 {
    String s = Integer.toString(N);
    if(s.length()==1)
        return N;
 
    String fh = s.substring(0, s.length()/2);
    String sh = s.substring(s.length()/2, s.length());
    int powten = 1;
    int halfpowten =1;
 
    for(int i=0;i<s.length()-1;i++)
    {
        powten*=10;
        if(i<fh.length()-1)
            halfpowten*=10;
    }
    int numsaid = compute(powten-1);
    numsaid++;
    
    if(Integer.parseInt(fh)!=halfpowten)
    {
    if(Integer.parseInt(sh)!=0)
    {
    int stepone = Integer.parseInt((new StringBuilder(fh)).reverse().toString());
    numsaid+=stepone;
    numsaid++;
    numsaid+=Integer.parseInt(sh)-1;
    }else{
        numsaid++;
        numsaid+= Integer.parseInt((new StringBuilder(fh)).reverse().toString());
        numsaid++;
    }
    }else{
        numsaid+=Integer.parseInt(sh);
    }
 
    if(s.length()%2==1)
    {
        String fh2 = s.substring(0, s.length()/2+1);
        String sh2 = s.substring(s.length()/2+1, s.length());
        int powten2 = 1;
        int halfpowten2 =1;
 
        for(int i=0;i<s.length()-1;i++)
        {
            powten2*=10;
            if(i<fh.length()-1)
                halfpowten2*=10;
        }
        int numsaid2 = compute(powten2-1);
        numsaid2++;
        
        if(Integer.parseInt(fh2)!=halfpowten2)
        {
        if(Integer.parseInt(sh2)!=0)
        {
        int stepone2 = Integer.parseInt((new StringBuilder(fh2)).reverse().toString());
        numsaid2+=stepone2;
        numsaid2++;
        numsaid2+=Integer.parseInt(sh2)-1;
        }else{
            numsaid2++;
            numsaid2+= Integer.parseInt((new StringBuilder(fh2)).reverse().toString());
            numsaid2++;
        }
        }else{
            numsaid2+=Integer.parseInt(sh2);
        }       
        return Math.min(numsaid, numsaid2);
    }
    
    return numsaid;
    
 }
 }
