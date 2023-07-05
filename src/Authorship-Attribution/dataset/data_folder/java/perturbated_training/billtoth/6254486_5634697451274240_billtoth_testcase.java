
 public class TestCase {
 
    String stack;
    int flips;
    int stackSize;
    String last;
    
    public TestCase (String s) {
        stack = s;
        stackSize = s.length();
        flips = 0;
    }
    
    public int evaluateCase() {
        
        if (stack.substring(0, 1).equals("-")){
            flips ++;
            last = "-";
        }
        else{
            last = "+";
        }
        
        for (int i = 1; i < stack.length(); i++){
            if (stack.substring(i,i+1).equals("-") && last.equals("+")){
                flips += 2;
            }
 
            last = stack.substring(i,i+1);
            
        }
        return flips;
    }
 
 }
