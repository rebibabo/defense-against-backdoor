package exo4;
 
 import java.util.ArrayList;
 import java.util.List;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) throws Exception {
        
        Config config = createSmallConfig(0);
        
        try {
        
            int T = 0;
            TestCase[] testCases = null;
            int currentTestCase = 0;
            
            while (config.input().hasNextLine()) {
                String line = config.input().nextLine();
                
                if(T == 0) {
                    T = Integer.parseInt(line);
                    testCases = new TestCase[T];
                }
                else {
                    String[] s = line.split(" ");
                    long K = Long.parseLong(s[0]);
                    long C = Long.parseLong(s[1]);
                    long S = Long.parseLong(s[2]);
                    if(S != K) {
                        throw new IllegalArgumentException("S != K");
                    }
                    testCases[currentTestCase] = new TestCase(K, C);
                    ++currentTestCase;
                }
            }
    
            for(int i = 0; i<T; ++i) {
                List<Long> res = solveTestCase(testCases[i]);
                config.output().println("Case #" + (i+1) + ": " + (res == null ? "IMPOSSIBLE" : implode(res)));
            }
            
        }
        finally {
            config.done();
        }
    }
    
    private static class TestCase {
        final long K;
        final long C;
        
        public TestCase(long k, long c) {
            K = k;
            C = c;
        }
    }
    
    private static String implode(List<Long> list) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(long n : list) {
            if(isFirst) {
                isFirst = false;
            }
            else {
                sb.append(" ");
            }
            sb.append(n);
        }
        return sb.toString();
    }
    
    private static List<Long> solveTestCase(TestCase tc) {
        List<Long> res = new ArrayList<>((int) tc.K);
        for(long digit=0; digit<tc.K; ++digit) {
            res.add(makeTestIndex(digit, tc.K, tc.C));
        }
        return res;
    }
    
    private static long makeTestIndex(long digit, long K, long C) {
        long res = 0;
        for(long i=0; i<C; ++i) {
            res = res * K + digit;
        }
        return 1 + res;
    }
 }
