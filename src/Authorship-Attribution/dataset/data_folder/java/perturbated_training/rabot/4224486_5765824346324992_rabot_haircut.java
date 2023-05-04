package gcj2015r1a;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.StringTokenizer;
 
 public class Haircut {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Haircut obj = new Haircut();
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
            int B = Integer.parseInt(token.nextToken());
            long N = Long.parseLong(token.nextToken());
            str = br.readLine();
            token = new StringTokenizer(str, " ");
 
            long[] M = new long[B];
            for (int j = 0; j < B; j++) {
                M[j] = Integer.parseInt(token.nextToken());
            }
 
            long ans = 0;
            if ( N <= B ) {
                ans = N;
            } else {
                long min = 0;
                long max = Long.MAX_VALUE / 10;
                ArrayList<Integer> empty = new ArrayList();
                while (max - min > 1) {
                    long cur = (min + max) / 2;
                    long done = 0;
                    empty.clear();
 
                    for (int j = 0 ; j < B ; j++ ) {
                        done += ((cur + M[j] - 1) / M[j]);
                        if ((cur % M[j]) == 0) {
                            empty.add(j);
                        }
                    }
 
                    if (done >= N) {
                        max = cur;
                    } else if (done + empty.size() < N) {
                        min = cur;
                    } else {
                        ans = empty.get((int)(N - done - 1)) + 1;
                        break;
                    }
                }
            }
 
            bw.write("Case #" + (i + 1) + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
