package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class Round1CA implements Problem {
    
    private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    
    private int numGroups;
    private int[] groupCounts;
    private int[] currGroupCounts;
    private int totalCount;
    private String plan;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
        numGroups = Integer.parseInt(input.readLine());
        String[] groupCountsStr = input.readLine().split(" ");
        groupCounts = new int[numGroups];
        for(int i=0; i<numGroups; ++i){
            groupCounts[i] = Integer.parseInt(groupCountsStr[i]);
        }
   }
    
    private int getFirst(){
        int maxGroup = -1;
        int maxGroupCount = 0;
        for(int i=0; i<numGroups; ++i){
            if(currGroupCounts[i] > maxGroupCount){
                maxGroupCount = currGroupCounts[i];
                maxGroup = i;
            }
        }
        return maxGroup;
    }
    
    private int getSecond(){
        int maxGroup = getFirst();
        int maxAllowed = (totalCount - 1) / 2;
        for(int i=0; i<numGroups; ++i){
            if(i == maxGroup){
                continue;
            }
            if(currGroupCounts[i] > maxAllowed){
                return -1;
            }
        }
        return maxGroup;
    }
    
    private String getNext(){       
        int firstToRemove = getFirst();
        --currGroupCounts[firstToRemove];
        --totalCount;
        
        int secondToRemove = getSecond();
        if(secondToRemove < 0){
            return ALPHABET[firstToRemove];
        }
        
        --currGroupCounts[secondToRemove];
        --totalCount;
        
        return ALPHABET[firstToRemove] + ALPHABET[secondToRemove];
    }
    
   @Override
   public void solve() {
        currGroupCounts = new int[numGroups];
        totalCount = 0;
        for(int i=0; i<numGroups; ++i){
            currGroupCounts[i] = groupCounts[i];
            totalCount += groupCounts[i];
        }
        
        StringBuilder planBuilder = new StringBuilder();
        while(totalCount > 0){
            planBuilder.append(getNext());
            planBuilder.append(" ");
        }
        
        plan = planBuilder.toString();
   }
 
   @Override
   public String getSolution() {
     return plan;
   }
 
 }
