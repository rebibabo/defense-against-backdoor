import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class B {
    int n;
    int[] c = new int[6];
    char[] color = new char[]{'R', 'O', 'Y', 'G', 'B', 'V'};
    
    void run() {
        try (Scanner sc = new Scanner(System.in)) {
            StringBuilder ans = new StringBuilder();
            int tcNum = Integer.parseInt(sc.next());
            for (int tc = 1; tc <= tcNum; tc++) {
                n = sc.nextInt();
                for(int i=0;i<6;i++) {
                    c[i] = sc.nextInt();
                }
                
                ans.append("Case #" + tc + ": " + fnc() + System.lineSeparator());
            }
 
            writeFileStr("src/BSans", ans.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    String fnc() {
        if(c[0] > c[2] + c[4] || c[2] > c[4] + c[0] || c[4] > c[0] + c[2]) {
            return "IMPOSSIBLE";
        }
        
        int max = -1;
        int maxId = -1;
        for(int i=0;i<6;i+=2) {
            if(max < c[i]) {
                max = c[i];
                maxId = i;
            }
        }
        int min = Integer.MAX_VALUE;
        int minId = -1;
        for(int i=0;i<6;i+=2) {
            if(i!=maxId && min > c[i]) {
                min = c[i];
                minId = i;
            }
        }
                
        int midId = -1;
        for(int i=0;i<6;i+=2) {
            if(i!=maxId && i!=minId) {
                midId = i;
            }
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<c[maxId];i++) {
            list.add(maxId);
        }
        for(int i=0;i<c[midId];i++) {
            list.add(i*2, midId);
        }
        
        for(int i=0;i<list.size()-1;i++) {
            if(list.get(i)==list.get(i+1)) {
                list.add(i+1, minId);
                c[minId]--;
            }
        }
        for(int i=1;i<list.size();i++) {
            if(c[minId]==0) break;
            if(list.get(i-1)!=minId && list.get(i)!=minId) {
                list.add(i, minId);
                c[minId]--;
            }
        }
        if(c[minId]==1) {
            list.add(minId);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
            sb.append(color[list.get(i)]);
        }
        
        return sb.toString();
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
