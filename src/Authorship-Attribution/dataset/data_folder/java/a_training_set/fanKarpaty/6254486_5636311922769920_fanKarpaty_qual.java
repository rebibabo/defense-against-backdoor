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
    
    public static long pow(long n, long c) {
        if (c == 0) {
            return 1;
        }
        long ret = 1L;
        for (int i = 0; i < c; i++)ret *= n;
        return ret;
    }
    
    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new FileReader(new File("input.txt")));
        System.setOut(new PrintStream(new File("output.txt")));
        int T = readInt();
        for (int ind = 1; ind<=T; ind++) {
            int[] ar = readIntArr();
            long k = ar[0], c = ar[1], s = ar[2];
            System.out.print("Case #" + ind + ":");
            if (c == 1L && s < k || c > 1L && s * 2 < k) {
                System.out.println(" IMPOSSIBLE");
            } else if (c == 1L) {
                for (long i = 1; i <= k; i++)System.out.print(" " + i);
                System.out.println();
            } else {
                long shift = pow(k, c - 1);
                for (long i = 1; i <= k; i+=2) {
                    System.out.print(" " + (shift * (i - 1) + i + (i == k ? 0 : 1)));
                }
                System.out.println();
            }
        }
        
 
    }
 
 }
