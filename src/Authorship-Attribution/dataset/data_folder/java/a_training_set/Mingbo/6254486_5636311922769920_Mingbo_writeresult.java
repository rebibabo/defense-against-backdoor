package fractiles;
 
 import java.io.*;
 public class WriteResult {
     private String result="";
     private int caseCount=0;
      public WriteResult(){      
      }
      public void printResult(){
        try {
            PrintWriter pw=new PrintWriter(new File("Result.out"));
            pw.print(result);
            pw.flush();
            System.out.print(result);
            pw.close();
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }        
      }
      public void addCaseResult(String s){
         this.caseCount++;
         result=result+"Case #"+this.caseCount+": "+s+'\n';
      }
 }
