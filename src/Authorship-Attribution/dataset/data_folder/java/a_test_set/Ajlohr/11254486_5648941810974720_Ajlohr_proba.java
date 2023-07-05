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
        int[] numbercount= new int[10];
        reduce(lettercount,numbercount,"ZERO",0,'Z');
        reduce(lettercount,numbercount,"SIX",6,'X');
        reduce(lettercount,numbercount,"FOUR",4,'U');
        reduce(lettercount,numbercount,"THREE",3,'R');
        reduce(lettercount,numbercount,"SEVEN",7,'S');
        reduce(lettercount,numbercount,"EIGHT",8,'G');
        reduce(lettercount,numbercount,"TWO",2,'T');
        reduce(lettercount,numbercount,"ONE",1,'O');
        reduce(lettercount,numbercount,"FIVE",5,'V');
        reduce(lettercount,numbercount,"NINE",9,'I');
        String out = "";
        for(int i=0;i<10;i++)
        {
            if(numbercount[i]>0)
            {
                numbercount[i]--;
                out+=i;
                i--;
            }
        }
        System.out.println("Case #"+curcase+": "+out);      
        }
        
    }
    public static void reduce(char[] lets,int[] nums,String S, int num,char unique)
    {       
        while(lets[unique]>0)
    {
            for(int i=0;i<S.length();i++)
            {
                lets[S.charAt(i)]--;
            }
        nums[num]++;
    }
    }
 }
