import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class C {
    int n, k;
    double u;
    double[] p;
 
    void run() {
        try (Scanner sc = new Scanner(System.in)) {
            StringBuilder ans = new StringBuilder();
            int tcNum = Integer.parseInt(sc.next());
            for (int tc = 1; tc <= tcNum; tc++) {
                n = sc.nextInt();
                k = sc.nextInt();
                u = sc.nextDouble();
                p = new double[n];
                for (int i = 0; i < n; i++) {
                    p[i] = sc.nextDouble();
                }
 
                String ansStr = "Case #" + tc + ": " + fnc() + System.lineSeparator();
                System.out.print(ansStr);
                ans.append(ansStr);
            }
 
            writeFileStr("src/CSans", ans.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    String fnc() {
        while (true) {
            ArrayList<Double> list = new ArrayList<Double>();
            for(int i=0;i<n;i++) {
                if(!list.contains(p[i])) {
                    list.add(p[i]);
                }
            }
            Collections.sort(list);
            
            double min = list.get(0);
            double min2 = 1.0;
            if (list.size() > 1) {
                min2 = list.get(1);
            }
            
            int cnt = 0;
            for(int i=0;i<n;i++) {
                if(p[i] == min) {
                    cnt ++ ;
                }
            }
            
            double dif = min2 - min;
            if(dif * cnt > u) {
                dif = u / cnt;
            }
            for(int i=0;i<n;i++) {
                if(p[i] == min) {
                    p[i] += dif;
                    u -= dif;
                }
            }
            
            if (u < 0.000000001) {
                break;
            }
        }
 
        double pp = 1.0;
        for (int i = 0; i < n; i++) {
            pp *= p[i];
        }
 
        return String.format("%.7f", pp).replaceAll(",", "").trim();
    }
 
    void writeFileStr(String fileName, String str) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            pw.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new C().run();
    }
 }
