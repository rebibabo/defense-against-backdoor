import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.Math.max;
 import static java.lang.System.arraycopy;
 import static java.lang.System.exit;
 import static java.util.Arrays.copyOf;
 import static java.util.Arrays.fill;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.NoSuchElementException;
 import java.util.StringTokenizer;
 
 public class A {
    
    static class IntList {
 
        int data[] = new int[3];
        int size = 0;
 
        boolean isEmpty() {
            return size == 0;
        }
 
        int size() {
            return size;
        }
 
        int get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return data[index];
        }
 
        void clear() {
            size = 0;
        }
 
        void set(int index, int value) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            data[index] = value;
        }
 
        void expand() {
            if (size >= data.length) {
                data = copyOf(data, (data.length << 1) + 1);
            }
        }
 
        void insert(int index, int value) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            expand();
            arraycopy(data, index, data, index + 1, size++ - index);
            data[index] = value;
        }
 
        int delete(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            int value = data[index];
            arraycopy(data, index + 1, data, index, --size - index);
            return value;
        }
 
        void push(int value) {
            expand();
            data[size++] = value;
        }
 
        int pop() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            return data[--size];
        }
 
        void unshift(int value) {
            expand();
            arraycopy(data, 0, data, 1, size++);
            data[0] = value;
        }
 
        int shift() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            int value = data[0];
            arraycopy(data, 1, data, 0, --size);
            return value;
        }
    }
    
    static final int LIM = 1000000;
 
    static void solve() throws Exception {
        IntList edges[] = new IntList[LIM];
        int n = scanInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                int v = scanInt() - 1;
                if (edges[v] == null) {
                    edges[v] = new IntList();
                }
                edges[v].push(i);
            }
        }
        int matchL[] = new int[LIM];
        int matchR[] = new int[n];
        fill(matchL, -1);
        fill(matchR, -1);
        int queue[] = new int[LIM];
        int back[] = new int[LIM];
        boolean used[] = new boolean[LIM];
        int ans = 0;
        int rangeStart = 0, rangeEnd = 0;
        for (int i = 0; i < LIM; i++) {
            if (edges[i] == null) {
 
                for (int j = rangeStart; j < rangeEnd; j++) {
                    matchR[matchL[j]] = -1;
                    matchL[j] = -1;
                }
                rangeStart = rangeEnd = i + 1;
                continue;
            }
            int queueHead = 0, queueTail = 1;
            queue[0] = i;
            used[i] = true;
            back[i] = -1;
            boolean found = false;
            bfs: do {
                int cur = queue[queueHead++];
                IntList cedges = edges[cur];
                for (int j = 0; j < cedges.size(); j++) {
                    int next = cedges.get(j);
                    if (matchR[next] < 0) {
                        matchR[next] = cur;
                        while (back[cur] >= 0) {
                            int prev = back[cur];
                            int pnext = matchL[cur];
                            matchL[cur] = next;
                            matchR[pnext] = prev;
                            cur = prev;
                            next = pnext;
                        }
                        matchL[cur] = next;
                        found = true;
                        break bfs;
                    } else if (!used[matchR[next]]) {
                        used[matchR[next]] = true;
                        queue[queueTail++] = matchR[next];
                        back[matchR[next]] = cur;
                    }
                }
            } while (queueHead != queueTail);
 
 
            if (!found) {
 
                while (true) {
                    if (rangeStart == rangeEnd) {
                        throw new AssertionError();
                    }
                    matchR[matchL[rangeStart]] = -1;
                    matchL[rangeStart] = -1;
                    if (rangeStart == rangeEnd) {
                        throw new AssertionError();
                    }
                    ++rangeStart;
                    if (used[rangeStart - 1]) {
 
                        break;
                    }
                }
                --i;
            } else {
 
                ++rangeEnd;
                ans = max(ans, rangeEnd - rangeStart);
            }
            for (int j = 0; j < queueTail; j++) {
                used[queue[j]] = false;
            }
        }
        printCase();
        out.println(ans);
    }
 
    static int scanInt() throws IOException {
        return parseInt(scanString());
    }
 
    static long scanLong() throws IOException {
        return parseLong(scanString());
    }
 
    static String scanString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            int tests = scanInt();
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