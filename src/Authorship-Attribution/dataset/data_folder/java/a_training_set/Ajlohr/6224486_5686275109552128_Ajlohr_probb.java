package codejam2015;
 
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProbB {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        for(int cas  = 1;cas <= numcases;cas++ )
        {
            System.out.print("Case #"+cas+": ");
        
        int numdiners = sc.nextInt();
        int[] plates = new int[numdiners + numdiners*1000+1];
        for(int i=0;i<numdiners;i++)
        {
            plates[i] =sc.nextInt();
        }
        Arrays.sort(plates);
        int bestnum = plates[numdiners + numdiners*1000];
        for(int i=1;i<=bestnum;i++)
        {
            plates[0] = (plates[numdiners+numdiners*1000])/2;
            plates[1] = (plates[numdiners+numdiners*1000]+1)/2;
            plates[numdiners+numdiners*1000] = 0;
            Arrays.sort(plates);
            if(i + plates[numdiners+numdiners*1000]<bestnum)
            {
                bestnum = plates[numdiners+numdiners*1000]+i;
            
        }
        }
        System.out.println(bestnum);
        }
    }
 }
