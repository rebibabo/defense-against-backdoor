package qcj2016jr;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.StringTokenizer;
 
 public class ProblemC {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        ProblemC obj = new ProblemC();
        obj.solve(fileName);
    }
 
    ArrayList<BigInteger> prime = null;
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        prime = new ArrayList<>();
        boolean[] chk = new boolean[100000];
        for ( int i = 2 ; i < chk.length ; i++ ) {
            if (chk[i] == false) {
                prime.add(BigInteger.valueOf(i));
                int cur = i;
                while (cur < chk.length) {
                    chk[cur] = true;
                    cur += i;
                }
            }
        }
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(token.nextToken());
            int J = Integer.parseInt(token.nextToken());
 
            bw.write("Case #" + (i + 1) + ":");
            bw.write("\r\n");
            
            long base = (1L << (N - 1)) | 1L;
            int last = 1 << (N - 2);
            int[] ansArr = new int[9];
            int cnt = 0;
            LOOP_j:for (int j = 0; j < last; j++) {
                long num = base | (((long)j) << 1);
                String s = Long.toBinaryString(num);
                
                for (int b = 2 ; b <= 10 ; b++) {
                    BigInteger a = new BigInteger(s,b);
                    int c = getComposite(a);
                    if ( c == -1 ) {
                        continue LOOP_j;
                    }
                    ansArr[b - 2] = c;
                }
                bw.write(s);
                for (int b = 2 ; b <= 10 ; b++) {
                    bw.write(" " + ansArr[b - 2]);
                }
                bw.write("\r\n");
                if ( ++cnt >= J ) {
                    break;
                }
            }
        }
        bw.close();
        br.close();
    }
    
    private int getComposite(BigInteger num) {
        for (BigInteger p : prime) {
            if ( num.mod(p) == BigInteger.ZERO && num != p) {
                return p.intValue();
            } else if ( p.multiply(BigInteger.valueOf(2)).compareTo(num) > 0 ) {
                return -1;
            }
        }
        return -1;
    }
 }
