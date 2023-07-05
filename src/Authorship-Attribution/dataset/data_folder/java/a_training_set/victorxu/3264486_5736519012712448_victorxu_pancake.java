import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class pancake {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("pancake.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pancake.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            String[] s = buf.readLine().split(" ");
            int k = Integer.parseInt(s[1]);
            char[] line = s[0].toCharArray();
            int count = 0;
            for(int j = 0; j < line.length - k + 1; j++){
                if(line[j] == '-'){
                    count++;
                    for(int z = 0; z < k; z++){
                        if(line[j+z] == '-') {
                            line[j+z] = '+';
                        } else {
                            line[j+z] = '-';
                        }
                    }
                }
            }
            System.out.println(String.valueOf(line));
            boolean good = true;
            for(int j = line.length - k + 1; j < line.length; j++){
                if(line[j] == '-') good = false;
            }
            if(good){
                out.println("Case #" + (i + 1) + ": " + count);
            } else {
                out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
            }
        }
        out.close();
    }
 }
