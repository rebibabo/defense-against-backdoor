import static java.lang.Math.max;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 import static java.lang.Math.*;
 
 public class Pancakes {
    String PROBLEM_ID = "problemB";
 
    enum TestType {
        EXAMPLE, SMALL, LARGE
    }
 
 
     TestType TYPE = TestType.SMALL;
 
 
    public String getFileName() {
        String result = PROBLEM_ID + "_";
        switch (TYPE) {
        case EXAMPLE:
            result += "example";
            break;
        case SMALL:
            result += "small";
            break;
        case LARGE:
            result += "large";
            break;
        }
        return result;
    }
 
    public String getInFileName() {
        return getFileName() + ".in";
    }
 
    public String getOutFileName() {
        return getFileName() + ".out";
    }
 
    public static void main(String[] args) throws Exception {
        new Pancakes();
    }
 
    public Pancakes() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                getOutFileName())));
        Scanner scan = new Scanner(in);
        for ( int[] a: memo) Arrays.fill(a, -1);
        int tests = scan.nextInt();
        for (int test = 0; test < tests; test++) {
            int[] count = new int[1001];
            int d = scan.nextInt();
            int[] diners = new int[d];
            for ( int i = 0; i < d; i++) diners[i] = scan.nextInt();
            Arrays.sort(diners);
            
            int result1 = method1(diners);
            int result2 = method2(diners);
            int result = min(result1, result2);
            String resultStr = String.format("Case #%d: %d", test + 1, result);
            
 
            System.out.println(resultStr);
            out.println(resultStr);
        }
        out.close();
        System.out.println("*** in file =  " + getInFileName());
        System.out.println("*** out file = " + getOutFileName());
    }
    
    int[][] memo = new int[1001][1001];
    
    int go(int k, int cutoff) {
        if ( k <= cutoff) return 0;
        if ( memo[k][cutoff] >= 0 ) return memo[k][cutoff];
        int result = min(go(k - cutoff, cutoff) + 1,
                         1 + go(k/2, cutoff) + go(k - k/2, cutoff));
        return memo[k][cutoff] = result;
    }
    
    int method2(int[] diners) {
        int result = Integer.MAX_VALUE;
        for ( int cutoff = 1; cutoff <= diners[diners.length-1]; cutoff++) {
 
            int cost = 0;
            for ( int d: diners) {
 
                cost += go(d, cutoff);
            }
 
            result = min(result, cutoff + cost);
        }
        return result;      
    }
    
    int method1(int[] diners) {
        int result = Integer.MAX_VALUE;
        int special = 0;
        int[] count = new int[1001];
        for ( int d: diners) count[d]++;
        for ( int i = 1000; i > 0; i--) {
            if ( count[i] == 0 ) continue;
            result = min(result, special + i);
            special += count[i];
            int z = i/2;
            count[z] += count[i];
            count[i-z] += count[i];
        }
        return result;
    }
    
    
 }
