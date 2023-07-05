package facebook;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Map;
 import java.util.Set;
 
 public class Qual {
 
    private static BufferedReader br = null;
    private static int readInt() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return 0;
    }
    
    private static double readDouble() {
        try {
            return Double.parseDouble(br.readLine());
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return 0;
    }
    
    private static int[] readIntArr() {
        int[] ret = null;
        String[] tmp;
        try {
            String str = br.readLine();
            tmp = str.split(" ");
            ret = new int[tmp.length];
            for (int i = 0; i < tmp.length; i++)
                ret[i] = Integer.parseInt(tmp[i]);
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return ret;
    }
    
    public static final int solve(int n) {
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            int k = n + i * n;
            while(k > 0) {
                digits.add(k % 10);
                k/=10;
            }
            
            if (digits.size() >= 10) {
                return n + i * n;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new FileReader(new File("input.txt")));
        System.setOut(new PrintStream(new File("output.txt")));
        int T = readInt();
        for (int ind = 1; ind<=T; ind++) {
            String s = br.readLine();
            int count = 0;
            while(true) {
                char c = s.charAt(0);
                int n = s.length();
                int i = 1;
                while(i < n) {
                    if (s.charAt(i) == c)i++;
                    else break;
                }
                if (i == n && c == '+')break;
                
                String tail = s.substring(i);
                for (int j = 0; j < i; j++) {
                    if (s.charAt(j) == '+')tail = "-" + tail;
                    else tail = "+" + tail;
                }
                
                s = tail;
                count++;
                
            }
            System.out.println("Case #" + ind + ": " + count);
        }
        
 
    }
 
 }
