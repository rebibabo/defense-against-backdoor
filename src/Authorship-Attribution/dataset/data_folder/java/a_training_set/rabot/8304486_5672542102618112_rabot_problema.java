
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProblemA {
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
 
        String path = ProblemA.class.getResource("").getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
        sc = new Scanner(new File(path + fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + outputFileName));
 
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            String G = sc.next();
            int L = G.length();
 
            int iG = Integer.parseInt(G);
 
            int[] chk = new int[100000];
            Arrays.fill(chk, 0);
            int max = (int) Math.pow(10, L);
            for (int j = 0; j < max; j++) {
                rec(chk, j, iG, L);
            }
            int cnt = 0;
            for (int j = 0; j < max; j++) {
                cnt += (chk[j]) > 0 ? 1 : 0;
            }
 
            bw.write("Case #" + i + ": " + cnt);
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 
    private static int rec(int[] chk, int cur, int iG, int L) {
        if (chk[cur] != 0) {
            return chk[cur];
        }
        if (cur == iG) {
            chk[cur] = 1;
            return chk[cur];
        }
        int next = 0;
        chk[cur] = -1;
        int b = cur;
        for (int i = 0; i < L; i++) {
            int a = b % 10;
            if (a > L) {
                return -1;
            }
            if (a != 0) {
                next += Math.pow(10, L - a);
            }
            b = b / 10;
        }
        chk[cur] = rec(chk, next, iG, L);
        return chk[cur];
    }
 }
