package fractiles;
 
 public class Fractiles {
     public static void main(String args[]){
        ReadFile RF=new ReadFile("test.in");
        WriteResult WR=new WriteResult();
        for(int caseIndex=0;caseIndex<RF.caseNum;caseIndex++){
            
             WR.addCaseResult(new Execute(RF.cases.get(caseIndex)).output());
            
        }
        WR.printResult();
     }
 }
