import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Map.Entry;
 
 public class B {
 
    Map<Character, Double> p;
    
    public void solve(Scanner scan, PrintWriter out) {
        int k = scan.nextInt();
        int l = scan.nextInt();
        int s = scan.nextInt();
        String keyboard = scan.next();
        String target = scan.next();
        p = new HashMap<>();
        populate();
        for (Character c : keyboard.toCharArray()) {
            p.put(c, p.get(c) + 1.0/k);
        }
        int maxBananas = maxBananas(s, target);
        for (Character c : target.toCharArray()) {
            if (p.get(c) == 0.0) {
                System.out.println(0.0);
                out.println(0.0);
                return;
            }
        }
        double[][][] array = new double[s+1][l+1][maxBananas+1];
        array[s][l][0] = 1.0;
        for (int i = s-1; i >= 0; i--) {
            for (int j = l; j >= 0; j--) {
                for (k = 0; k <= maxBananas; k++) {
                    if (j == 0 && k == 0) continue;
                    if (j != l && j == overlapIndex(target) && k > 0) {
                        array[i][j][k] += array[i+1][1][k-1] * p.get(target.charAt(overlapIndex(target)-1));
                        array[i][j][k] += array[i+1][j+1][k] * p.get(target.charAt(j));
                    } else if (j == 0 && k > 0) {
                        array[i][j][k] += array[i+1][1][k-1] * p.get(target.charAt(0));
                    } else if (j == l) {
                        for (int m = 1; m <= l; m++) {
                            array[i][j][k] += array[i+1][m][k] * (1-p.get(target.charAt(m-1)));
                        }
                        array[i][j][k] += array[i+1][0][k] * (1-p.get(target.charAt(overlapIndex(target)-1)));
                    } else {
                        array[i][j][k] += array[i+1][j+1][k] * p.get(target.charAt(j));
                    }
                
                }
                
            }
        }
        double result = 0;
        for (int i = 0; i <= l; i++) {
            for (int j = 0; j <= maxBananas; j++) {
                result += (maxBananas - j) * array[0][i][j];
            }
        }
        System.out.println(result);
        out.println(result);
    }
    
    private void populate() {
        Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (Character c : alphabet) {
            p.put(c, 0.0);
        }
    }
 
    public int maxBananas(int length, String s) {
        int lastIndex = length - s.length();
        int overlap = overlapIndex(s);
        return (lastIndex/overlap)+1;
        
    }
    
    public int overlapIndex(String s) {
        char[] array = s.toCharArray();
        int result = array.length;
        for (int i = 1; i < s.length(); i++) {
            boolean success = true;
            for (int j = 0; j < s.length()-i; j++) {
                if (array[j] != array[i+j]) {
                    success = false;
                    break;
                }
            }
            if (success) {
                return i;
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        String filename = "B-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new B().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }