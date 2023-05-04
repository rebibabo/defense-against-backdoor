package exo2;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) throws Exception {
        
        Config config = createSmallConfig(0);
        
        try {
        
            int T = 0;
            String[] in = null;
            int currentTestCase = 0;
            
            while (config.input().hasNextLine()) {
                String line = config.input().nextLine();
                
                if(T == 0) {
                    T = Integer.parseInt(line);
                    in = new String[T];
                }
                else {
                    in[currentTestCase] = line;
                    ++currentTestCase;
                }
            }
    
            for(int i = 0; i<T; ++i) {
                config.output().println("Case #" + (i+1) + ": " + solveTestCase(in[i]));
            }
            
        }
        finally {
            config.done();
        }
    }
    
    
    
    
    
    
    
    
    
    private static int solveTestCase(String in) {
        int L = in.length();
        if(L == 0) {
            return 0;
        }
        
        int res = in.charAt(L-1) == '-' ? 1 : 0;
        for(int i=1; i<L; ++i) {
            if(in.charAt(i) != in.charAt(i-1)) {
                ++res;
            }
        }
        return res;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 }
