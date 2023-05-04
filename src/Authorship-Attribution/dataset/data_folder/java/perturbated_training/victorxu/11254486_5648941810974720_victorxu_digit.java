import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class Digit {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("digit.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("digit.out")));
        int q = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= q; i++){
            String s = buf.readLine();
            int[] counts = new int[10];
            for(int j = 0; j < s.length(); j++){
                if (s.charAt(j) == 'Z') {
                    counts[0]++;
                } else if (s.charAt(j) == 'O') {
                    counts[1]++;
                } else if (s.charAt(j) == 'W') {
                    counts[2]++;
                } else if (s.charAt(j) == 'T') {
                    counts[3]++;
                } else if (s.charAt(j) == 'F') {
                    counts[4]++;
                } else if (s.charAt(j) == 'V') {
                    counts[5]++;
                } else if (s.charAt(j) == 'X') {
                    counts[6]++;
                } else if (s.charAt(j) == 'S') {
                    counts[7]++;
                } else if (s.charAt(j) == 'G') {
                    counts[8]++;
                } else if (s.charAt(j) == 'I') {
                    counts[9]++;
                } 
            }
            counts[3] = counts[3] - counts[2] - counts[8];
            counts[7] = counts[7] - counts[6];
            counts[5] = counts[5] - counts[7];
            counts[4] = counts[4] - counts[5];
            counts[1] = counts[1] - counts[0] - counts[2] - counts[4];
            counts[9] = counts[9] - counts[5] - counts[6] - counts[8];
            String res = "";
            for(int j = 0; j < 10; j++){
                while(counts[j] > 0){
                    res = res + Integer.parseInt("" + j);
                    counts[j]--;
                }
            }
            out.println("Case #" + i + ": " + res);
        }
        
        buf.close();
        out.close();
    }
 }
