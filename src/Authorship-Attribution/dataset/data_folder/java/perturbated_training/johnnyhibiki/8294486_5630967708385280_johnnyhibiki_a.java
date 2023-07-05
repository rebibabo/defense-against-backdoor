import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
 
    int d, n;
    int[] k, s;
    
    void run() {
        try (Scanner sc = new Scanner(System.in)) {
            StringBuilder ans = new StringBuilder();
            int tcNum = Integer.parseInt(sc.next());
            for (int tc = 1; tc <= tcNum; tc++) {
                d = sc.nextInt();
                n = sc.nextInt();
                k = new int[n];
                s = new int[n];
                for(int i=0;i<n;i++) {
                    k[i] = sc.nextInt();
                    s[i] = sc.nextInt();
                }
                
                ans.append("Case #" + tc + ": " + fnc() + System.lineSeparator());
            }
 
            writeFileStr("src/ASans", ans.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    String fnc() {
        double max = -1;
        for(int i=0;i<n;i++) {
            max = Math.max(max, (double)(d-k[i])/s[i]);
        }
        
        return String.format("%.7f", d/max);
    }
 
    void writeFileStr(String fileName, String str) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            pw.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new A().run();
    }
 }
