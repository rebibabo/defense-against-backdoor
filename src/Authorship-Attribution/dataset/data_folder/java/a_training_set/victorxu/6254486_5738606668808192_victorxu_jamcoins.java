import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 
 
 public class jamcoins {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("sheep.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("jamcoins.out")));
        int n = 16;
        ArrayList<String> ans = generate("1000000000000001",1);
        out.println("Case #1: ");
        for(int i = 0; i < 50; i++){
            out.println(ans.get(i) + " 3 2 5 2 7 2 3 2 11");
        }
        out.close();
    }
    public static ArrayList<String> generate(String str, int start){
        ArrayList<String> s = new ArrayList<String>();
        s.add(str);
        for(int i = start; i < 15; i++){
            if(str.charAt(i) == '0' && str.charAt(i+1) == '0'){
                s.addAll(generate(str.substring(0, i) + "11" + str.substring(i+2),i));
            }
        }
        return s;
    }
 }
