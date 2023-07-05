package codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class A {
 
    static BufferedReader br;
    static PrintWriter pr;
    static StringTokenizer st;
 
    public static void main(String[] args) throws IOException {
        
        
        br = new BufferedReader(new FileReader("in.txt"));
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
                    pr.printf("Case #%d: %d\n", q, i.dist);
                    break;
                }
                if (!v.contains(i.index+1)) {
                    v.add(i.index+1);
                    qq.offer(new State(i.index+1, i.dist+1));
                }
                long reverse = reverse(i.index);
                if (!v.contains(reverse)) {
                    v.add(reverse);
                    qq.offer(new State(reverse, i.dist+1));
                }
            }
        }
        
        pr.close();
    }
    private static long reverse(long index) {
        String res = "";
        while (index != 0) {
            res += index % 10;
            index /= 10;
        }
        return Long.parseLong(res);
    }
    static class State {
        long index; int dist;
        State (long index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
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
        return br.readLine().trim();
    }
 }
 
