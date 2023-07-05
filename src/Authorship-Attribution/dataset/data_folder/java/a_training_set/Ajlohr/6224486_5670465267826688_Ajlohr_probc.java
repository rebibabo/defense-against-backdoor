package codejam2015;
 
 import java.util.Scanner;
 
 public class ProbC {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        for(int cas  = 1;cas <= numcases;cas++ )
        {
         int L = sc.nextInt();
         long X = sc.nextLong();
         String word = sc.next();
         boolean isnegative =false;
         char curletter = word.charAt(0);
         for(int i=1;i<word.length();i++)
         {
             char newletter = word.charAt(i);
             char result = 'n';
             if(curletter =='i')
             {
                 if(newletter =='i')
                 {
                     isnegative ^=true;
                     result = '1';
                 }
                 if(newletter =='j')
                 {
                    
                     result = 'k';                   
                 }
                 if(newletter =='k')
                 {
                     isnegative ^=true;
                     result = 'j';                   
                 }
              }
             if(curletter =='j')
             {
                 if(newletter =='i')
                 {
                     isnegative ^=true;
                     result = 'k';                   
                 }
                 if(newletter =='j')
                 {
                     isnegative ^=true;
                     result = '1';                   
                 }
                 if(newletter =='k')
                 {
                    
                     result = 'i';                   
                 }               
             }
             if(curletter =='k')
             {
                 if(newletter =='i')
                 {
                    
                     result = 'j';                   
                 }
                 if(newletter =='j')
                 {
                     isnegative ^=true;
                     result = 'i';                   
                 }
                 if(newletter =='k')
                 {
                     isnegative ^=true;
                     result = '1';                   
                 }               
             }
             if(curletter=='1')
             {
                 if(newletter =='i')
                 {
                     result = 'i';
                 }
                 if(newletter =='j')
                 {
                     result = 'j';
                 }
                 if(newletter =='k')
                 {
                     result = 'k';
                 }
             }
             curletter = result;
         }
         isnegative = (X%2==0)?false:isnegative;
         if(((X%4==3)||(X%4==2))&&(curletter!='1'))
         {
             isnegative^=true;
         }
         if(X%2==0)
             curletter = '1';
         
         if(isnegative&&(curletter =='1'))
             System.out.println("Case #"+cas+": YES");
         else
             System.out.println("Case #"+cas+": NO");
        }
    }
 }
