package exo3;
 
 import java.util.HashSet;
 import java.util.Set;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) throws Exception {
        
        Config config = createSmallConfig(1);
        
        try {
            
            int T = 0;
            int N = 0;
            int J = 0;
        
            while (config.input().hasNextLine()) {
                String line = config.input().nextLine();
                
                if(T == 0) {
                    T = Integer.parseInt(line);
                    if(T != 1) {
                        throw new IllegalArgumentException("T != 1");
                    }
                }
                else {
                    String[] s = line.split(" ");
                    N = Integer.parseInt(s[0]);
                    J = Integer.parseInt(s[1]);
                }
            }
            
            long[] primes = findPrimes(10);
            debug("Primes: " + implode(primes));
            
            config.output().println("Case #1:");
            
            int found = 0;
            Set<String> alreadyVisited = new HashSet<>();
            while(found < J) {
                
                boolean[] candidate = generateCandidate(N);
                String candidateAsString = implode(candidate);
                if(!alreadyVisited.add(candidateAsString)) {
                    debug("Duplicate found: " + candidateAsString);
                    continue;
                }
                
                long[] divisors = checkCandidate(candidate, primes);
                if(divisors != null) {
                    ++found;
                    
                    debug("Found " + found + "/" + J);
                    config.output().println(candidateAsString + " " + implode(divisors));
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                }
            }
        }
        finally {
            config.done();
        }
    }
    
    private static long[] findPrimes(int nb) {
        long[] result = new long[nb];
        long value = 2;
        int nbPrimeFound = 0;
        while(nbPrimeFound < nb) {
            if(isPrime(value, result, nbPrimeFound)) {
                result[nbPrimeFound] = value;
                ++nbPrimeFound;
            }
            ++value;
        }
        return result;
    }
    
    private static boolean isPrime(long value, long[] primes, int nbPrimeFound) {
        for(int i=0; i<nbPrimeFound; ++i) {
            if(value % primes[i] == 0) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean[] generateCandidate(int N) {
        boolean[] result = new boolean[N];
        result[0] = true;
        result[N-1] = true;
        for(int i=1; i<=N-2; ++i) {
            result[i] = Math.random() >= 0.5;
        }
        return result;
    }
    
    private static long[] checkCandidate(boolean[] candidate, long[] primes) {
        long[] result = new long[9];
        for(int radix = 2; radix <= 10; ++radix) {
            long divisor = checkCandidate(candidate, radix, primes);
            if(divisor < 0) {
                return null;
            }
            result[radix - 2] = divisor;
        }
        return result;
    }
    
    private static long checkCandidate(boolean[] candidate, long radix, long[] primes) {
        for(int i=0; i<primes.length; ++i) {
            if(isDivisor(candidate, primes[i], radix)) {
                return primes[i];
            }
        }
        return -1;
    }
    
    private static long expandCandidate(boolean[] candidate, long radix) {
        long res = 0l;
        for(int i=0; i<candidate.length; ++i) {
            res = res * radix + (candidate[i] ? 1l : 0l); 
        }
        return res;
    }
    
    private static boolean isDivisor(boolean[] candidate, long divisor, long radix) {
        long modulus = 0l;
        for(int i=0; i<candidate.length; ++i) {
            modulus = (modulus * radix + (candidate[i] ? 1l : 0l)) % divisor; 
        }
        return modulus == 0;
    }
    
    private static String implode(boolean[] tab) {
        StringBuilder sb = new StringBuilder();
        for(boolean b : tab) {
            sb.append(b ? '1' : '0');
        }
        return sb.toString();
    }
    
    private static String implode(long[] tab) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<tab.length; ++i) {
            if(i > 0) {
                sb.append(" ");
            }
            sb.append(tab[i]);
        }
        return sb.toString();
    }
 }
