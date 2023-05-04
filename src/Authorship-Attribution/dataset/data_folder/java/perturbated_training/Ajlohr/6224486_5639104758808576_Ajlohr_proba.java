package codejam2015;
 
 import java.util.Scanner;
 
 public class ProbA {
 public static void main(String[] args)
 {
    Scanner sc = new Scanner(System.in);
    int numcases = sc.nextInt();
    for(int cas  = 1;cas <= numcases;cas++ )
    {
        System.out.print("Case #"+cas+": ");
        int numstanding=0;
        int numadded=0;
        int maxshy = sc.nextInt();
        String aud = sc.next();
        for(int i=0;i<=maxshy;i++)
        {
            if(aud.charAt(i)!='0')
            {
                numadded+= Math.max(i-numstanding,0);
                numstanding+=Math.max(i-numstanding,0);
                numstanding+= Integer.parseInt(""+aud.charAt(i));
            }
        }
        System.out.println(numadded);
    }
    sc.close();
 }
 }
