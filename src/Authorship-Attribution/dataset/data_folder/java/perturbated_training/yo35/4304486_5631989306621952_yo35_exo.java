package exo1;
 
 import javax.xml.ws.soap.AddressingFeature;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) {
        
        
        
        
        
        
        configSmall(0, false);
        
        
        
        
        
        try {
            int testCaseCount = input().nextInt();
            for(int i=1; i<=testCaseCount; ++i) {
                
                
                
                
                
                output().println("Case #" + i + ": " + solveTestCase());
                
                
                
                
            }
        }
        finally {
            done();
        }
    }
    
    
    
    
    
    private static String solveTestCase() {
        
        String word = input().next();
        
        String res = word.substring(0, 1);
        for(int i=1; i<word.length(); ++i) {
            
            String addBefore = word.substring(i, i+1) + res;
            String addAfter = res + word.substring(i, i+1);
            
            res = addBefore.compareTo(addAfter) < 0 ? addAfter : addBefore;
        }
        return res;
    }
    
    
    
 }
