import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
    }
 
    public Main() throws IOException {
        
        Scanner sc = new Scanner(new File("B-small-attempt7.in"));
        PrintWriter out = new PrintWriter(new File("B-small-attempt7.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            String C = sc.next();
            String J = sc.next();
            String c = "";
            String j = "";
            boolean allDifferent = true;
            int breakpoint = -1;
            for (int i = 0; i < C.length(); i++) {
                char a = C.charAt(i);
                char b = J.charAt(i);
                if (a != '?' && b != '?' && a != b) {
                    allDifferent = false;
                    breakpoint = i;
                    break;
                }
            }
            int startpoint = breakpoint - 1;
            boolean startPointFound = false;
            for (int i = breakpoint - 1; i >= 0; i--) {
                char a = C.charAt(i);
                char b = J.charAt(i);
                if (a != '?' || b != '?') {
                    startpoint = i;
                    startPointFound = true;
                    break;
                }
            }
            if (!startPointFound) {
                startpoint = -1;
            }
            if (allDifferent) {
                for (int i = 0; i < C.length(); i++) {
                    char a = C.charAt(i);
                    char b = J.charAt(i);
                    if (a == '?') {
                        if (b == '?') {
                            c += '0';
                            j += '0';
                        } else {
                            c += b;
                            j += b;
                        }
                    } else {
                        if (b == '?') {
                            c += a;
                            j += a;
                        } else {
                            c += a;
                            j += b;
                        }
                    }
                }
            } else if (breakpoint == 0) {
                char a = C.charAt(0);
                char b = J.charAt(0);
                int valC = Integer.parseInt("" + a);
                int valJ = Integer.parseInt("" + b);
                if (valC > valJ) {
                    c = a + fillZeros(C, 1);
                    j = b + fillNines(J, 1);
                } else {
                    c = a + fillNines(C, 1);
                    j = b + fillZeros(J, 1);
                }
            } else if (!startPointFound) {
                
                startpoint = breakpoint;
                BigInteger smallestDiff = new BigInteger(
                        "1000000000000000000000000");
                String bestC = "";
                String bestJ = "";
                for (int z = 0; z < 10; z++) {
                    for (int u = 0; u < 10; u++) {
                        String zV = "";
                        String uV = "";
                        if (startpoint != 0) {
                            char Z = C.charAt(startpoint - 1);
                            char U = J.charAt(startpoint - 1);
                            if (Z != '?' && (Z - '0' != z)) {
                                continue;
                            }
                            if (U != '?' && (U - '0' != u)) {
                                continue;
                            }
                            zV += z;
                            uV += u;
                        }
 
                        
                        String testC = fillZeros(C, 0, breakpoint - 2) + zV
                                + C.charAt(breakpoint);
                        String testJ = fillZeros(J, 0, breakpoint - 2) + uV
                                + J.charAt(breakpoint);
                        int valC = Integer.parseInt(testC);
                        int valJ = Integer.parseInt(testJ);
                        if (valC > valJ) {
                            String lastC = testC + fillZeros(C, breakpoint + 1);
                            String lastJ = testJ + fillNines(J, breakpoint + 1);
                            BigInteger bC = new BigInteger(lastC);
                            BigInteger bJ = new BigInteger(lastJ);
                            BigInteger diff = bC.subtract(bJ);
                            if (diff.compareTo(smallestDiff) < 0) {
                                smallestDiff = diff;
                                bestC = lastC;
                                bestJ = lastJ;
                            }
                        } else {
                            String lastC = testC + fillNines(C, breakpoint + 1);
                            String lastJ = testJ + fillZeros(J, breakpoint + 1);
                            BigInteger bC = new BigInteger(lastC);
                            BigInteger bJ = new BigInteger(lastJ);
                            BigInteger diff = bJ.subtract(bC);
                            if (diff.compareTo(smallestDiff) < 0) {
                                smallestDiff = diff;
                                bestC = lastC;
                                bestJ = lastJ;
                            }
 
                        }
                        
                        testC = fillNines(C, 0, breakpoint - 2) + zV
                                + C.charAt(breakpoint);
                        testJ = fillZeros(J, 0, breakpoint - 2) + uV
                                + J.charAt(breakpoint);
                        valC = Integer.parseInt(testC);
                        valJ = Integer.parseInt(testJ);
                        if (valC > valJ) {
                            String lastC = testC + fillZeros(C, breakpoint + 1);
                            String lastJ = testJ + fillNines(J, breakpoint + 1);
                            BigInteger bC = new BigInteger(lastC);
                            BigInteger bJ = new BigInteger(lastJ);
                            BigInteger diff = bC.subtract(bJ);
                            if (diff.compareTo(smallestDiff) < 0) {
                                smallestDiff = diff;
                                bestC = lastC;
                                bestJ = lastJ;
                            }
                        } else {
                            String lastC = testC + fillNines(C, breakpoint + 1);
                            String lastJ = testJ + fillZeros(J, breakpoint + 1);
                            BigInteger bC = new BigInteger(lastC);
                            BigInteger bJ = new BigInteger(lastJ);
                            BigInteger diff = bJ.subtract(bC);
                            if (diff.compareTo(smallestDiff) < 0) {
                                smallestDiff = diff;
                                bestC = lastC;
                                bestJ = lastJ;
                            }
 
                        }
                        
                        testC = fillZeros(C, 0, breakpoint - 2) + zV
                                + C.charAt(breakpoint);
                        testJ = fillNines(J, 0, breakpoint - 2) + uV
                                + J.charAt(breakpoint);
                        valC = Integer.parseInt(testC);
                        valJ = Integer.parseInt(testJ);
                        if (valC > valJ) {
                            String lastC = testC + fillZeros(C, breakpoint + 1);
                            String lastJ = testJ + fillNines(J, breakpoint + 1);
                            BigInteger bC = new BigInteger(lastC);
                            BigInteger bJ = new BigInteger(lastJ);
                            BigInteger diff = bC.subtract(bJ);
                            if (diff.compareTo(smallestDiff) < 0) {
                                smallestDiff = diff;
                                bestC = lastC;
                                bestJ = lastJ;
                            }
                        } else {
                            String lastC = testC + fillNines(C, breakpoint + 1);
                            String lastJ = testJ + fillZeros(J, breakpoint + 1);
                            BigInteger bC = new BigInteger(lastC);
                            BigInteger bJ = new BigInteger(lastJ);
                            BigInteger diff = bJ.subtract(bC);
                            if (diff.compareTo(smallestDiff) < 0) {
                                smallestDiff = diff;
                                bestC = lastC;
                                bestJ = lastJ;
                            }
                        }
                    }
                }
                c += bestC;
                j += bestJ;
            } else {
                for (int i = 0; i < startpoint - 1; i++) {
                    char a = C.charAt(i);
                    char b = J.charAt(i);
                    if (a == '?') {
                        if (b == '?') {
                            c += '0';
                            j += '0';
                        } else {
                            c += b;
                            j += b;
                        }
                    } else {
                        if (b == '?') {
                            c += a;
                            j += a;
                        } else {
                            System.out.println("ERROR");
                        }
                    }
                }
                BigInteger smallestDiff = new BigInteger(
                        "1000000000000000000000000");
                String bestC = "";
                String bestJ = "";
                for (int x = 0; x < 10; x++) {
                    for (int y = 0; y < 10; y++) {
                        for (int z = 0; z < 10; z++) {
                            for (int u = 0; u < 10; u++) {
                                for (int m = 0; m < 10; m++) {
                                    for (int n = 0; n < 10; n++) {
                                        char a = C.charAt(startpoint);
                                        char b = J.charAt(startpoint);
                                        if (a != '?' && (a - '0' != x)) {
                                            continue;
                                        }
                                        if (b != '?' && (b - '0' != y)) {
                                            continue;
                                        }
                                        String zV = "";
                                        String uV = "";
                                        if (startpoint != 0) {
                                            char Z = C.charAt(startpoint - 1);
                                            char U = J.charAt(startpoint - 1);
                                            if (Z != '?' && (Z - '0' != z)) {
                                                continue;
                                            }
                                            if (U != '?' && (U - '0' != u)) {
                                                continue;
                                            }
                                            zV += z;
                                            uV += u;
                                        }
                                        String mV = "";
                                        String nV = "";
                                        if (breakpoint-startpoint > 1) {
                                            char Z = C.charAt(breakpoint - 1);
                                            char U = J.charAt(breakpoint - 1);
                                            if (Z != '?' && (Z - '0' != z)) {
                                                continue;
                                            }
                                            if (U != '?' && (U - '0' != u)) {
                                                continue;
                                            }
                                            mV += m;
                                            nV += n;
                                        }
                                        
                                        String testC = zV
                                                + x
                                                + fillZeros(C, startpoint + 1,
                                                        breakpoint - 2)
                                                + mV + C.charAt(breakpoint);
                                        String testJ = uV
                                                + y
                                                + fillZeros(J, startpoint + 1,
                                                        breakpoint - 2)
                                                + nV + J.charAt(breakpoint);
                                        int valC = Integer.parseInt(testC);
                                        int valJ = Integer.parseInt(testJ);
                                        if (valC > valJ) {
                                            String lastC = testC
                                                    + fillZeros(C,
                                                            breakpoint + 1);
                                            String lastJ = testJ
                                                    + fillNines(J,
                                                            breakpoint + 1);
                                            BigInteger bC = new BigInteger(
                                                    lastC);
                                            BigInteger bJ = new BigInteger(
                                                    lastJ);
                                            BigInteger diff = bC.subtract(bJ);
                                            if (diff.compareTo(smallestDiff) < 0) {
                                                smallestDiff = diff;
                                                bestC = lastC;
                                                bestJ = lastJ;
                                            }
                                        } else {
                                            String lastC = testC
                                                    + fillNines(C,
                                                            breakpoint + 1);
                                            String lastJ = testJ
                                                    + fillZeros(J,
                                                            breakpoint + 1);
                                            BigInteger bC = new BigInteger(
                                                    lastC);
                                            BigInteger bJ = new BigInteger(
                                                    lastJ);
                                            BigInteger diff = bJ.subtract(bC);
                                            if (diff.compareTo(smallestDiff) < 0) {
                                                smallestDiff = diff;
                                                bestC = lastC;
                                                bestJ = lastJ;
                                            }
 
                                        }
                                        
                                        testC = zV
                                                + x
                                                + fillNines(C, startpoint + 1,
                                                        breakpoint - 2)
                                                + mV + C.charAt(breakpoint);
                                        testJ = uV
                                                + y
                                                + fillZeros(J, startpoint + 1,
                                                        breakpoint - 2)
                                                + nV + J.charAt(breakpoint);
                                        valC = Integer.parseInt(testC);
                                        valJ = Integer.parseInt(testJ);
                                        if (valC > valJ) {
                                            String lastC = testC
                                                    + fillZeros(C,
                                                            breakpoint + 1);
                                            String lastJ = testJ
                                                    + fillNines(J,
                                                            breakpoint + 1);
                                            BigInteger bC = new BigInteger(
                                                    lastC);
                                            BigInteger bJ = new BigInteger(
                                                    lastJ);
                                            BigInteger diff = bC.subtract(bJ);
                                            if (diff.compareTo(smallestDiff) < 0) {
                                                smallestDiff = diff;
                                                bestC = lastC;
                                                bestJ = lastJ;
                                            }
                                        } else {
                                            String lastC = testC
                                                    + fillNines(C,
                                                            breakpoint + 1);
                                            String lastJ = testJ
                                                    + fillZeros(J,
                                                            breakpoint + 1);
                                            BigInteger bC = new BigInteger(
                                                    lastC);
                                            BigInteger bJ = new BigInteger(
                                                    lastJ);
                                            BigInteger diff = bJ.subtract(bC);
                                            if (diff.compareTo(smallestDiff) < 0) {
                                                smallestDiff = diff;
                                                bestC = lastC;
                                                bestJ = lastJ;
                                            }
 
                                        }
                                        
                                        testC = zV
                                                + x
                                                + fillZeros(C, startpoint + 1,
                                                        breakpoint - 2)
                                                + mV + C.charAt(breakpoint);
                                        testJ = uV
                                                + y
                                                + fillNines(J, startpoint + 1,
                                                        breakpoint - 2)
                                                + nV + J.charAt(breakpoint);
                                        valC = Integer.parseInt(testC);
                                        valJ = Integer.parseInt(testJ);
                                        if (valC > valJ) {
                                            String lastC = testC
                                                    + fillZeros(C,
                                                            breakpoint + 1);
                                            String lastJ = testJ
                                                    + fillNines(J,
                                                            breakpoint + 1);
                                            BigInteger bC = new BigInteger(
                                                    lastC);
                                            BigInteger bJ = new BigInteger(
                                                    lastJ);
                                            BigInteger diff = bC.subtract(bJ);
                                            if (diff.compareTo(smallestDiff) < 0) {
                                                smallestDiff = diff;
                                                bestC = lastC;
                                                bestJ = lastJ;
                                            }
                                        } else {
                                            String lastC = testC
                                                    + fillNines(C,
                                                            breakpoint + 1);
                                            String lastJ = testJ
                                                    + fillZeros(J,
                                                            breakpoint + 1);
                                            BigInteger bC = new BigInteger(
                                                    lastC);
                                            BigInteger bJ = new BigInteger(
                                                    lastJ);
                                            BigInteger diff = bJ.subtract(bC);
                                            if (diff.compareTo(smallestDiff) < 0) {
                                                smallestDiff = diff;
                                                bestC = lastC;
                                                bestJ = lastJ;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                c += bestC;
                j += bestJ;
            }
 
            String solution = "Case #" + task + ": " + c + " " + j;
            System.out.println(solution);
            out.println(solution);
        }
 
        out.close();
        sc.close();
    }
 
    private String fillZeros(String s, int start) {
        String ret = "";
        for (int i = start; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != '?') {
                ret += ch;
            } else {
                ret += '0';
            }
        }
        return ret;
    }
 
    private String fillZeros(String s, int start, int end) {
        String ret = "";
        for (int i = start; i <= end; i++) {
            char ch = s.charAt(i);
            if (ch != '?') {
                ret += ch;
            } else {
                ret += '0';
            }
        }
        return ret;
    }
 
    private String fillNines(String s, int start) {
        String ret = "";
        for (int i = start; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != '?') {
                ret += ch;
            } else {
                ret += '9';
            }
        }
        return ret;
    }
 
    private String fillNines(String s, int start, int end) {
        String ret = "";
        for (int i = start; i <= end; i++) {
            char ch = s.charAt(i);
            if (ch != '?') {
                ret += ch;
            } else {
                ret += '9';
            }
        }
        return ret;
    }
 }