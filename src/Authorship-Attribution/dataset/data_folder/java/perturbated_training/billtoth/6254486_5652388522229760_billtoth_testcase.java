
 public class TestCase {
 
    int n;
    int digitCounter;
    int iterations;
    int seenDigitSum;
    Boolean insomnia;
    Boolean allDigits;
    int digitsInNumber;
    int digit;
    int tally;
    int tallyTest;
    
    public TestCase (int start) {
        n = start;
        digitCounter = 0;
        seenDigitSum = 0;
        allDigits = false;
        insomnia = false;
        tally = 0;
    }
    
    public int evaluateCase() {
        
        
        
        if(n == 0){
            return 0;
        }
        else{
            for(iterations = 1; digitCounter < 10; iterations++){
                checkDigits(n*iterations);
            }
            
            return (iterations-1)*n;
        }
    }
 
    private void checkDigits(int numToCheck) {
        digitsInNumber = getNumberOfDigits(numToCheck);
        for (int i = 0; i < digitsInNumber; i++){
            digit = getDigit(i, numToCheck);
            if (seenDigit(digit) == false){
                digitCounter++;
                tally += (int) Math.pow(2, digit); 
                
                
                
            }
        }
    }
 
    private int getNumberOfDigits(int numToCheck) {
        double d = numToCheck * 1.0;
        return (int) (Math.log10(d) - (Math.log10(d) % 1)) + 1;
    }
 
    private int getDigit(int j, int numToCheck) {
        return ((numToCheck / (int) Math.pow(10, j)) % 10);
        
        
        
    }
    
    private Boolean seenDigit (int dig){
        tallyTest = tally;
        
        
        
        
        
        
        
 
        for (int k = 9; k >= dig; k--){
            if (tallyTest >= (int) Math.pow(2, k)){
                if (k == dig){
                    return true;
                }
                else{
                    tallyTest -= (int) Math.pow(2, k);
                }
            }
        }
        
        return false;
            
    }
 }
