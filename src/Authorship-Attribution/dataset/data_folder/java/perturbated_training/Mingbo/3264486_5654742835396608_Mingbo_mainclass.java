package bathStall;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class MainClass {
     public static void main(String args[]){
        ReadFile RF=new ReadFile("test.in");
        WriteResult WR=new WriteResult();
        
        for(int caseIndex=0;caseIndex<RF.caseNum;caseIndex++){
            System.out.println(RF.cases.get(caseIndex).toString());
             WR.addCaseResult(new Execute(RF.cases.get(caseIndex)).output());
            
        }
        WR.printResult();
     }
     
     static class ReadFile {
         public int caseNum;
         public List<List<String>> cases=new ArrayList<>();
         public ReadFile(String fileName){      
            try {
                Scanner in = new Scanner(new File(fileName));
                this.startRead(in);
            } catch (FileNotFoundException e1) {
                
                e1.printStackTrace();
                System.out.println("cannot found file");
            }       
         }
         
         private void startRead(Scanner in){
            try{
                if(in.hasNext()) caseNum=in.nextInt();
                for(int i=0;i<caseNum;i++){
                    List<String> temp=new ArrayList<>();
                    temp.add(in.next());
                    temp.add(in.next());
                    this.cases.add(temp);
                }
            }
            finally{
                in.close();
            }
         }
     }
     
     static class WriteResult {
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
 }
