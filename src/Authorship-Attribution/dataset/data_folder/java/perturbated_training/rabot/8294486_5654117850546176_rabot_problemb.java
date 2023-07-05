
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Scanner;
 
 public class ProblemB {
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
 
        String path = ProblemB.class.getResource("").getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
        sc = new Scanner(new File(path + fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + outputFileName));
 
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            int R = sc.nextInt();
            int O = sc.nextInt();
            int Y = sc.nextInt();
            int G = sc.nextInt();
            int B = sc.nextInt();
            int V = sc.nextInt();
 
            String ans = "";
            if (O > 0 && O >= B) {
                if (O == B && R == 0 && Y == 0 && G == 0 && V == 0) {
                    for (int o = 0; o < O; o++) {
                        ans += "OB";
                    }
                } else {
                    ans = "IMPOSSIBLE";
                }
            } else if (G > 0 && G >= R) {
                if (G == R && O == 0 && Y == 0 && B == 0 && V == 0) {
                    for (int g = 0; g < G; g++) {
                        ans += "GR";
                    }
                } else {
                    ans = "IMPOSSIBLE";
                }
            } else if (V > 0 && V >= Y) {
                if (V == Y && R == 0 && O == 0 && G == 0 && B == 0) {
                    for (int v = 0; v < V; v++) {
                        ans += "VY";
                    }
                } else {
                    ans = "IMPOSSIBLE";
                }
            } else {
                int B1 = B - O;
                int R1 = R - G;
                int Y1 = Y - V;
                if (B1 + R1 >= Y1 && R1 + Y1 >= B1 && Y1 + B1 >= R1) {
                    String[] BB = new String[B1];
                    String[] RR = new String[R1];
                    String[] YY = new String[Y1];
                    for (int b = 0; b < B1; b++) {
                        BB[b] = "B";
                        if (b == 0 && O > 0) {
                            for (int o = 0; o < O; o++) {
                                BB[b] += "OB";
                            }
                        }
                    }
                    for (int r = 0; r < R1; r++) {
                        RR[r] = "R";
                        if (r == 0 && G > 0) {
                            for (int g = 0; g < G; g++) {
                                RR[r] += "GR";
                            }
                        }
                    }
                    for (int y = 0; y < Y1; y++) {
                        YY[y] = "Y";
                        if (y == 0 && V > 0) {
                            for (int v = 0; v < V; v++) {
                                YY[y] += "VY";
                            }
                        }
                    }
                    String[][] XX = new String[3][];
                    if (B1 >= R1 && B1 >= Y1) {
                        XX[0] = BB;
                        if (R1 >= Y1) {
                            XX[1] = RR;
                            XX[2] = YY;
                        } else {
                            XX[1] = YY;
                            XX[2] = RR;
                        }
                    } else if (R1 >= Y1) {
                        XX[0] = RR;
                        if (B1 >= Y1) {
                            XX[1] = BB;
                            XX[2] = YY;
                        } else {
                            XX[1] = YY;
                            XX[2] = BB;
                        }
                    } else {
                        XX[0] = YY;
                        if (B1 >= R1) {
                            XX[1] = BB;
                            XX[2] = RR;
                        } else {
                            XX[1] = RR;
                            XX[2] = BB;
                        }
                    }
                    int x0 = XX[0].length;
                    int x1 = XX[1].length;
                    int x2 = XX[2].length;
                    for (int x = 0; x < XX[0].length; x++) {
                        ans += XX[0][x];
                        if (x1 + x2 > x0) {
                            ans += XX[1][XX[1].length - x1];
                            ans += XX[2][XX[2].length - x2];
                            x1--;
                            x2--;
                        } else if (x1 > 0) {
                            ans += XX[1][XX[1].length - x1];
                            x1--;
                        } else {
                            ans += XX[2][XX[2].length - x2];
                            x2--;
                        }
                        x0--;
                    }
                } else {
                    ans = "IMPOSSIBLE";
                }
            }
 
            bw.write("Case #" + i + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 }
