import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class tidy {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("tidy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tidy.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            int num = Integer.parseInt(buf.readLine());
            int max = 0;
            for(int j = 1; j <= num; j++){
                char[] str = Integer.toString(j).toCharArray();
                boolean b = true;
                for(int k = 0; k < str.length - 1; k++){
                    if(str[k] > str[k+1]) b = false;
                }
                if(b) max = j;
            }
            out.println("Case #" + (i + 1) + ": " + max);
        }
        out.close();
    }
 }
