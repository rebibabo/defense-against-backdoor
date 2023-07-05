import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.StringTokenizer;
 
 public class Bilingual {
    public static void main(String[] args) throws IOException {
        
        InputReader r = new InputReader(new FileReader("C-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new FileWriter("C_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int n = r.nextInt();
            String str = r.nextLine();
            String[] sp = str.split("[ ]+");
            HashSet<String> english = new HashSet<String>();
            for (String s : sp)
                english.add(s);
            str = r.nextLine();
            sp = str.split("[ ]+");
            HashSet<String> french = new HashSet<String>();
            for (String s : sp)
                french.add(s);
            int common = 0;
            for (String s : english)
                if (french.contains(s))
                    common++;
            String[][] arr = new String[n - 2][];
            for (int i = 2; i < n; i++) {
                str = r.nextLine();
                arr[i - 2] = str.split("[ ]+");
            }
            int res = 1 << 28;
            for (int i = 0; i < (1 << (arr.length)); i++) {
                HashSet<String> e2 = new HashSet<String>();
                HashSet<String> f2 = new HashSet<String>();
                for (int j = 0; j < arr.length; j++) {
                    if ((i & (1 << j)) > 0) {
                        for (String s : arr[j])
                            e2.add(s);
                    } else {
                        for (String s : arr[j])
                            f2.add(s);
                    }
                }
                HashSet<String> taken = new HashSet<String>();
                for (String s : e2) {
                    if (f2.contains(s)) {
                        if (english.contains(s) && french.contains(s))
                            continue;
                        taken.add(s);
                    } else if (french.contains(s)) {
                        if (english.contains(s))
                            continue;
                        taken.add(s);
                    }
                }
                for (String s : f2) {
                    if (e2.contains(s)) {
                        if (english.contains(s) && french.contains(s))
                            continue;
                        taken.add(s);
                    } else if (english.contains(s)) {
                        if (french.contains(s))
                            continue;
                        taken.add(s);
                    }
                }
                res = Math.min(res, taken.size() + common);
            }
            System.out.println(test + " " + res);
            out.printf("Case #%d: %d\n", test++, res);
        }
        out.close();
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
