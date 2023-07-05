import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 import static java.util.Arrays.fill;
 import static java.util.Arrays.sort;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.StringTokenizer;
 
 public class A {
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    static final int facts[] = new int[10];
    static {
        facts[0] = 1;
        for (int i = 1; i < facts.length; i++) {
            facts[i] = facts[i - 1] * i;
        }
    }
 
    static final Map<Integer, Integer> ans[] = new Map[9];
    static {
        for (int l = 1; l <= 9; l++) {
            Map<Integer, Integer> curAns = ans[l - 1] = new HashMap<>();
            List<Integer> numsL = new ArrayList<>();
            go1(l, l, 0, numsL);
            Integer nums[] = numsL.toArray(new Integer[numsL.size()]);
            sort(nums, new Comparator<Integer>() {
 
                int sum(int v) {
                    return v == 0 ? 0 : v % 10 + sum(v / 10);
                }
 
                int cnt(int v) {
                    return v == 0 ? 0 : (v % 10 == 0 ? 0 : 1) + cnt(v / 10);
                }
 
                public int compare(Integer o1, Integer o2) {
                    int s1 = sum(o1), s2 = sum(o2);
                    if (s1 != s2) {
                        return s1 - s2;
                    }
                    int c1 = cnt(o1), c2 = cnt(o2);
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    return o1 - o2;
                }
            });
            for (Integer i: nums) {
                curAns.put(i, 1);
            }
            int cnts[] = new int[l];
            for (int i = nums.length - 1; i >= 0; i--) {
                fill(cnts, 0);
                int c = nums[i];
                int incoming = facts[l];
                int r = l;
                while (c != 0) {
                    incoming /= facts[c % 10];
                    r -= c % 10;
                    if (c % 10 != 0) {
                        ++cnts[c % 10 - 1];
                    }
                    c /= 10;
                }
                incoming /= facts[r];
                curAns.put(nums[i], curAns.get(nums[i]) + incoming);
 
 
 
 
 
 
 
 
 
                int next = 0;
                for (int j = l - 1; j >= 0; j--) {
                    next = next * 10 + cnts[j];
                }
                curAns.put(next, curAns.get(next) + curAns.get(nums[i]) - 1);
            }
            int a = 1;
            for (int i = 0; i < l; i++) {
                a *= l + 1;
            }
            curAns.put(1, a - 1);
        }
    }
 
    static void go1(int len, int sum, int cur, List<Integer> res) {
        if (len == 0) {
            if (cur != 0) {
                res.add(cur);
            }
        } else {
            for (int d = 0; d <= sum; d++) {
                go1(len - 1, sum - d, cur * 10 + d, res);
            }
        }
    }
 
    static void solve() throws Exception {
        String g = next();
        int l = g.length();
        int v = 0;
        for (int i = l - 1; i >= 0; i--) {
            v = v * 10 + g.charAt(i) - '0';
        }
        Integer res = ans[l - 1].get(v);
        printCase();
        out.println(res == null ? 1 : (int) res);
    }
 
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
 
    static int nextInt() throws IOException {
        return parseInt(next());
    }
 
    static long nextLong() throws IOException {
        return parseLong(next());
    }
 
    static double nextDouble() throws IOException {
        return parseDouble(next());
    }
 
    static String next() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            int tests = nextInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }