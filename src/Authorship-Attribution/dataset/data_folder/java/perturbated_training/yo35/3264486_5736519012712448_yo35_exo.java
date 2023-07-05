package exo1;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) {
 
        
        
        
 
        
        configSmall(0, false);
        
 
        
        
 
        try {
            int testCaseCount = input().nextInt();
            for (int i = 1; i <= testCaseCount; ++i) {
 
                
                
                
 
                output().println("Case #" + i + ": " + solveTestCase());
 
                
                
 
            }
        }
        finally {
            done();
        }
    }
 
    
    
    
 
    private static String solveTestCase() {
 
        boolean[] state = parseState(input().next());
 
        int S = state.length;
 
        int K = input().nextInt();
 
        int result = 0;
 
        int k = 0;
        while (k <= S - K) {
 
            if (state[k]) {
                ++k;
            }
            else {
                for (int l = k; l < k + K; ++l) {
                    state[l] = !state[l];
                }
                ++result;
            }
        }
 
        for (int i = S - K + 1; i < S; ++i) {
            if (!state[i]) {
                return "IMPOSSIBLE";
            }
        }
 
        return Integer.toString(result);
    }
 
    private static boolean[] parseState(String val) {
        boolean[] result = new boolean[val.length()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = val.charAt(i) == '+';
        }
        return result;
    }
 
    
    
 }
