package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 
 
 public class Round1CC implements Problem {
    
    private int numJ;
    private int numP;
    private int numS;
    private int k;
    
    private int maxDays;
    private Map<String, Integer> counts;
    private List<String> eachDay;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
        String[] args = input.readLine().split(" ");
        numJ = Integer.parseInt(args[0]);
        numP = Integer.parseInt(args[1]);
        numS = Integer.parseInt(args[2]);
        k = Integer.parseInt(args[3]);
   }
    
    private void addDay(int j, int p, int s){
        String jpKey = "J" + j + "P" + p;
        Integer jpCount = counts.get(jpKey);
        if(jpCount != null && jpCount == k){
            return;
        }
        
        String jsKey = "J" + j + "S" + s;
        Integer jsCount = counts.get(jsKey);
        if(jsCount != null && jsCount == k){
            return;
        }
        
        String psKey = "P" + p + "S" + s;
        Integer psCount = counts.get(psKey);
        if(psCount != null && psCount == k){
            return;
        }
        
        eachDay.add(j + " " + p + " " + s);
        
        if(jpCount == null){
            counts.put(jpKey, 1);
        } else {
            counts.put(jpKey, jpCount + 1);
        }
        
        if(jsCount == null){
            counts.put(jsKey, 1);
        } else {
            counts.put(jsKey, jsCount + 1);
        }
        
        if(psCount == null){
            counts.put(psKey, 1);
        } else {
            counts.put(psKey, psCount + 1);
        }
    }
    
    private void getEachDayWithJP(){
        int numR = Math.min(numS, k);
        for(int j=1; j<=numJ; ++j){
            for(int p=1; p<=numP; ++p){
                for(int r=1; r<=numR; ++r){
                    for(int s=1; s<=numS; ++s){
                        addDay(j, p, s);
                    }
                }
            }
        }
    }
    
    private void getEachDayWithJS(){
        int numR = Math.min(numP, k);
        for(int j=1; j<=numJ; ++j){
            for(int s=1; s<=numS; ++s){
                for(int r=1; r<=numR; ++r){
                    for(int p=1; p<=numP; ++p){
                        addDay(j, p, s);
                    }
                }
            }
        }
    }
    
    private void getEachDayWithPS(){
        int numR = Math.min(numP, k);
        for(int p=1; p<=numP; ++p){
            for(int s=1; s<=numS; ++s){
                for(int r=1; r<=numR; ++r){
                    for(int j=1; j<=numJ; ++j){
                        addDay(j, p, s);
                    }
                }
            }
        }
    }
    
   @Override
   public void solve() {
        eachDay = new LinkedList<>();
        counts = new HashMap<>();
        int jpPair = numJ * numP;
        int jsPair = numJ * numS;
        int psPair = numP * numS;
        
        int minPair = Math.min(Math.min(jpPair, jsPair), psPair);
        if(minPair == jpPair){
            getEachDayWithJP();
        } else if(minPair == jsPair){
            getEachDayWithJS();
        } else {
            getEachDayWithPS();
        }
        
        maxDays = eachDay.size();
   }
 
   @Override
   public String getSolution() {
        StringBuilder solBuilder = new StringBuilder();
        solBuilder.append(maxDays);
        for(String currDay: eachDay){
            solBuilder.append(System.lineSeparator());
            solBuilder.append(currDay);
        }
     return solBuilder.toString();
   }
 
 }
