import java.util.*;
 import java.io.*;
 
 public class B {
    FastScanner in;
    PrintWriter out;
 
    class Barber implements Comparable<Barber> {
        int num;
        long time;
        
        public Barber(int num, long time) {
            super();
            this.num = num;
            this.time = time;
        }
 
        @Override
        public int compareTo(Barber arg0) {
            return Integer.compare(num, arg0.num);
        }
    }
    
    long getCount(long[] a, long x) {
        long count = 0;
        for (int i = 0; i < a.length; i++) {
            count += (x + a[i] - 1) / a[i];
        }
        return count;
    }
    
    public void solve() throws IOException {
        int b = in.nextInt(), n = in.nextInt() - 1;
        long[] a = new long[b];
        for (int i = 0; i < b; i++) {
            a[i] = in.nextLong();
        }
        long l = 0, r = a[0] * n + 10;
        while (r - l > 1) {
            long x = (l + r) / 2;
            long count = getCount(a, x);
            if (count <= n)
                l = x;
            else
                r = x;
        }
        ArrayList<Barber> bs = new ArrayList<>();
        for (int i = 0; i < b; i++) {
            long count = (l + a[i] - 1) / a[i];
            if (count * a[i] == l)
                bs.add(new Barber(i, l));
        }
        long before = getCount(a, l);
        
        Collections.sort(bs);
        int idx = (int) (n - before);
        out.println(bs.get(idx).num + 1);
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tests = in.nextInt();
            for (int i = 0; i < tests; i++) {
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