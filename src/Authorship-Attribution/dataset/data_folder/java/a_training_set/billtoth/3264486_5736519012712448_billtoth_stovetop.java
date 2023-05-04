import java.io.File;
 import java.io.FileNotFoundException;
 
 public class Stovetop {
 
    String pancakeArrangement;
    int flipperSize;
    int numberOfFlips;
    int numberOfCakes;
    
    public Stovetop(String c, int f) {
        pancakeArrangement = c;
        flipperSize = f;
        numberOfFlips = 0;
        numberOfCakes = pancakeArrangement.length();
    }
 
    public String evaluate() {
        for(int i = 0; i < numberOfCakes; i++){
            if(pancakeArrangement.substring(i,i+1).equals("-") 
                    && (numberOfCakes-flipperSize) >= (i)){
                flip(i);
            }
            if(pancakeArrangement.substring(i,i+1).equals("-")
                && (numberOfCakes-flipperSize) < (i)){
                return "IMPOSSIBLE";
            }   
        }
        return Integer.toString(numberOfFlips);
    }
    
    private void flip(int i){
        for(int j = i; j < i+flipperSize; j++){
            if(pancakeArrangement.substring(j,j+1).equals("-")){
                pancakeArrangement = pancakeArrangement.substring(0,j) +
                        "+" + pancakeArrangement.substring(j+1);
            }
            else{
                pancakeArrangement = pancakeArrangement.substring(0,j) +
                        "-" + pancakeArrangement.substring(j+1);
            }
        }
        numberOfFlips++;
    }
 
 }
