package roundq;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class Zad3 {
    
    private int t;
    
    private int[] l;
    
    private int[] x;
    
    private String[] s;
    
    private static final String yes = "YES";
    
    private static final String no = "NO";
    
    private class Quaternion {
        
        private boolean isPositive;
        
        private char state;
        
        public Quaternion() {
            isPositive = true;
            state = '1';
        }
        
        public boolean isI() {
            return isPositive && (state == 'i');
        }
        
        public boolean isJ() {
            return isPositive && (state == 'j');
        }
        
        public boolean isK() {
            return isPositive && (state == 'k');
        }
        
        public void multiply(char c) {
            
            if (c != '1') {
                if (state == c) {
                    state = '1';
                    isPositive = !isPositive;
                } else if (state == '1') {
                    state = c;
                } else if (state == 'i') {
                    if (c == 'j') {
                        state = 'k';
                    } else {
                        state = 'j';
                        isPositive = !isPositive;
                    }
                } else if (state == 'j') {
                    if (c == 'i') {
                        state = 'k';
                        isPositive = !isPositive;
                    } else {
                        state = 'i';
                    }
                } else {
                    if (c == 'i') {
                        state = 'j';
                    } else {
                        state = 'i';
                        isPositive = !isPositive;
                    }
                }
            }
        }
        
        @Override
        public String toString() {
            return "[" + (isPositive ? "+" : "-") + state + "]";
        }
    }
    
    public void readInput() throws FileNotFoundException {
        Scanner s = new Scanner(new File("zad3.in"));
        
        t = s.nextInt();
        l = new int[t];
        x = new int[t];
        this.s = new String[t];
        
        for (int i = 0; i < t; i++) {
            l[i] = s.nextInt();
            x[i] = s.nextInt();
            this.s[i] = s.next();
        }
        
        s.close();
    }
    
    private boolean checkIfK(String s, int from) {
        Quaternion q = new Quaternion();
        for (int i = from; i < s.length(); i++) {
            q.multiply(s.charAt(i));
        }
        return q.isK();
    }
    
    private boolean findJ(String s, int from) {
        
        Quaternion q = new Quaternion();
        for (int j = from; j < s.length(); j++) {
            q.multiply(s.charAt(j));
            
            if (q.isJ()) {
                if (checkIfK(s, j + 1)) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    private String existIJK(String s) {
        
        Quaternion q = new Quaternion();
        for (int i = 0; i < s.length(); i++) {
            q.multiply(s.charAt(i));
            
            if (q.isI()) {
                if (findJ(s, i+1)) {
                    return yes;
                }
            }
        }
        
        return no;
    }
    
    private String solveCase(int caseNum) {
        
        int x = this.x[caseNum];
        StringBuilder builder = new StringBuilder();
        String s = this.s[caseNum];
        for (int i = 0; i < x; i++) {
            builder.append(s);
        }
        String ijk = builder.toString();
        return existIJK(ijk);
    }
    
    public void solveAndPrintOutput() {
        
        for (int i = 0; i < t; i++) {
            System.out.println("Case #" + (i+1) + ": " + solveCase(i));
        }
    }
 
    public static void main(String[] args) {
 
        Zad3 z = new Zad3();
        try {
            z.readInput();
            z.solveAndPrintOutput();
            
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        
 
 
    }
 
 }
