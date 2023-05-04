import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class RPS {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("RPS.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("RPS.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= t; i++){
            String[] str = buf.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int r = Integer.parseInt(str[1]);
            int p = Integer.parseInt(str[2]);
            int s = Integer.parseInt(str[3]);
            String st = "R";
            String st2 = "P";
            String st3 = "S";
            for(int j = 0; j < n; j++){
                String stemp = "";
                String stemp2 = "";
                String stemp3 = "";
                if(st.compareTo(st2) < 0){
                    stemp = st + st2;
                } else {
                    stemp = st2 + st;
                }
                if(st2.compareTo(st3) < 0){
                    stemp2 = st2 + st3;
                } else {
                    stemp2 = st3 + st2;
                }
                if(st3.compareTo(st) < 0){
                    stemp3 = st3 + st;
                } else {
                    stemp3 = st + st3;
                }
                st = stemp;
                st2 = stemp2;
                st3 = stemp3;
            }
            
            out.print("Case #" + i + ": ");
            int countr = 0;
            int countp = 0;
            int counts = 0;
            for(int j = 0; j < st.length(); j++){
                if(st.charAt(j) == 'R'){
                    countr++;
                } else if(st.charAt(j) == 'P'){
                    countp++;
                } else if(st.charAt(j) == 'S'){
                    counts++;
                }
            }
            if (countr == r && countp == p && counts == s){
                out.println(st);
            } else {
                countr = 0;
                countp = 0;
                counts = 0;
                for(int j = 0; j < st.length(); j++){
                    if(st2.charAt(j) == 'R'){
                        countr++;
                    } else if(st2.charAt(j) == 'P'){
                        countp++;
                    } else if(st2.charAt(j) == 'S'){
                        counts++;
                    }
                }
                if (countr == r && countp == p && counts == s){
                    out.println(st2);
                } else {
                    countr = 0;
                    countp = 0;
                    counts = 0;
                    for(int j = 0; j < st.length(); j++){
                        if(st3.charAt(j) == 'R'){
                            countr++;
                        } else if(st3.charAt(j) == 'P'){
                            countp++;
                        } else if(st3.charAt(j) == 'S'){
                            counts++;
                        }
                    }
                    if (countr == r && countp == p && counts == s){
                        out.println(st3);
                    } else {
                        out.println("IMPOSSIBLE");
                    }
                }
            }
        }
        out.close();
    }
 }
