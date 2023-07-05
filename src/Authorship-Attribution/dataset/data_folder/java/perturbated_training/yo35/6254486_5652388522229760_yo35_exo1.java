package codejam;
 
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class Exo1 {
 
    public static void main(String[] argv) throws Exception {
        
        int T = 0;
        long[] N = null;
        int currentTestCase = 0;
        
        Scanner sc = new Scanner(Exo0.class.getClassLoader().getResourceAsStream("data/exo1/in2.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            
            if(T == 0) {
                T = Integer.parseInt(line);
                N = new long[T];
            }
            else {
                N[currentTestCase] = Long.parseLong(line);
                ++currentTestCase;
            }
        }
        sc.close();
 
        for(int i = 0; i<T; ++i) {
            String res = solveTestCase(N[i]);
            System.out.println("Case #" + (i+1) + ": " + res);
        }
    }
    
    private static String solveTestCase(long N) {
        
        if(N == 0) {
            return "INSOMNIA";
        }
        
        Set<Long> alreadySeen = new HashSet<>();
        long factor = 0;
        while(alreadySeen.size() < 10) {
            ++factor;
            updateDigits(factor * N, alreadySeen);
        }
        
        long res = factor * N;
        return Long.toString(res);
    }
    
    private static void updateDigits(long value, Set<Long> alreadySeen) {
        while(value > 0) {
            long lastDigit = value % 10l;
            alreadySeen.add(lastDigit);
            value /= 10l;
        }
    }
 }
