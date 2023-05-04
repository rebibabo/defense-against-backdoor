package contest.codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class B {
 
    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;
 
    static int T, N, C, M;
 
    public static void main (String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new FileReader("B-small-attempt2.in"));
        out = new PrintWriter(new FileWriter("out.txt"));
 
        T = readInt();
 
        for (int t = 1; t <= T; t++) {
            N = readInt();
            C = readInt();
            M = readInt();
 
            assert(C == 2);
 
            int[][] seats = new int[2][N];
 
            for (int i = 0; i < M; i++) {
                int seat = readInt() - 1;
                int buyer = readInt() - 1;
                seats[buyer][seat]++;
            }
 
            PriorityQueue<State> pq = new PriorityQueue<State>();
            int totalConflicts = 0;
            int net = 0;
            for (int i = 0; i < N; i++) {
                net += seats[0][i] - seats[1][i];
                int currConflicts = Math.min(seats[0][i], seats[1][i]);
                totalConflicts += currConflicts;
                if (currConflicts != 0)
                    pq.offer(new State(i, currConflicts));
            }
            net = Math.abs(net);
            int nonConflictPairs = M / 2 - totalConflicts - net / 2;
            while (pq.size() > 2) {
                State a = pq.poll();
                State b = pq.poll();
                nonConflictPairs += a.conflicts * 2;
                if (b.conflicts - a.conflicts != 0)
                    pq.offer(new State(b.index, b.conflicts - a.conflicts));
            }
            
            
            if (pq.size() == 0) {
                out.printf("Case #%d: %d %d\n", t, nonConflictPairs + net, 0);
            } else if (pq.size() == 1) {
                State b = pq.poll();
                if (b.conflicts <= nonConflictPairs) {
                    nonConflictPairs += b.conflicts;
                    out.printf("Case #%d: %d %d\n", t, nonConflictPairs + net, 0);
                } else {
                    int temp = b.conflicts;
                    b.conflicts -= nonConflictPairs;
                    nonConflictPairs += temp;
                    if (b.index == 0) {
                        out.printf("Case #%d: %d %d\n", t, nonConflictPairs + b.conflicts + net, 0);
                    } else {
                        out.printf("Case #%d: %d %d\n", t, nonConflictPairs + net, b.conflicts);
                    }
                }
            } else {
                assert(pq.size() == 2);
                State a = pq.poll();
                State b = pq.poll();
                b.conflicts -= a.conflicts;
                if (b.conflicts <= nonConflictPairs) {
                    nonConflictPairs += b.conflicts;
                    
                    nonConflictPairs += a.conflicts;
                    out.printf("Case #%d: %d %d\n", t, nonConflictPairs + net, 0);
                } else {
                    int temp = b.conflicts;
                    b.conflicts -= nonConflictPairs;
                    nonConflictPairs += temp;
                    
 
                    nonConflictPairs += a.conflicts;
                    if (b.index == 0) {
                        out.printf("Case #%d: %d %d\n", t, nonConflictPairs + b.conflicts + net, 0);
                    } else {
                        out.printf("Case #%d: %d %d\n", t, nonConflictPairs + net, b.conflicts);
                    }
                }
            }
        }
 
        out.close();
    }
 
    static class State implements Comparable<State> {
        int index, conflicts;
        State (int index, int conflicts) {
            this.index = index;
            this.conflicts = conflicts;
        }
        @Override
        public int compareTo (State s) {
            return conflicts - s.conflicts;
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
 
