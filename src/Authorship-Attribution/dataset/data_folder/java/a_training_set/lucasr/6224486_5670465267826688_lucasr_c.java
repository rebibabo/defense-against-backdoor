package qualy;
 
 import java.util.Scanner;
 
 public class C {
    private static int[][] times = {{0, 1, 2, 3},
                                    {1, 0, 3, 2},
                                    {2, 3, 0, 1},
                                    {3, 2, 1, 0}};
    
    private static int[][] timesSign = {{1,  1,  1,  1},
                                        {1, -1,  1, -1},
                                        {1, -1, -1,  1},
                                        {1,  1, -1, -1}};
    
    private static class SignedVal {
        private int sign, val;
 
        public SignedVal(int sign, int val) {
            this.sign = sign;
            this.val = val;
        }
        
        private SignedVal times(SignedVal other) {
            sign *= timesSign[val][other.val] * other.sign;
            val = times[val][other.val];
            return this;
        }
        
        private SignedVal preTimes(SignedVal other) {
            sign *= timesSign[other.val][val] * other.sign;
            val = times[other.val][val];
            return this;
        }
        
        private SignedVal copy() {
            return new SignedVal(sign, val);
        }
 
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SignedVal other = (SignedVal) obj;
            if (sign != other.sign)
                return false;
            if (val != other.val)
                return false;
            return true;
        }
    }
    
    private static SignedVal I = new SignedVal(1, 1), J = new SignedVal(1, 2), K = new SignedVal(1,  3), ONE = new SignedVal(1, 0);
    private static SignedVal ALL = I.copy().times(J).times(K);
    
    private static SignedVal get(char c) {
        if (c == 'i') return I;
        if (c == 'j') return J;
        return K;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int L = sc.nextInt();
            long X = sc.nextLong();
            long total = X * L;
            String str = sc.next();
            boolean impossible = false;
            SignedVal allProduct = ONE.copy(), tmp = ONE.copy();
            for (int i = 0; i < L; i++) tmp.times(get(str.charAt(i)));
            for (int i = 0; i < X % 4; i++) allProduct.times(tmp);
            if (!allProduct.equals(ALL)) impossible = true;
            
            if (!impossible) {
                int usedForI = -1, usedForK = -1, xmin4 = (int) Math.min(4, X);
                SignedVal prod = ONE.copy();
                for (int i = 0; i < xmin4 * L; i++) {
                    prod.times(get(str.charAt(i % L)));
                    if (I.equals(prod)) {
                        usedForI = i + 1;
                        break;
                    }
                }
                prod = ONE.copy();
                for (int i = 0; i < xmin4 * L; i++) {
                    prod.preTimes(get(str.charAt(L - 1 - (i % L))));
                    if (K.equals(prod)) {
                        usedForK = i + 1;
                        break;
                    }
                }
                if (usedForI < 0 || usedForK < 0 || usedForI + usedForK >= total) impossible = true;
            }
            
            System.out.println("Case #" + caze + ": " + (impossible ? "NO" : "YES"));
        }
    }
 
 }
