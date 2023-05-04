
 import java.io.*;
 import java.util.*;
 
 public class A {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
 
    String compute(char[][] b, char[] alt) {
        boolean found = false;
        for (int r = 0 ; r < b.length ; r++) {
            for (int c = 0 ; c < b[r].length ; c++) {
                if (b[r][c] == '?') {
                    found = true;
 alt:               for (char a : alt) {
                        int minr = r;
                        int maxr = r;
                        int minc = c;
                        int maxc = c;
                        for (int rr = 0; rr < b.length ; rr++) {
                            for (int cc = 0 ; cc < b[rr].length ; cc++) {
                                if (b[rr][cc] == a) {
                                    minr = Math.min(minr, rr);
                                    maxr = Math.max(maxr, rr);
                                    minc = Math.min(minc, cc);
                                    maxc = Math.max(maxc, cc);
                                }
                            }
                        }
                        for (int rr = minr; rr <= maxr ; rr++) {
                            for (int cc = minc ; cc <= maxc ; cc++) {
                                if (b[rr][cc] != a && b[rr][cc] != '?') continue alt;
                            }
                        }
                        char[][] next = b.clone();
                        for (int rr = 0 ; rr < next.length ; rr++) next[rr] = next[rr].clone();
                        for (int rr = minr; rr <= maxr ; rr++) {
                            for (int cc = minc ; cc <= maxc ; cc++) {
                                next[rr][cc] = a;
                            }
                        }
                        String ret = compute(next, alt);
                        if (ret != null) return ret;
                    }
                }
            }
        }
        if (!found) {
            StringBuilder sb = new StringBuilder();
            for (char[] row : b) sb.append("\n").append(new String(row));
            return sb.toString();
        } else {
            return null;
        }
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int[] v = getInts(in);
                int R = v[0];
                int C = v[1];
                char[][] b = new char[R][];
                for (int r = 0 ; r < R ; r++) b[r] = in.readLine().toCharArray();
                Set<Character> unique = new HashSet<>();
                for (char[] row : b) for (char c : row) if (c != '?') unique.add(c);
                char[] uniques = new char[unique.size()];
                int i = 0;
                for (char c : unique) uniques[i++] = c;
                out.printf("Case #%d: %s\n", t, compute(b, uniques));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new A().run(args);
    }
 }
