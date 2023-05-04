import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 
 
 public class rankfile {
    static boolean skiprf = false;
    static int skipnf = -1;
    static int rowf = -1;
    static int colf = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("rankfile.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rankfile.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++){
            skiprf = false;
            skipnf = -1;
            rowf = -1;
            colf = -1;
            out.print("Case #"+(i+1)+": ");
            int n = Integer.parseInt(buf.readLine());
            Tuple[] rf = new Tuple[2*n-1];
            for(int j = 0; j < 2*n-1; j++) rf[j] = new Tuple(n);
            for(int j = 0; j < 2 * n - 1; j++){
                String[] s = buf.readLine().split(" ");
                for(int k = 0; k < n; k++){
                    
                    rf[j].values[k] = Integer.parseInt(s[k]);
                }
            }
            Arrays.sort(rf);
            
 
            
            int[][] soldiers = new int[n][n];
            for(int j = 0; j < n; j++) soldiers[0][j] = rf[0].values[j];
            
            int[][] result = fill(soldiers, rf, 1, 0, 1, n, false, false, -1);
            if(skipnf != -1){
                if(skiprf){
                    for(int j = 0; j < n; j++){
                        System.out.println(i + " " + skipnf + " " + j);
                        out.print(result[skipnf][j] + " ");
                    }
                } else {
                    for(int j = 0; j < n; j++){
                        out.print(result[j][skipnf] + " ");
                    }
                }
            } else {
                System.out.println(i + " " + rowf + " " + colf);
                if(rowf != n){
                    for(int j = 0; j < n; j++){
                        System.out.println((n-1) + " "+ j);
                        System.out.println(soldiers.length +" "+ result.length);
                        out.print(result[n-1][j] + " ");
                    }
                } else if(colf != n){
                    for(int j = 0; j < n; j++){
                        out.print(result[j][n-1] + " ");
                    }
                } else {
                    System.out.println("Impossible");
                }
            }
            out.println();
 
        }
        out.close();
    }
    public static int[][] fill(int[][] soldiers, Tuple[] rf, int mrow, int mcol, int ind, int n, boolean skip, boolean skipr, int skipn){
        System.out.println(mrow + " " + mcol);
        for(int i = 0; i < n; i++){
            System.out.println(Arrays.toString(soldiers[i]));
        }
    
        if(ind == 2 * n - 1) {
            rowf = mrow;
            colf = mcol;
            return soldiers;
        }
        boolean fail = false;
        if(mrow < n && (mrow == 0 || rf[ind].values[0] > soldiers[mrow-1][0])){
            for(int i = 0; i < n; i++){
                if(soldiers[mrow][i] != 0 && soldiers[mrow][i] != rf[ind].values[i]) {
                    fail = true;
                }
            }
            if(!fail) {
                int[][] tr = new int[n][n];
                int[][] scopy = new int[n][n];
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        scopy[i][j] = soldiers[i][j];
                    }
                }
                for(int i = 0; i < n; i++) scopy[mrow][i] = rf[ind].values[i];
                tr = fill(scopy, rf, mrow+1, mcol, ind+1, n, skip, skipr, skipn);
                if(tr != null) {
                    return tr;
                }
            }
        }
        if(mcol < n && (mcol == 0 || rf[ind].values[0] > soldiers[0][mcol-1])){
            fail = false;
            for(int i = 0; i < n; i++){
                if(soldiers[i][mcol] != 0 && soldiers[i][mcol] != rf[ind].values[i]) {
                    fail = true;
                }
            }
            if(!fail) {
                int[][] tr = new int[n][n];
                int[][] scopy = new int[n][n];
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        scopy[i][j] = soldiers[i][j];
                    }
                }
                for(int i = 0; i < n; i++) scopy[i][mcol] = rf[ind].values[i];
                tr = fill(scopy, rf, mrow, mcol+1, ind+1, n, skip, skipr, skipn);
                if(tr != null) {
                    return tr;
                }
            }
        }
        if(skip == false){
            if(mrow < n-1){
                fail = false;
                mrow++;
                for(int i = 0; i < n; i++){
                    if(soldiers[mrow][i] != 0 && soldiers[mrow][i] != rf[ind].values[i]) {
                        fail = true;
                    }
                }
                if(!fail) {
                    int[][] tr = new int[n][n];
                    int[][] scopy = new int[n][n];
                    for(int i = 0; i < n; i++){
                        for(int j = 0; j < n; j++){
                            scopy[i][j] = soldiers[i][j];
                        }
                    }
                    for(int i = 0; i < n; i++) scopy[mrow][i] = rf[ind].values[i];
                    tr = fill(scopy, rf, mrow+1, mcol, ind+1, n, true, true, mrow-1);
                    if(tr != null) {
                        skiprf = true;
                        skipnf = mrow-1;
 
                        return tr;
                    }
                }
                mrow--;
            }
            if(mcol < n-1){
                fail = false;
                mcol++;
                for(int i = 0; i < n; i++){
                    if(soldiers[i][mcol] != 0 && soldiers[i][mcol] != rf[ind].values[i]) {
                        fail = true;
                    }
                }
                if(!fail) {
                    int[][] tr = new int[n][n];
                    int[][] scopy = new int[n][n];
                    for(int i = 0; i < n; i++){
                        for(int j = 0; j < n; j++){
                            scopy[i][j] = soldiers[i][j];
                        }
                    }
                    for(int i = 0; i < n; i++) scopy[i][mcol] = rf[ind].values[i];
                    tr = fill(scopy, rf, mrow, mcol+1, ind+1, n, true, false, mcol-1);
                    if(tr != null) {
                        skiprf = false;
                        skipnf = mcol-1;
                        return tr;
                    }
                }
                mcol--;
            }
        }
        return null;
    }
    public static class Tuple implements Comparable<Tuple>{
        int[] values;
        public Tuple(int n){
            values = new int[n];
        }
        public Tuple(int[] n){
            values = n;
        }
        @Override
        public int compareTo(Tuple t) {
            return this.values[0] - t.values[0];
        }
    }
 }
