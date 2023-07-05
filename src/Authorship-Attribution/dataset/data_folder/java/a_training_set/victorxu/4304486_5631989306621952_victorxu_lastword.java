import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class lastWord {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("lastword.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lastword.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++){
            String s = buf.readLine();
            String best = s.charAt(0) + "";
            char c = s.charAt(0);
            for(int j = 1; j < s.length(); j++){
                if (s.charAt(j) >= c){
                    c = s.charAt(j);
                    best = s.charAt(j) + best;
                } else {
                    best = best + s.charAt(j);
                }
            }
            out.print("Case #"+(i+1)+": ");
            out.println(best);
        }
        out.close();
    }
 }
