import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class C {
    
    static Map<String, Map<String, String>> mult = new HashMap<>();
 
    private final static String ONE = "1";
    private final static String i = "i";
    private final static String j = "j";
    private final static String k = "k";
    private final static String nONE = "-1";
    private final static String ni = "-i";
    private final static String nj = "-j";
    private final static String nk = "-k";
    
    public static void preprocess() {
        mult = new HashMap<>();
        Map<String, String> m = new HashMap<>();
        m.put(ONE, ONE);
        m.put(i, i);
        m.put(j, j);
        m.put(k, k);
        m.put(nONE, nONE);
        m.put(ni, ni);
        m.put(nj, nj);
        m.put(nk, nk);
        mult.put(ONE, m);
        m = new HashMap<>();
        m.put(ONE, i);
        m.put(i, nONE);
        m.put(j, k);
        m.put(k, nj);
        m.put(nONE, ni);
        m.put(ni, ONE);
        m.put(nj, nk);
        m.put(nk, j);
        mult.put(i, m);
        m = new HashMap<>();
        m.put(ONE, j);
        m.put(i, nk);
        m.put(j, nONE);
        m.put(k, i);
        m.put(nONE, nj);
        m.put(ni, k);
        m.put(nj, ONE);
        m.put(nk, ni);
        mult.put(j, m);
        m = new HashMap<>();
        m.put(ONE, k);
        m.put(i, j);
        m.put(j, ni);
        m.put(k, nONE);
        m.put(nONE, nk);
        m.put(ni, nj);
        m.put(nj, i);
        m.put(nk, ONE);
        mult.put(k, m);
        
        m = new HashMap<>();
        m.put(ONE, nONE);
        m.put(i, ni);
        m.put(j, nj);
        m.put(k, nk);
        m.put(nONE, ONE);
        m.put(ni, i);
        m.put(nj, j);
        m.put(nk, k);
        mult.put(nONE, m);
        m = new HashMap<>();
        m.put(ONE, ni);
        m.put(i, ONE);
        m.put(j, nk);
        m.put(k, j);
        m.put(nONE, i);
        m.put(ni, nONE);
        m.put(nj, k);
        m.put(nk, nj);
        mult.put(ni, m);
        m = new HashMap<>();
        m.put(ONE, nj);
        m.put(i, k);
        m.put(j, ONE);
        m.put(k, ni);
        m.put(nONE, j);
        m.put(ni, nk);
        m.put(nj, nONE);
        m.put(nk, i);
        mult.put(nj, m);
        m = new HashMap<>();
        m.put(ONE, nk);
        m.put(i, nj);
        m.put(j, i);
        m.put(k, ONE);
        m.put(nONE, k);
        m.put(ni, j);
        m.put(nj, ni);
        m.put(nk, nONE);
        mult.put(nk, m);
    }
    
    public void solve(Scanner scan, PrintWriter out) {
        int length = scan.nextInt();
        int repeat = scan.nextInt();
        String string = scan.next();
        String[] s = new String[length];
        for (int i = 0; i < length; i++) {
            s[i] = string.charAt(i) + "";
            
        }
        String[][] array = new String[length][length+1];
        
        for (int i = 0; i < length; i++) {
            String cur = s[i];
            array[i][i] = ONE;
            array[i][i+1] = s[i];
            
            for (int j = i+2; j <= length; j++) {
                array[i][j] = mult.get(cur).get(s[j-1]);
                
                cur = array[i][j];
            }
            
        }
        
        String val = array[0][length];
        String[] r = new String[4];
        r[0] = ONE;
        
        for (int i = 1; i < 4; i++) {
            r[i] = mult.get(r[i-1]).get(val);
            
        }
        
        boolean success = false;
        loop:
        for (int i1 = 0; i1 < 4; i1++) {
            if (i1 >= repeat) continue;
            for (int i2 = 1; i2 < length; i2++) {
                
                
                if (mult.get(r[i1]).get(array[0][i2]).equals(i)) {
                    for (int j2 = i2+1; j2 <= length; j2++) {
                        if (array[i2][j2].equals(j)) {
                            int rem = repeat - i1 - 1;
                            if (rem < 0) continue;
                            
                            if (j2 == length) {
                                if (rem == 0) continue;
                            }
                            rem %= 4;
                            String result = j2 == length ? r[rem] : mult.get(array[j2%length][length]).get(r[rem]);
                            
                            if (result.equals(k)) {
                                success = true;
                                break loop;
                            }
                        }
                    }
                    for (int j1 = 0; j1 < 4; j1++) {
                        if (j1 >= repeat) continue;
                        for (int j2 = 1; j2 <= length; j2++) {
                            if (j1 == 0 && j2 == i2) continue;
                            boolean jSuccess = false;
                            if (j1 == 0) {
                                
                                
                                
                                if (mult.get(array[i2][length]).get(array[0][j2]).equals(j)) {
                                    jSuccess = true;
                                }
                            } else {
                                
                                String prev = array[i2][length];
                                String cur = r[j1];
                                String next = array[0][j2];
                                
                                
                                
                                if (mult.get(mult.get(prev).get(cur)).get(next).equals(j)) {
                                    jSuccess = true;
                                }
                            }
                            if (jSuccess) {
                                int rem = repeat - i1 - j1 - 2;
                                if (rem < 0) continue;
                                
                                
                                if (j2 == length) {
                                    if (rem == 0) continue;
                                }
                                rem %= 4;
                                String result = j2 == length ? r[rem] : mult.get(array[j2%length][length]).get(r[rem]);
                                
                                if (result.equals(k)) {
                                    success = true;
                                    break loop;
                                }
 
                            }
                        }
                    }
 
                }
            }
        }
        System.out.println(success ? "YES" : "NO");
        out.println(success ? "YES" : "NO");
    }
 
    public static void main(String[] args) throws Exception {
        preprocess();
         Scanner scan = new Scanner(new FileReader("C-small-attempt3.in"));
         PrintWriter out = new PrintWriter("C-small-attempt3.out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new C().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }