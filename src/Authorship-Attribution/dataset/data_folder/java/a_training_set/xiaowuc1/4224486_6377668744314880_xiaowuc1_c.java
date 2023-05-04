import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class C {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("c.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d:\n", casenum);
            int n = readInt();
            State[] list = new State[n];
            for(int i = 0; i < n; i++) {
                list[i] = new State(readInt(), readInt());
            }
            int[] max = new int[n];
            for(int mask = 0; mask < 1 << n; mask++) {
                TreeSet<State> set = new TreeSet<State>();
                for(int i = 0; i < n; i++) {
                    if((mask&(1<<i)) != 0) {
                        set.add(list[i]);
                    }
                }
                ArrayList<State> hu = convexHull(set);
                for(int i = 0; i < n; i++) {
                    if(hu.contains(list[i])) {
                        max[i] = Math.max(max[i], Integer.bitCount(mask));
                    }
                }
            }
            for(int out: max) {
                pw.println(n-out);
            }
        }
        pw.close();
    }
 
    public static ArrayList<State> convexHull(TreeSet<State> set) {
        ArrayList<State> up = new ArrayList<State>();
        ArrayList<State> dn = new ArrayList<State>();
        for(State curr: set)    {
            while (up.size() > 1 && area(up.get(up.size()-2), up.get(up.size()-1), curr) > 0) 
                up.remove(up.size()-1);
            while (dn.size() > 1 && area(dn.get(dn.size()-2), dn.get(dn.size()-1), curr) < 0) 
                dn.remove(dn.size()-1);
            up.add(curr);
            dn.add(curr);
        }
        ArrayList<State> hu = new ArrayList<State>();
        for(State s: dn)    {
            hu.add(s);
        }
        for(int i = up.size() - 2; i >= 1; i--) {
            hu.add(up.get(i));
        }
        return hu;
    }
    
    public static double cross(State p, State q)   {
        return p.x*q.y-p.y*q.x;
    }
    public static double area(State a, State b, State c)    {
        return cross(a,b) + cross(b,c) + cross(c,a);
    }
 
    static class State implements Comparable<State> {
        public double x,y;
        public State(double a, double b)    {
            x=a;
            y=b;
        }
        public String toString()        {
            return x + " " + y;
        }
        public int compareTo(State s)   {
            if(y < s.y)
                return -1;
            else if(y > s.y)
                return 1;
            else if(x < s.x)
                return -1;
            else if(x > s.x)
                return 1;
            else
                return 0;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            long temp;
            temp = Double.doubleToLongBits(x);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(y);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            State other = (State) obj;
            if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
                return false;
            if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
                return false;
            return true;
        }
        
    }
    public static int readInt() {
        return Integer.parseInt(nextToken());
    }
 
    public static long readLong() {
        return Long.parseLong(nextToken());
    }
 
    public static double readDouble() {
        return Double.parseDouble(nextToken());
    }
 
    public static String nextToken() {
        while(st == null || !st.hasMoreTokens())    {
            try {
                if(!br.ready()) {
                    pw.close();
                    System.exit(0);
                }
                st = new StringTokenizer(br.readLine());
            }
            catch(IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        return st.nextToken();
    }
 
    public static String readLine() {
        st = null;
        try {
            return br.readLine();
        }
        catch(IOException e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }
    }
 
 }
