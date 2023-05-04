
 public class Stall {
 
    int stallNumber;
    boolean inUse;
    int spaceToLeft;
    int spaceToRight;
    int smallerSpace;
    int largerSpace;
    
    public Stall(int i, int s){
        stallNumber = i;
        if(i == 0 || i == (s + 1)){
            inUse = true;
        }
        else{
            inUse = false;
        }
    }
 
 
    
 }
