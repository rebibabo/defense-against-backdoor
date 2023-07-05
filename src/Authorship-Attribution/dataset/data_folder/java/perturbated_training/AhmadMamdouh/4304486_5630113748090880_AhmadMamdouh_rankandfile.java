import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.StringTokenizer;
 
 public class RankAndFile {
    static class P implements Comparable<P> {
        int[] a;
 
        public P(int[] aa) {
            a = aa;
        }
 
        @Override
        public int compareTo(P arg0) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] < arg0.a[i])
                    return -1;
                if (a[i] > arg0.a[i])
                    return 1;
            }
            return 0;
        }
 
    }
 
    static int[][] arr;
    static int[] sol;
 
    static void go() {
        for (int i = 0; i < (1 << arr.length); i++) {
            ArrayList<P> list = new ArrayList<P>();
            for (int j = 0; j < arr.length; j++) {
                if ((i & (1 << j)) == 0) {
                    list.add(new P(arr[j]));
                }
            }
            if (list.size() == arr[0].length) {
                Collections.sort(list);
                if (checkRow(list)) {
                    return;
                } else if (checkCol(list)) {
                    return;
                }
            }
        }
    }
 
    private static boolean checkCol(ArrayList<P> list) {
        int[][] temp = new int[arr[0].length][arr[0].length];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).a.length; j++) {
                temp[j][i] = list.get(i).a[j];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (i + 1 < list.size() || arr[i][j] < arr[i + 1][j]) {
 
                } else {
                    return false;
                }
                if (j + 1 == list.size() || arr[i][j] < arr[i][j + 1]) {
 
                } else {
                    return false;
                }
 
            }
        }
        extract(temp);
        return false;
    }
 
    private static void extract(int[][] temp) {
        boolean[] vis = new boolean[arr.length];
        for (int i = 0; i < temp.length; i++) {
            outer: for (int j = 0; j < temp.length; j++) {
                boolean rowFound = false;
                for (int k = 0; k < arr.length; k++) {
                    if (!vis[k]) {
                        boolean can = true;
                        for (int x = 0; x < temp.length; x++) {
                            if (arr[k][x] == arr[i][j]) {
                                continue;
                            } else {
                                can = false;
                            }
                        }
                        if (can) {
                            vis[k] = true;
                            rowFound = true;
                            continue outer;
                        }
                    }
                }
                if (!rowFound) {
                    for (int k = 0; k < temp.length; k++) {
                        sol[k] = temp[i][k];
                    }
                    return;
                }
            }
        }
        for (int i = 0; i < temp.length; i++) {
            outer: for (int j = 0; j < temp.length; j++) {
                boolean rowFound = false;
                for (int k = 0; k < arr.length; k++) {
                    if (!vis[k]) {
                        boolean can = true;
                        for (int x = 0; x < temp.length; x++) {
                            if (arr[k][x] == arr[j][i]) {
                                continue;
                            } else {
                                can = false;
                            }
                        }
                        if (can) {
                            vis[k] = true;
                            rowFound = true;
                            continue outer;
                        }
                    }
                }
                if (!rowFound) {
                    for (int k = 0; k < temp.length; k++) {
                        sol[k] = temp[k][i];
                    }
                    return;
                }
            }
        }
    }
 
    private static boolean checkRow(ArrayList<P> list) {
        int[][] temp = new int[arr[0].length][arr[0].length];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).a.length; j++) {
                temp[i][j] = list.get(i).a[j];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (i + 1 < list.size() || arr[i][j] < arr[i + 1][j]) {
 
                } else {
                    return false;
                }
                if (j + 1 == list.size() || arr[i][j] < arr[i][j + 1]) {
 
                } else {
                    return false;
                }
 
            }
        }
        extract(temp);
        return true;
    }
 
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            int n = r.nextInt();
            int len = 2 * n - 1;
            arr = new int[len][n];
            int[] cnt = new int[3000];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = r.nextInt();
                    cnt[arr[i][j]]++;
                }
            }
            sol = new int[n];
            int k =0;
            for(int i=0;i<cnt.length;i++){
                if(cnt[i]%2==1)
                    sol[k++] = i;
            }
            System.out.printf("Case #%d:", test);
            for (int i = 0; i < n; i++) {
                System.out.print(" " + sol[i]);
            }
            System.out.println();
            test++;
        }
    }
    static int[][] build;
    static void solveElegant(int[][] curr) {
        int min = 1 << 28;
        for (int i = 0; i < curr.length; i++) {
            min = Math.min(min, curr[i][0]);
        }
        int[] index = new int[2];
        int k = 0;
        for (int i = 0; i < curr.length; i++) {
            if (curr[i][0] == min) {
                index[k++] = i;
            }
        }
        if (k != 2) {
            System.out.println("WA - K");
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
