package gcj2015qr;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.StringTokenizer;
 
 public class OminousOmino {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        OminousOmino obj = new OminousOmino();
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
 
            int X = Integer.parseInt(token.nextToken());
            int R = Integer.parseInt(token.nextToken());
            int C = Integer.parseInt(token.nextToken());
 
            final String OK = "GABRIEL";
            final String NG = "RICHARD";
 
            String ans = NG;
            if (((R * C) % X) != 0) {
                ans = NG;
            } else {
                if (X <= 2) {
                    ans = OK;
                } else if (X == 3) {
                    if (R == 1 || C == 1) {
                        ans = NG;
                    } else {
                        ans = OK;
                    }
                } else if (X == 4) {
                    if (Math.min(R, C) <= 2) {
                        ans = NG;
                    } else {
                        ans = OK;
                    }
                } else if (X == 5) {
                    if (Math.min(R, C) <= 2) {
                        ans = NG;
                    } else if (Math.min(R, C) == 3) {
                        if (Math.max(R, C) <= 5) {
                            ans = NG;
                        } else {
                            ans = OK;
                        }
                    } else {
                        ans = OK;
                    }
                } else if (X == 6) {
                    if (Math.min(R, C) <= 3) {
                        ans = NG;
                    } else {
                        ans = OK;
                    }
                } else if (X >= 7) {
                    ans = NG;
                }
            }
 
            bw.write("Case #" + (i + 1) + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
