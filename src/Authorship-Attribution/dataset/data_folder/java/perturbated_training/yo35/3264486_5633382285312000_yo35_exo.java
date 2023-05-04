package exo2;
 
 import java.util.ArrayList;
 import java.util.List;
 
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
 
    
    
    
 
    private static long solveTestCase() {
 
        long N = input().nextLong();
 
        
 
        long[] digits = getDigits(N);
 
        int i = 0;
        while (i + 1 < digits.length) {
            if (digits[i] > digits[i + 1]) {
                break;
            }
            ++i;
        }
 
        
        if (i == digits.length - 1) {
            return N;
        }
 
        while (i > 0 && digits[i - 1] == digits[i]) {
            --i;
        }
 
        --digits[i];
        for (int k = i + 1; k < digits.length; ++k) {
            digits[k] = 9;
        }
 
        return getValue(digits);
    }
 
    private static long getValue(long[] digits) {
        long result = 0;
        for (long digit : digits) {
            result = result * 10 + digit;
        }
        return result;
    }
 
    private static long[] getDigits(long N) {
        List<Long> digits = new ArrayList<Long>();
        while (N > 0) {
            digits.add(N % 10);
            N /= 10;
        }
 
        long[] result = new long[digits.size()];
        int i = result.length - 1;
        for (long digit : digits) {
            result[i--] = digit;
        }
        return result;
    }
 
    
    
 }
