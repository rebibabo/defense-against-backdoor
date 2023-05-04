package StandingOvation;
 import java.util.*;
 public class Execute {
     List<String> thisCase;
     int max;
     int result=0;
     String toLoop;
      public Execute(List<String> thisCase){
         this.thisCase=thisCase;
         this.max = Integer.parseInt(thisCase.get(0));
         toLoop=thisCase.get(1);
         int sum=Character.getNumericValue(toLoop.charAt(0));
         for (int i=1; i<=max; i++){
             if(sum<i && Character.getNumericValue(toLoop.charAt(i))>0) {
                 result+=i-sum;
                 sum=i;
             }
             System.out.println(sum+"|"+result);
             sum+=Character.getNumericValue(toLoop.charAt(i)); 
         }
      }
      
      public String output(){
         return ""+result;
      }
      
 }
