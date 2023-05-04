import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
    
    int amount = 0;
    int n = 16;
    int j = 50;
    int max = 100;
    PrintWriter out;
    
    public Main() throws FileNotFoundException {
        out = new PrintWriter(new File("C_small.out"));
        
        System.out.println("Case #1:");
        out.println("Case #1:");
        
        calculate("1");
        
        out.close();
    }
    
    public void calculate(String s) {
        if (j == amount) return;
        if (s.length() == (n-1)) {
            String t = s+'1';
            String sol = t;
            boolean poss = true;
            for (int i = 2; i < 11; i++) {
                BigInteger val = new BigInteger(t,i);
                boolean found = false;
                for (int k = 2; k < max; k++) {
                    if (val.mod(new BigInteger(""+k)).equals(BigInteger.ZERO)) {
                        sol += (" " + k);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    poss = false;
                    break;
                }
            }
            if (poss) {
                System.out.println(sol);
                out.println(sol);
                amount++;
            }
        } else {
            calculate(s+'0');
            calculate(s+'1');
        }
    }
 }
