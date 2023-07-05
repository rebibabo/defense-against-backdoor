import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class AlphabetCake {
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            int n = r.nextInt();
            int m = r.nextInt();
            char[][] arr = new char[n][m];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = r.next().toCharArray();
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] != '?') {
                        for (int k = j - 1; k >= 0; k--) {
                            if (arr[i][k] == '?') {
                                arr[i][k] = arr[i][j];
                            } else {
                                break;
                            }
                        }
                        for (int k = j + 1; k < m; k++) {
                            if (arr[i][k] == '?') {
                                arr[i][k] = arr[i][j];
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        
            for (int i = 0; i < arr.length; i++) {
                boolean containsChar = false;
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] != '?') {
                        containsChar = true;
                    }
                }
                if (containsChar) {
                    for (int k = i - 1; k >= 0; k--) {
                        containsChar = false;
                        for (int j = 0; j < arr[i].length; j++) {
                            if (arr[k][j] != '?') {
                                containsChar = true;
                            }
                        }
                        if (containsChar) {
                            break;
                        } else {
                            for (int j = 0; j < arr[i].length; j++) {
                                arr[k][j] = arr[i][j];
                            }
                        }
                    }
                    for (int k = i + 1; k < n; k++) {
                        containsChar = false;
                        for (int j = 0; j < arr[i].length; j++) {
                            if (arr[k][j] != '?') {
                                containsChar = true;
                            }
                        }
                        if (containsChar) {
                            break;
                        } else {
                            for (int j = 0; j < arr[i].length; j++) {
                                arr[k][j] = arr[i][j];
                            }
                        }
                    }
                }
            }
            System.out.printf("Case #%d:\n", test);
            for (int i = 0; i < arr.length; i++) {
                System.out.println(new String(arr[i]));
            }
            test++;
        }
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
 
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 }
