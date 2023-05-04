package gcj2015r1a;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.StringTokenizer;
 
 public class Logging {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Logging obj = new Logging();
        obj.solve(fileName);
    }
 
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
 
            long[] X = new long[N];
            long[] Y = new long[N];
            for (int j = 0 ; j < N ; j++ ) {
                String str = br.readLine();
                StringTokenizer token = new StringTokenizer(str, " ");
 
                X[j] = Integer.parseInt(token.nextToken());
                Y[j] = Integer.parseInt(token.nextToken());
            }
 
            bw.write("Case #" + (i + 1) + ": ");
            bw.write("\r\n");
 
            for (int j = 0 ; j < N ; j++) {
                if ( N <= 3 ) {
                    bw.write("" + 0);
                    bw.write("\r\n");
                    continue;
                }
                int ans = N - 3;
 
                for (int k = 0 ; k < (1 << N) ; k++) {
                    int cnt = 0;
                    for (int a = 0 ; a < N ; a++) {
                        cnt += (((k >> a) & 1) == 0) ? 0 : 1;
                    }
                    if ( cnt >= ans ) {
                        continue;
                    }
 
                    for (int a = 0 ; a < N ; a++) {
                        if (a == j || ((1 << a) & k) != 0 ) {
                            continue;
                        }
                        long base = 0;
                        boolean flg = true;
                        for (int b = 0 ; b < N ; b++) {
                            if (b == j || b == a || ((1 << b) & k) != 0 ) {
                                continue;
                            }
                            long d = (X[j] - X[a]) * (Y[b] - Y[j]) - (Y[j] - Y[a]) * (X[b] - X[j]);
                            if (base == 0) {
                                base = d;
                            } else if ( (base > 0 && d > 0) || (base < 0 && d < 0 ) || d == 0) {
                                continue;
                            } else {
                                flg = false;
                                break;
                            }
                        }
                        if (flg) {
                            ans = cnt;
                            break;
                        }
                    }
                    if ( ans == 0 ) {
                        break;
                    }
                }
 
                bw.write("" + ans);
                bw.write("\r\n");
            }
        }
        bw.close();
        br.close();
    }
 }
