package gcj2015r2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Bilingual {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Bilingual obj = new Bilingual();
        obj.solve(fileName);
    }
 
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            StringTokenizer token = new StringTokenizer(str, " ");
            int N = Integer.parseInt(token.nextToken());
 
            int[][] sen = new int[N][];
            ArrayList<String> dict = new ArrayList<String>();
            for (int j = 0; j < N; j++) {
                str = br.readLine();
                token = new StringTokenizer(str, " ");
                int[] tmp = new int[2000];
                int idx = 0;
                while (token.hasMoreTokens()) {
                    String word = token.nextToken();
                    boolean flg = false;
                    for (int k = 0 ; k < dict.size() ; k++) {
                        if (word.equals(dict.get(k))) {
                            tmp[idx++] = k;
                            flg = true;
                            break;
                        }
                    }
                    if (flg == false) {
                        tmp[idx++] = dict.size();
                        dict.add(word);
                    }
                }
                sen[j] = Arrays.copyOf(tmp, idx);
            }
 
            int ans = dict.size();
            for (int j = 0 ; j < (1 << N-2) ; j++) {
                int[] check = new int[dict.size()];
                for (int w : sen[0]) {
                    check[w] |= 1;
                }
                for (int w : sen[1]) {
                    check[w] |= 2;
                }
 
                for (int k = 0 ; k < N-2 ; k++) {
                    if ((j & (1 << k)) != 0) {
                        for (int w : sen[k + 2]) {
                            check[w] |= 1;
                        }
                    } else {
                        for (int w : sen[k + 2]) {
                            check[w] |= 2;
                        }
                    }
                }
                int cnt = 0;
                for (int c : check) {
                    if ( c == 3 ) {
                        cnt++;
                    }
                }
                ans = Math.min(ans, cnt);
            }
 
 
            bw.write("Case #" + (i + 1) + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
