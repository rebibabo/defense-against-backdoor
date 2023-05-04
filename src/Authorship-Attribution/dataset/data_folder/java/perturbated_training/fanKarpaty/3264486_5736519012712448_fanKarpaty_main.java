import java.io.*;
 import java.util.Scanner;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
         System.setOut(new PrintStream(new File("output.txt")));
         Scanner in = new Scanner(new FileInputStream(new File("input.txt")));
 
         int T = in.nextInt();
         in.nextLine();
         for (int t = 1; t <= T; t++) {
             String[] line = in.nextLine().split(" ");
             char[] arr = line[0].trim().toCharArray();
             int n = Integer.parseInt(line[1].trim());
 
             int ret = 0;
             for (int i = 0; i < arr.length; i++) {
                 if (arr[i] == '-' && i + n > arr.length) {
                     ret = -1;
                     break;
                 }
                 if (arr[i] == '-') {
                     ret++;
                     for (int j = 0; j < n; j++) {
                         arr[i + j] = arr[i + j] == '+' ? '-' : '+';
                     }
                 }
             }
 
 
             System.out.println("Case #" + t + ": " + (ret == -1 ? "IMPOSSIBLE" : ret));
         }
     }
 }
