import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 
 
 public class Logging {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("logging.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logging.out")));
        int q = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= q; i++){
            out.println("Case #" + i + ":");
            int n = Integer.parseInt(buf.readLine());
            int[][] coor = new int[n][2];
            for(int j = 0; j < n; j++){
                String[] s = buf.readLine().split(" ");
                coor[j][0] = Integer.parseInt(s[0]);
                coor[j][1] = Integer.parseInt(s[1]);
            }
            for(int j = 0; j < n; j++){
                double[] angle = new double[n-1];
                for(int k = 0; k < n; k++){
                    if(j > k) {
                        if(coor[k][1] - coor[j][1] == 0 && coor[k][0] - coor[j][0] < 0) angle[k] = Math.PI;
                        else {
                            angle[k] = (Math.atan2((coor[k][1] - coor[j][1]) , (coor[k][0] - coor[j][0])) + Math.PI) % Math.PI;
                            
                            if((coor[k][1] - coor[j][1]) < 0) angle[k] = angle[k] + Math.PI;
                        }
                        
                    }
                    if(j < k) {
                        if(coor[k][1] - coor[j][1] == 0 && coor[k][0] - coor[j][0] < 0) angle[k-1] = Math.PI;
                        else {
                            angle[k-1] = (Math.atan2((coor[k][1] - coor[j][1]) , (coor[k][0] - coor[j][0])) + Math.PI) % Math.PI;
                            
                            if((coor[k][1] - coor[j][1]) < 0) angle[k-1] = angle[k-1] + Math.PI;
                        }
                        
                    }
                }
                Arrays.sort(angle);
                if(j == 3) System.out.println(Arrays.toString(angle));
                int h = -1;
                int l = 0;
                int r = -1;
                int zc = 0;
                for(int k = 0; k < n-1; k++){
                    if(angle[k] == 0) zc++;
                    else break;
                }
                for(int k = 0; k < n-1; k++){
                    if(angle[k] == Math.PI){
                        h = k-1;
                        break;
                    }
                    if(angle[k] > Math.PI){
                        h = k-1;
                        break;
                    }
                }
                if(h < 0){
                    out.println(0);
                }else{
                    int maxr = h + 1;
                    int minr = h + 1 - zc;
                    double top = Math.PI;
                    while(h < n-2){
                        h++;
                        int border = 1;
                        top = angle[h];
                        if(j == 3) System.out.println(angle[h]);
                        while(angle[l] < top - Math.PI) l++;
                        while(Math.abs(angle[l] - top + Math.PI) < 0.00000001) {
                            l++;
                            border++;
                        }
                        
                        
                        
                        
                        r = h - l;
                        if(j == 3) System.out.println(r + " " + h + " " + l);
                        if(r < minr) minr = r;
                        while(h < n-2 && Math.abs(angle[h+1] - top) < 0.00000001){
                            h++;
                            border++;
                        }
                        
                        
                        
                        
                        r = r + border;
                        if(j == 3) System.out.println(r);
                        if(r > maxr) maxr = r;
                    }
                    int ans = Math.min(minr, n-1-maxr);
                    out.println(ans);
                }
            }
        }
        buf.close();
        out.close();
    }
 }
