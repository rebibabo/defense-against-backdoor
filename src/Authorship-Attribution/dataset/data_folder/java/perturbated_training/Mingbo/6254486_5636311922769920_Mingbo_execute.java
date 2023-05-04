package fractiles;
 import java.util.*;
 public class Execute {
     String result;
      public Execute(Map<String,Integer> thisCase){
         this.core(thisCase);
      }
      private void core(Map<String,Integer> thisCase){
         String r = "";
         int k = thisCase.get("k");
         int c = thisCase.get("c");
         int s = thisCase.get("s");
         for(int i =1; i<=s; i++){
             r = r+(i)+" ";
         }
         result = r;
      }
      
      
      public String output(){
         return result;
      }
      
 }
