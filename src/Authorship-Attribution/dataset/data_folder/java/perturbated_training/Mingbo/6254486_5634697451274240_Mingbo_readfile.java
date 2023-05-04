package revengeofthePancakes;
 import java.io.*;
 import java.util.*;
 public class ReadFile {
     public long caseNum;
     public List<String> cases=new ArrayList<String>();
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
            for(long i=0;i<caseNum;i++){
                String temp=in.next();
                
                
                this.cases.add(temp);
            }
        }
        finally{
            in.close();
        }
     }
 }
