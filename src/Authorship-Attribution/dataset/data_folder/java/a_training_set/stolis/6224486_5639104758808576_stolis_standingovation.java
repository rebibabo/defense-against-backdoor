package year2015.qualification;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class StandingOvation {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("A-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int S = in.nextInt();
             int answer = 0;
             int total = 0;
             String s = in.next();
             for (int i=0; i<=S; i++) {
                 int people = s.charAt(i)-'0';
                 if (people > 0) {
                     int friends = Math.max(0, i-total);
                     total += friends + people;
                     answer += friends;
                 }
             }
             out.println("Case #"+(t+1)+": "+answer);
         }
 
         out.close();
     }
     
 }
