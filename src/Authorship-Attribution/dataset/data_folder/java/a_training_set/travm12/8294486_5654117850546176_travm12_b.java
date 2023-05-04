import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class b {
    public static void main(String[] Args) throws Exception {
        
        FS sc = new FS(new File("B-small-attempt2.in"));
        
        
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("b.out"))));
        int cc = 0;
 
        int t = sc.nextInt();
        while (t-- > 0) {
            out.printf("Case #%d: ", ++cc);
 
            int n = sc.nextInt();
            int r = sc.nextInt();
            int o = sc.nextInt();
            int y = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            int v = sc.nextInt();
            int nr = r - g;
            int ny = y - v;
            int nb = b - o;
            boolean possible = true;
            if ((g > 1 && nr <= 0 && n != g + r) || (v > 1 && ny <= 0 && n != v + y) || (o > 1 && nb <= 0 && n != o + b)
                    || nr < 0 || ny < 0 || nb < 0)
                possible = false;
            if ((nr + nb + ny) % 2 == 1 && (ny == 0 || nb == 0 || nr == 0))
                possible = false;
            if (ny > nb + nr || nb > ny + nr || nr > nb + ny)
                possible = false;
            if (possible) {
                if (ny > nr && ny > nb) {
                    out.print("Y");
                    for (int i = 0; i < v; i++)
                        out.print("VY");
                    ny--;
                    boolean yLast = true;
                    boolean rLast = false;
                    boolean bLast = false;
                    boolean pO = false;
                    boolean pG = false;
                    while (ny != 0 || nr != 0 || nb != 0) {
                        
 
                        if (ny < 0 || nr < 0 || nb < 0)
                            System.out.println(cc + " " + "r " + nr + " " + ny + " " + nb);
                        if (yLast || ny == 0) {
                            if (nb > nr || rLast) {
                                out.print("B");
                                nb--;
                                if (!pO)
                                    for (int i = 0; i < o; i++)
                                        out.print("OB");
                                pO = true;
                                bLast = true;
                                yLast = false;
                                rLast = false;
                            } else {
                                out.print("R");
                                nr--;
                                if (!pG)
                                    for (int i = 0; i < g; i++)
                                        out.print("GR");
                                pG = true;
                                rLast = true;
                                yLast = false;
                                bLast = false;
                            }
                        } else {
                            yLast = true;
                            rLast = false;
                            bLast = false;
                            ny--;
                            out.print("Y");
                        }
                    }
                    if (yLast)
                        System.out.println(cc);
                } else if (nr > nb) {
                    out.print("R");
                    for (int i = 0; i < g; i++)
                        out.print("GR");
                    nr--;
                    boolean yLast = false;
                    boolean rLast = true;
                    boolean bLast = false;
                    boolean pO = false;
                    boolean pV = false;
                    while (ny > 0 || nr > 0 || nb > 0) {
 
                        if (ny < 0 || nr < 0 || nb < 0)
                            System.out.println(cc + " " + "r " + nr + " " + ny + " " + nb);
                        if (rLast || nr == 0) {
                            if (nb > ny || yLast) {
                                out.print("B");
                                nb--;
                                if (!pO)
                                    for (int i = 0; i < o; i++)
                                        out.print("OB");
                                pO = true;
                                bLast = true;
                                yLast = false;
                                rLast = false;
                            } else {
                                out.print("Y");
                                ny--;
                                if (!pV)
                                    for (int i = 0; i < v; i++)
                                        out.print("VY");
                                pV = true;
                                yLast = true;
                                rLast = false;
                                bLast = false;
                            }
                        } else {
                            rLast = true;
                            yLast = false;
                            bLast = false;
                            nr--;
                            out.print("R");
                        }
                    }
                    if (rLast)
                        System.out.println(cc);
                } else if (nb > 0) {
                    out.print("B");
                    for (int i = 0; i < o; i++)
                        out.print("OB");
                    nb--;
                    boolean bLast = true;
                    boolean rLast = false;
                    boolean yLast = false;
                    boolean pV = false;
                    boolean pG = false;
                    while (ny != 0 || nr != 0 || nb != 0) {
                        
 
                        if (ny < 0 || nr < 0 || nb < 0)
                            System.out.println(cc + " " + "r " + nr + " " + ny + " " + nb);
                        if (bLast || nb == 0) {
                            if (ny > nr || rLast) {
                                out.print("Y");
                                ny--;
                                if (!pV)
                                    for (int i = 0; i < v; i++)
                                        out.print("VY");
                                pV = true;
                                yLast = true;
                                bLast = false;
                                rLast = false;
                            } else {
                                out.print("R");
                                nr--;
                                if (!pG)
                                    for (int i = 0; i < g; i++)
                                        out.print("GR");
                                pG = true;
                                rLast = true;
                                yLast = false;
                                bLast = false;
                            }
                        } else {
                            bLast = true;
                            rLast = false;
                            yLast = false;
                            nb--;
                            out.print("B");
                        }
                    }
                    if (bLast)
                        System.out.println(cc);
                } else{
                    for (int i = 0; i < o; i++)
                        out.print("OB");
                    for (int i = 0; i < v; i++)
                        out.print("YV");
                    for (int i = 0; i < g; i++)
                        out.print("RG");
 
                }
                out.println();
            } else {
                out.println("IMPOSSIBLE");
            }
 
        }
        out.close();
    }
 
    public static class FS {
        BufferedReader br;
        StringTokenizer st;
 
        FS(InputStream in) throws Exception {
            br = new BufferedReader(new InputStreamReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        FS(File in) throws Exception {
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        String next() throws Exception {
            if (st.hasMoreTokens())
                return st.nextToken();
            st = new StringTokenizer(br.readLine());
            return next();
        }
 
        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
    }
 }
