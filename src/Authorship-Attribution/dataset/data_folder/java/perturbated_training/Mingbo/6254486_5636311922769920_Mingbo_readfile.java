package fractiles;
 import java.io.*;
 import java.util.*;
 public class ReadFile {
     public long caseNum;
     public List<Map<String,Integer>> cases=new ArrayList<Map<String,Integer>>();
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
                Map<String,Integer> m = new HashMap<String,Integer>();
                m.put("k", in.nextInt());
                m.put("c", in.nextInt());
                m.put("s", in.nextInt());
                
                
                this.cases.add(m);
            }
        }
        finally{
            in.close();
        }
     }
 }
