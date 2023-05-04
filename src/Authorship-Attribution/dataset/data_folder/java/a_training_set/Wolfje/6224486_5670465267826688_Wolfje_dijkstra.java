import static java.lang.Math.min;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class Dijkstra {
    String PROBLEM_ID = "problemC";
 
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
        new Dijkstra();
    }
 
    public Dijkstra() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                getOutFileName())));
        Scanner scan = new Scanner(in);
        int target = mult(mult(2, 3), 4);
        int tests = scan.nextInt();
        for (int test = 0; test < tests; test++) {
            int L = scan.nextInt();
            int X = scan.nextInt();
            long maxIndex = (long)L*X;
            String s = scan.next();
            int[] a = new int[L];
            for ( int i = 0; i < L; i++ ) a[i] = "01ijk".indexOf(s.charAt(i));
            int aProd = 1;
            for ( int x: a ) aProd = mult(aProd, x);
            int check = pow(aProd, X);
            int index = 0;
            index = find(index, 2, a);
            if ( index >= 0 ) index = find(index + 1, 3, a);
            if ( index >= 0 ) index = find(index + 1, 4, a);
 
 
            boolean ok = index >= 0 && index < maxIndex && target == check;
            String result = ok ? "YES" : "NO";
            
            String resultStr = String.format("Case #%d: %s", test + 1, result);
            
 
            System.out.println(resultStr);
            out.println(resultStr);
        }
        out.close();
        System.out.println("*** in file =  " + getInFileName());
        System.out.println("*** out file = " + getOutFileName());
    }
    
    int find(int index, int goal, int[] x) {
        int r = 1;
        int n = x.length;
        for ( int i = 0; i < 13*n; i++) {
            r = mult(r, x[(index+i) % n]);
            if ( r == goal ) return index + i;
        }
        return -1;
    }
    
    int[][] table = new int[][] { {0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4},
            {0, 2, -1, 4, -3},
            {0, 3, -4, -1, 2},
            {0, 4, 3, -2, -1}};
    
    int mult(int a, int b) {
        int c = table[Math.abs(a)][Math.abs(b)];
        if ( a < 0 ) c *= -1;
        if ( b < 0 ) c *= -1;
        return c;
    }
    
    int pow(int a, int n) {
        int r = 1;
        while ( n > 0 ) {
            if ( n % 2 == 0) {
                a = mult(a, a); 
                n /= 2;
            } else {
                r = mult(r, a);
                n--;
            }
        }
        return r;       
    }
        
    
 }
