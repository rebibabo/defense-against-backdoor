package coinjam;
 import java.util.*;
 public class Execute {
     String result;
      public Execute(Map<String,Integer> thisCase){
         this.core(thisCase);
      }
      private void core(Map<String,Integer> thisCase){
         String r = "\n";
         int n = thisCase.get("n");
         int j = thisCase.get("j");
         int numOfDigit = n/2 - 2;
         int count=1;
         while(count<=j){
             String half = constructS(count,numOfDigit);
             r = r+half+half;
             for (int i=2; i<=10;i++){
                 r = r + " "+convertStoInt(half,i);
             }
             r = r+"\n";
             count++;
         }
         
         result = r;
      }
      
      
      private long convertStoInt(String half, int base) {
         long l =0;
         for (int i =half.length()-1; i>=0;i--){
             long t = Character.getNumericValue(half.charAt(i));
             for(int j =0; j<half.length()-1-i;j++){
                 t=t*base;
             }
             l=l+t;
         }
         return l ;
     }
      
     private String constructS(int count, int numOfDigit) {
         String s = "";
         while(count>0){
             s= count%2 + s;
             count= count/2;
         }
         for (int i =s.length(); i<numOfDigit;i++){
             s="0"+s;
         }
         
         return "1"+s+"1";
     }
     
     public String output(){
         return result;
      }
      
 }
