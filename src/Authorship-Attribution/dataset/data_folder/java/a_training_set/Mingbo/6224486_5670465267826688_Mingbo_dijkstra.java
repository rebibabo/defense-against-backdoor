package Dijkstra;
 
 public class Dijkstra {
     public static void main(String args[]){
        ReadFile RF=new ReadFile("C-small-attempt0.in");
        WriteResult WR=new WriteResult();
        
        for(int caseIndex=0;caseIndex<RF.caseNum;caseIndex++){
            System.out.println(RF.cases.get(caseIndex).toString());
             WR.addCaseResult(new Execute(RF.cases.get(caseIndex)).output());
            
        }
        WR.printResult();
     }
 }
