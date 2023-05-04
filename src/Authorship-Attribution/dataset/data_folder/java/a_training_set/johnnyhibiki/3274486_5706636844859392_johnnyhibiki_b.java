import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class B {
    int ac, aj;
    int[][] cc, jj;
    
    void run() {
        try (Scanner sc = new Scanner(System.in)) {
            StringBuilder ans = new StringBuilder();
            int tcNum = Integer.parseInt(sc.next());
            for (int tc = 1; tc <= tcNum; tc++) {
                ac = sc.nextInt();
                aj = sc.nextInt();
                cc = new int[ac][2];
                jj = new int[aj][2];
                for(int i=0;i<ac;i++) {
                    cc[i][0] = sc.nextInt();
                    cc[i][1] = sc.nextInt();
                }
                for(int i=0;i<aj;i++) {
                    jj[i][0] = sc.nextInt();
                    jj[i][1] = sc.nextInt();
                }
                
                String ansStr = "Case #" + tc + ": " + fnc() + System.lineSeparator();
                System.out.print(ansStr);
                ans.append(ansStr);
            }
 
            writeFileStr("src/BSans", ans.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    String fnc() {
        if(ac == 1 && aj == 1 || ac + aj == 1) {
            return "" + 2;
        } else {
            
            if(ac == 2) {
                if(cc[0][0] < cc[1][0]) {
                    if(cc[1][1] - cc[0][0] <= 720 || 1440 + cc[0][1] - cc[1][0] <= 720) {
                        return "" + 2;
                    } else {
                        return "" + 4;
                    }
                } else {
                    if(cc[0][1] - cc[1][0] <= 720 || 1440 + cc[1][1] - cc[0][0] <= 720) {
                        return "" + 2;
                    } else {
                        return "" + 4;
                    }
                }
            } else {
                if(jj[0][0] < jj[1][0]) {
                    if(jj[1][1] - jj[0][0] <= 720 || 1440 + jj[0][1] - jj[1][0] <= 720) {
                        return "" + 2;
                    } else {
                        return "" + 4;
                    }
                } else {
                    if(jj[0][1] - jj[1][0] <= 720 || 1440 + jj[1][1] - jj[0][0] <= 720) {
                        return "" + 2;
                    } else {
                        return "" + 4;
                    }
                }
            }
        }
    }
 
    void writeFileStr(String fileName, String str) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            pw.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new B().run();
    }
 }
