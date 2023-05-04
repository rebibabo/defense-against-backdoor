import java.io.BufferedInputStream;
 import java.util.HashMap;
 import java.util.Scanner;
 
 
 public class TaskC {
    private static byte[][] product = {
        {0, 0, 0, 0, 0},
        {0, 1, 2, 3, 4},
        {0, 2, -1, 4, -3},
        {0, 3, -4, -1, 2},
        {0, 4, 3, -2, -1}
    };
    
    private static HashMap<String, Byte> cache = new HashMap<String, Byte>(30000);
 
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int l = sc.nextInt();
            int x = sc.nextInt();
            sc.nextLine();
            String line = sc.nextLine();
            if (l == 1 || x % 4 == 0) {
                print(i+1, false);
                continue;
            }
            if (x > 12) {
                x = 12 + (x % 4);
            }
            cache.put(line, calculate(line, l));
            byte iVal = toInd('i');
            byte jVal = toInd('j');
            byte kVal = toInd('k');
            
            StringBuilder sb = new StringBuilder(l * x);
            for (int j = 0; j < x; j++) {
                sb.append(line);
            }
            boolean done = false;
            for (int j = 1; j < l * x - 1; j++) {
                if (iVal == calculate(sb.substring(0, j), l)) {
                    for (int k = j + 1; k < l * x; k++) {
                        if (jVal == calculate(sb.substring(j, k), l)) {
                            if (kVal == calculate(sb.substring(k), l)) {
                                print(i+1, true);
                            }
                            else {
                                print(i+1, false);
                            }
                            done = true;
                            break;
                        }
                    }
                    break;
                }
            }
            if (!done) {
                print(i+1, false);
            }
        }
        sc.close();
        System.err.println(System.currentTimeMillis() - time);
    }
    
    private static byte calculate(String line, int len) {
        if (line.length() == 1) {
            return toInd(line.charAt(0));
        }
        if (line.length() == 2) {
            return calculate(line.charAt(0), line.charAt(1));
        }
        if (line.length() > len) {
            String tail = line.substring(line.length() - len);
            if (cache.containsKey(tail)) {
                return calculate(calculate(line.substring(0, line.length() - len), len), cache.get(tail));
            }
            String head = line.substring(0, len);
            if (cache.containsKey(head)) {
                return calculate(cache.get(head), calculate(line.substring(len), len));
            }
        }
        if (cache.containsKey(line)) {
            return cache.get(line);
        }
        else {
            byte result = calculate(calculate(line.substring(0, line.length() / 2), len),
                    calculate(line.substring(line.length() / 2), len));
            if (line.length() <= 10) {
                cache.put(line, result);
            }
            return result;
        }
    }
    
    private static byte calculate(byte a, byte b) {
        boolean minus = (a < 0) ^ (b < 0);
        return (byte) (minus ? -product[Math.abs(a)][Math.abs(b)] : product[Math.abs(a)][Math.abs(b)]);
    }
    
    private static byte calculate(char a, char b) {
        return calculate(toInd(a), toInd(b));
    }
    
    private static byte toInd(char c) {
        switch (c) {
        case 'i':
            return 2;
        case 'j':
            return 3;
        case 'k':
            return 4;
        default:
            return -1;
        }
    }
    
    private static void print(int caseNum, boolean answer) {
        if (answer) {
            System.out.println("Case #" + caseNum + ": YES");
        }
        else {
            System.out.println("Case #" + caseNum + ": NO");
        }
    }
 
 }
