package facebook;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
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
    
    public static boolean check(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            if (ch == '?')continue;
            if (ch != b.charAt(i))return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new FileReader(new File("input.txt")));
        System.setOut(new PrintStream(new File("output.txt")));
        int T = readInt();
        
        for (int ind = 1; ind<=T; ind++) {
            System.out.print("Case #" + ind + ": ");
            int N = readInt();
            String a[] = new String[N];
            String b[] = new String[N];
            for (int i =0 ; i < N; i++) {
                String s = br.readLine();
                String t[] = s.split(" ");
                a[i] = t[0];
                b[i] = t[1];
            }
            
            int result = 0;
            for (int m = 0; m < (1 << N); m++) {
                int t = 0;
                int M = m;
                while (M > 0) {
                    t++;
                    M &= M-1;
                }
                if (t <= result)continue;
                
                Set<String> sf = new HashSet<String>();
                Set<String> ss = new HashSet<String>();
                
                for (int i = 0; i < N; i++) {
                    if ((m & (1 << i)) == 0) {
                        sf.add(a[i]);
                        ss.add(b[i]);
                    }
                }
                
                int count = 0;
                for (int i = 0; i < N; i++) {
                    if ((m &(1 << i)) != 0) {
                        if (sf.contains(a[i]) && ss.contains(b[i])) {
                            count ++;
                        }
                    }
                }
                
                if (count > result) result = count;
            }
            System.out.println(result);
            
        }
        
 
    }
 
 }
