import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class RedTape {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("redtape.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("redtape.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= t; i++){
            String[] str = buf.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            String[] str2 = buf.readLine().split(" ");
            double[] in = new double[n];
            for(int j = 0; j < n; j++) in[j] = Double.parseDouble(str2[j]);
            double[] prb = new double[k+1];
            prb[0] = 1.0;
            System.out.println(i);
            out.println("Case #" + i + ": " + search(0,prb,k,0,n,in));
        }
        out.close();
    }
    public static double search(int i, double[] prb, int k, int j, int n, double[] input){
        if(i == k){
            return prb[k/2];
        }
        double max = 0;
        for(int c = j; c < n - k + i + 1; c++){
            double[] prb2 = new double[k+1];
            for(int d = 0; d < k+1; d++){
                
                prb2[d] = prb[d] * (1 - input[c]);
                if(d > 0) prb2[d] += prb[d-1] * input[c];
            }
            double val = search(i+1, prb2, k, c+1, n, input);
            if(val > max) max = val;
        }
        return max;
    }
 }
