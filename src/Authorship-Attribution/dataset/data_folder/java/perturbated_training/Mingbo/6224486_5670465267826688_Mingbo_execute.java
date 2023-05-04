package Dijkstra;
 import java.util.*;
 public class Execute {
     List<String> thisCase;
     int max;
     String result="";
     int sLength;
     int repeat;
     String s;
     Map<String,String> m = new HashMap<String,String>();
      public Execute(List<String> thisCase){
         m.put("11", "1");m.put("1i", "i");m.put("1j", "j");m.put("1k", "k");
         m.put("i1", "i");m.put("ii", "-1");m.put("ij", "k");m.put("ik", "-j");
         m.put("j1", "j");m.put("ji", "-k");m.put("jj", "-1");m.put("jk", "i");
         m.put("k1", "k");m.put("ki", "j");m.put("kj", "-i");m.put("kk", "-1");
         
         m.put("-11", "-1");m.put("-1i", "-i");m.put("-1j", "-j");m.put("-1k", "-k");
         m.put("-i1", "-i");m.put("-ii", "1");m.put("-ij", "-k");m.put("-ik", "j");
         m.put("-j1", "-j");m.put("-ji", "k");m.put("-jj", "1");m.put("-jk", "-i");
         m.put("-k1", "-k");m.put("-ki", "-j");m.put("-kj", "i");m.put("-kk", "1");
         
         this.thisCase=thisCase;
         sLength=Integer.parseInt(thisCase.get(0));
         repeat=Integer.parseInt(thisCase.get(1));
         s=thisCase.get(2);
         
         String temp="1";
         for (int i=0; i<repeat; i++){
             for (int j=0; j<sLength; j++){
                 temp+=s.charAt(j);
                 System.out.print(temp+"|");
                 temp=m.get(temp);
                 System.out.println(temp);
                 if(result.equals("")) {
                     if (temp.equals("i")) {result+=temp; temp="1";}
                 } else if (result.equals("i")){
                     if (temp.equals("j")) {result+=temp; temp="1";}
                 } else if (result.equals("ij")){
                     if (temp.equals("k")) {result+=temp; temp="1";}
                 }
                 System.out.println(result);
             }
         }
         if (!temp.equals("1")) result+=temp;
      }
      
      public String output(){
         if (result.equals("ijk")) return "YES";
         else return "NO";
      }
      
 }
