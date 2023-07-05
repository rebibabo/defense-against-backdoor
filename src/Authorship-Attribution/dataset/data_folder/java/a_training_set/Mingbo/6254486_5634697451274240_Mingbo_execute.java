package revengeofthePancakes;
 import java.util.*;
 public class Execute {
     String result;
      public Execute(String thisCase){
         this.core(thisCase);
      }
      private void core(String thisCase){
         int r = calculate(thisCase);
         result = r+"";
      }
      
      private int calculate(String s){   
         boolean flag = false;
         while(s.length()>0){
             if(s.charAt(0)=='+'){
                 s = s.substring(1,s.length());
                 flag = true;
             } else {
                 break;
             }
         }
         
         int index=s.length();
         if(index==0) return 0;     
         for (int i = 0;i<index; i++){
             if(s.charAt(i) == '+'){
                
                 String fhalf = s.substring(0,i);
                 s=s.substring(i,s.length());
                 fhalf=flip(fhalf);
                 s=fhalf+s;
                 
                 if (flag){
                     return 2+calculate(s);
                 } else {
                     return 1+calculate(s);
                 }
                 
             }
         }
         if (flag){
             return 2;
         } else {
             return 1;
         }       
      }
      
      private String flip(String s){
         String toReturn = "";
         for (int i = s.length();i>0; i--){
             if(s.charAt(i-1)=='+') {
                 toReturn = toReturn + "-";
             } else {
                 toReturn = toReturn + "+";
             }
         }
         return toReturn;
      }
      
      
      public String output(){
         return result;
      }
      
 }
