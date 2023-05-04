import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.Scanner;
 
 public class A {
    int n, k;
    double[][] rh;
    
    double pi = 3.1415926535897932384626433832795028841971; 
    
    void run() {
        try (Scanner sc = new Scanner(System.in)) {
            StringBuilder ans = new StringBuilder();
            int tcNum = Integer.parseInt(sc.next());
            for (int tc = 1; tc <= tcNum; tc++) {
                n = sc.nextInt();
                k = sc.nextInt();
                rh = new double[n][3];
                for(int i=0;i<n;i++) {
                    rh[i][0] = sc.nextInt();
                    rh[i][1] = sc.nextInt();
                    rh[i][2] = pi * rh[i][0] * 2 * rh[i][1];
                }
                
                Arrays.sort(rh, new Comparator<double[]>(){
                    public int compare(double[] o1, double[] o2) {
                        if(o2[2] == o1[2]) {
                            if(o2[0] > o1[0]) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }else{
                            if(o2[2] > o1[2]) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
                
                String ansStr = "Case #" + tc + ": " + fnc() + System.lineSeparator();
                System.out.print(ansStr);
                ans.append(ansStr);
            }
 
            writeFileStr("src/ASans", ans.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    double max;
    String fnc() {
        max = 0;
        makeperm(0, new int[n], new boolean[n]);
        
        return String.format("%.8f", max).replaceAll(",", "").trim();
    }
 
    
    void check (int[] perm) {
        double sum = 0;
        double maxR = 0;
        for(int i=0;i<k;i++) {
            sum += rh[perm[i]][2];
            maxR = Math.max(maxR, rh[perm[i]][0]);
        }
        sum += maxR * pi * maxR;
        max = Math.max(max, sum);
    }
    
    void makeperm(int kk, int[] perm, boolean[] flag){
        if(kk==k){
            check(perm);
        }else{
            for(int i=0;i<n;i++){
                if(flag[i]==true) continue;
                perm[kk] = i;
                flag[i] = true;
                makeperm(kk+1, perm, flag);
                flag[i] = false;
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
        new A().run();
    }
 }
