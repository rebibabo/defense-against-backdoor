
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Scanner;
 
 public class ProblemC {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();
        sc.close();
        int fileNamePos = fileName.indexOf('.');
        String outputFileName = null;
        if (fileNamePos >= 0) {
            outputFileName = fileName.substring(0, fileNamePos) + ".out.txt";
        } else {
            outputFileName = fileName + ".out.txt";
        }
 
        String path = ProblemC.class.getResource("").getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
        sc = new Scanner(new File(path + fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + outputFileName));
 
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            long N = sc.nextLong();
            long K = sc.nextLong();
 
            Range[] cur = new Range[2];
            Range[] next = new Range[2];
            cur[0] = new Range();
            cur[1] = new Range();
            cur[0].dist = N;
            cur[0].cnt = 1;
 
            long rest = K;
            long last_d = 1;
            LOOP: while (rest >= 0) {
                next[0] = new Range();
                next[1] = new Range();
                for (int j = 0; j <= 1; j++) {
                    if (cur[j].cnt == 0) {
                        continue;
                    }
                    if (cur[j].cnt < rest) {
                        rest = rest - cur[j].cnt;
                        long d = cur[j].dist - 1;
                        if (d % 2 == 0) {
                            if (j == 0) {
                                next[0].dist = d / 2;
                                next[0].cnt += cur[j].cnt * 2;
                            } else {
                                next[1].dist = d / 2;
                                next[1].cnt += cur[j].cnt * 2;
                            }
                        } else {
                            next[0].dist = (d - 1) / 2 + 1;
                            next[0].cnt += cur[j].cnt;
                            next[1].dist = (d - 1) / 2;
                            next[1].cnt += cur[j].cnt;
                        }
                    } else {
                        rest = 0;
                        last_d = cur[j].dist;
                        break LOOP;
                    }
                }
                cur[0] = next[0];
                cur[1] = next[1];
            }
            bw.write("Case #" + i + ": " + last_d / 2 + " " + (last_d - 1) / 2);
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 
 }
 
 class Range {
    long dist = 0;
    long cnt = 0;
 
    void reset() {
        dist = 0;
        cnt = 0;
    }
 
    void copy(Range r) {
        this.dist = r.dist;
        this.cnt = r.cnt;
    }
 }
