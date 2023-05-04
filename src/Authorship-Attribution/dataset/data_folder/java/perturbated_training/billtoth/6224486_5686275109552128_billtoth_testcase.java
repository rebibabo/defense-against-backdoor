import java.util.ArrayList;
 
 
 public class TestCase {
 
    int max;
    int maxCount;
    int nextHighest;
    ArrayList<Integer> dinerPlates;
    int minutes;
    int nextCount;
    
    public TestCase (int d, ArrayList<Integer> dP) {
        dinerPlates = dP;
        minutes = 0;
    }
    
    public int evaluateCase() {
        maxFindAndCount();
        makeEven();
        maxFindAndCount();
        while (isCutWorthIt(max, maxCount) && !doesNextHighestRuin()){
            cutDownMax();
            maxFindAndCount();
            makeEven();
            maxFindAndCount();
        }
        while (max > 0){
            regularMinute();
            maxFindAndCount();
            
        }
        
        return minutes;
    }
    
    private void makeEven(){
        if (max %2 != 0 && !oneNine()){
            regularMinute();
            
        }
    }
 
    private boolean isCutWorthIt(int pancakesMax, int platesWithMax) {
        if (pancakesMax %2 != 0){
            pancakesMax --;
        }
        if ((pancakesMax/2 + platesWithMax) <= pancakesMax){
            return true;
        }
        else{
            return false;
        }
    }
 
    private boolean doesNextHighestRuin() {
        if (nextHighest > max/2 && !isCutWorthIt(nextHighest, nextCount)){
            return true;
        }
        
        else{
            return false;
        }
    }
    
    private void cutDownMax() {
        
        for (int i=0;  i < dinerPlates.size(); i++){
            if (oneNine() && dinerPlates.get(i) == max){
                dinerPlates.set(i, 6);
                dinerPlates.add(3);
                minutes++;
            }
            else if (dinerPlates.get(i) == max){
                dinerPlates.set(i, max/2);
                dinerPlates.add(max/2);
                minutes++;
            }
        }
    }
    
    private void regularMinute() {
        for (int i=0;  i < dinerPlates.size(); i++){
            dinerPlates.set(i, dinerPlates.get(i) - 1);
        }
        minutes++;
    }
    
    
 
    private void maxFindAndCount() {
        max = 0;
        maxCount = 0;
        nextHighest = 0;
        nextCount = 0;
        for (int i = 0; i < dinerPlates.size(); i++){
            if (dinerPlates.get(i) > max){
                max = dinerPlates.get(i);
                maxCount = 1;
            }
            else if (dinerPlates.get(i) == max){
                maxCount++;
            }
            
            if(dinerPlates.get(i) < max && dinerPlates.get(i) > nextHighest){
                nextHighest = dinerPlates.get(i);
            }
        }
        
        for (int i = 0; i < dinerPlates.size(); i++){
            if (dinerPlates.get(i) == nextHighest){
                nextCount++;
            }
        }
    }
 
    private boolean oneNine(){
        if (max == 9 && maxCount ==1){
            return true;
        }
        else{
            return false;
        }
    }
 }
