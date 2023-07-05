import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 import static java.lang.Math.*;
 
 
 public class ProblemC {
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
        new ProblemC();
    }
 
    public ProblemC() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                getOutFileName())));
        Scanner scan = new Scanner(in);
        int tests = scan.nextInt();
        for (int test = 0; test < tests; test++) {
            int n = scan.nextInt();
            ArrayList<Hiker> hikers = new ArrayList<Hiker>();
            for ( int i = 0; i < n; i++) {
                int d = scan.nextInt();
                int h = scan.nextInt();
                int m = scan.nextInt();
                for ( int j = 0; j < h; j++) hikers.add(new Hiker(d, m+j));
            }
            Collections.sort(hikers);
            int answer = hikers.size();
            if ( hikers.size() > 1) {
                Hiker a = hikers.get(0);
                Hiker b = hikers.get(1);
                if ( a.time < b.time ) {
                    
                    if ( (360-b.pos)*b.time < (720-a.pos)*a.time ) answer = 0;
                    else answer = 1;
                } else {
                    
                    if ( (360-a.pos)*a.time < (720-b.pos)*b.time ) answer = 0;
                    else answer = 1;
                }
                
            }
            String resultStr = String.format("Case #%d: %d", test + 1, answer);
            
 
            System.out.println(resultStr);
            out.println(resultStr);
        }
        out.close();
        System.out.println("*** in file =  " + getInFileName());
        System.out.println("*** out file = " + getOutFileName());
    }
    
    public class Hiker implements Comparable<Hiker>{
        long pos;
        long time;
 
        public Hiker(int pos, int time ) {
            this.pos = pos;
            this.time = time;
        }
 
        @Override
        public int compareTo(Hiker other) {
            if ( pos != other.pos ) return (int)(pos - other.pos);
            return (int)(other.time - time);
        }
 
    }
 
 }
