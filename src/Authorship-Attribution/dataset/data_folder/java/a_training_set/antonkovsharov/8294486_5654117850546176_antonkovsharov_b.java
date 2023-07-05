import java.util.*;
 import java.io.*;
 
 public class B {
    FastScanner in;
    PrintWriter out;
 
    class Letter implements Comparable<Letter> {
        String c;
        int cnt;
 
        public Letter(String c, int cnt) {
            super();
            this.c = c;
            this.cnt = cnt;
        }
 
        @Override
        public int compareTo(Letter o) {
            return Integer.compare(o.cnt, cnt);
        }
    }
 
    String[] val;
    int[] next;
 
    void print(int head) {
        for (int e = head; e != 0; e = next[e]) {
            out.print(val[e]);
        }
        out.println();
    }
 
    public void solve() throws IOException {
        int n = in.nextInt();
        String[] cs = new String[] { "R", "O", "Y", "G", "B", "V" };
        Letter[] lets = new Letter[cs.length];
        for (int i = 0; i < lets.length; i++) {
            lets[i] = new Letter(cs[i], in.nextInt());
        }
        Arrays.sort(lets);
        Letter[] a = new Letter[3];
        int ap = 0;
        for (int i = 0; i < lets.length; i++) {
            if (lets[i].cnt != 0) {
                a[ap++] = lets[i];
            }
        }
        while (ap < 3) {
            a[ap++] = new Letter("Z", 0);
        }
        if (a[0].cnt > a[1].cnt + a[2].cnt) {
            out.println("IMPOSSIBLE");
            return;
        }
 
        int head = 0;
        final int MAX_SIZE = 2000;
        next = new int[MAX_SIZE];
        val = new String[MAX_SIZE];
 
        head = 0;
        int cntE = 0;
        for (int i = 0; i < a[0].cnt; i++) {
            next[++cntE] = head;
            head = cntE;
            val[cntE] = a[0].c;
        }
        int cur = head;
        for (int type = 1; type < 3; type++) {
            for (int i = 0; i < a[type].cnt; i++) {
                int nextNext = next[cur];
                next[cur] = ++cntE;
                next[cntE] = nextNext;
                val[cntE] = a[type].c;
                cur = nextNext;
                if (cur == 0) {
                    cur = head;
                }
            }
        }
        print(head);
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tn = in.nextInt();
            for (int i = 0; i < tn; i++) {
                System.err.println(i);
                out.print("Case #" + (i + 1) + ": ");
                solve();
            }
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 
    public static void main(String[] arg) {
        new B().run();
    }
 }