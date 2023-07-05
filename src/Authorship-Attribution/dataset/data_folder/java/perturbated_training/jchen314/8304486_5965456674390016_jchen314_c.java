import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class C {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int C = sc.nextInt();
 
             long total = 0;
             List<List<Integer>> ins = new ArrayList<>();
             for (int i = 0; i <= C; i++) {
                 ins.add(new ArrayList<>());
             }
             int[][] outs = new int[C + 1][2];
             for (int i = 1; i <= 2 * C; i++) {
                 int target = sc.nextInt();
                 int out = sc.nextInt();
                 int time = sc.nextInt();
                 outs[(i + 1) / 2][i % 2] = out;
                 int in = (out + time) % 24;
                 ins.get(target).add(in);
                 total += time;
             }
 
             {
                 int i = 1;
                 int a = (outs[i][0] - ins.get(i).get(0) + 24) % 24 + outs[i][1];
                 int b = (outs[i][1] - ins.get(i).get(0) + 24) % 24 + outs[i][0];
                 int c = (outs[i][0] - ins.get(i).get(1) + 24) % 24 + outs[i][1];
                 int d = (outs[i][1] - ins.get(i).get(1) + 24) % 24 + outs[i][0];
                 total += Math.min(a, Math.min(b, Math.min(c, d)));
             }
             for (int i = 2; i <= C; i++) {
                 int a = (outs[i][0] - ins.get(i).get(0) + 24) % 24 + (outs[i][1] - ins.get(i).get(1) + 24) % 24;
                 int b = (outs[i][1] - ins.get(i).get(0) + 24) % 24 + (outs[i][0] - ins.get(i).get(1) + 24) % 24;
                 total += Math.min(a, b);
             }
 
             System.out.print("Case #" + caseNum + ": ");
             System.out.println(total);
         }
     }
 }
