import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class mushroom {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("mushroom.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mushroom.out")));
        int n = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= n; i++){
            int m = Integer.parseInt(buf.readLine());
            String[] s = buf.readLine().split(" ");
            int fsum = 0;
            int largest = 0;
            for(int j = 1; j < m; j++){
                if(Integer.parseInt(s[j]) - Integer.parseInt(s[j-1]) < 0){
                    fsum = fsum + Integer.parseInt(s[j-1]) - Integer.parseInt(s[j]);
                    if(Integer.parseInt(s[j-1]) - Integer.parseInt(s[j]) > largest) largest = Integer.parseInt(s[j-1]) - Integer.parseInt(s[j]);
                }
            }
            int nsum = 0;
            for(int j = 0; j < m-1; j++){
                nsum = nsum + Math.min(Integer.parseInt(s[j]), largest);
            }
            out.println("Case #" + i + ": " + fsum + " " + nsum); 
        }
        buf.close();
        out.close();
    }
 }
