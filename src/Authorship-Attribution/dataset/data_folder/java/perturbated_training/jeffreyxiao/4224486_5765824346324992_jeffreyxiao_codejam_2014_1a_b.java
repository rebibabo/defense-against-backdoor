import java.util.*;
 import java.io.*;
 
 public class Codejam_2014_1A_B {
 
    static BufferedReader br;
    static PrintWriter ps;
    static StringTokenizer st;
    
    
    public static void main (String[] args) throws IOException {
        
        ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        br = new BufferedReader(new FileReader("test.txt"));
        
    
        int t = readInt();
        for (int q = 1; q <= t; q++) {
            int b = readInt();
            int n = readInt()-1;
            int[] a = new int[b];
            for (int i = 0; i < b; i++)
                a[i] = readInt();
            int lcm = 1;
            int sum = 0;
            for (int i = 0; i < b; i++)
                lcm = lcm(lcm, a[i]);
            for (int i = 0; i < b; i++) {
                sum += lcm/a[i];
            }
            n %= sum;
            PriorityQueue<State> pq = new PriorityQueue<State>();
            for (int i = 0; i < b; i++)
                pq.add(new State(i, 0));
            for (int i = 0; i < n; i++) {
                State s = pq.poll();
                s.time += a[s.id];
                pq.offer(s);
            }
            System.out.printf("Case #%d: %d\n", q, pq.poll().id+1);
        }
    
        ps.close();
    }
    static int lcm (int x, int y) {
        return x*y/gcf(x, y);
    }
    static int gcf (int x, int y) {
        return y == 0 ? x : gcf(y, x%y);
    }
    static class State implements Comparable<State> {
        int id;
        int time;
        State (int id, int time) {
            this.id = id;
            this.time = time;
        }
        public int compareTo (State s) {
            if (time == s.time)
                return id - s.id;
            return time - s.time;
        }
    }
    static String next () throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
 
    static long readLong () throws IOException {
        return Long.parseLong(next());
    }
 
    static int readInt () throws IOException {
        return Integer.parseInt(next());
    }
 
    static double readDouble () throws IOException {
        return Double.parseDouble(next());
    }
 
    static char readCharacter () throws IOException {
        return next().charAt(0);
    }
 
    static String readLine () throws IOException {
        return br.readLine().trim();
    }
 }