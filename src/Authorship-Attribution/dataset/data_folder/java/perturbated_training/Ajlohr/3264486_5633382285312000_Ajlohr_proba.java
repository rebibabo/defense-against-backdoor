package Qualification;
 
 import java.util.Scanner;
 
 public class ProbA {
 public static void main(String[] args)
 {
    Scanner sc = new Scanner(System.in);
    long t = sc.nextLong();
    sc.nextLine();
    for(int curcase=1;curcase<=t;curcase++)
    {
        System.out.println("Case #"+curcase+": "+check(sc.nextLine()));
    }
    
 }
 public static String check(String a)
 {
    for(int i=0;i<a.length()-1;i++)
    {
        if(a.charAt(i)>a.charAt(i+1))
        {
            String s = a.substring(0,i)+Character.toString((char) (a.charAt(i)-1));
            if (s.charAt(0)=='0')
            s="";
            for (int j=a.length()-1;j>i;j--)
            {
                s+="9";
            }
            return check(s);
        }
    }
    return a;
 }
 
 }
