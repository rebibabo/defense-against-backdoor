import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("A-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("A_small.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            char[][] field = new char[r][c];
            HashSet<Character> set = new HashSet<Character>();
            for (int i = 0; i < r; i++) {
                String row = sc.next();
                for (int j = 0; j < c; j++) {
                    field[i][j] = row.charAt(j);
                    if (row.charAt(j) != '?') {
                        set.add(row.charAt(j));
                    }
                }
            }
            
            solve(field, r, c, set, 0, 0);
            
            System.out.println("Case #" + task + ": ");
            out.println("Case #" + task + ": ");
            for (int i = 0; i < r; i++) {
                String s = "";
                for (int j = 0; j < c; j++) {
                    s += field[i][j];
                }
                System.out.println(s);
                out.println(s);
            }
        }
 
        out.close();
        sc.close();
    }
    
    private void print(char[][] field, int r, int c) {
        for (int i = 0; i < r; i++) {
            String s = "";
            for (int j = 0; j < c; j++) {
                s += field[i][j];
            }
            System.out.println(s);
        }
        System.out.println();
    }
    
    private boolean solve(char[][] field, int r, int c, HashSet<Character> set, int i, int j) {
        if (i==r) {
            
            for (char ch: set) {
                int startI=0;
                int startJ=0;
                int endI=0;
                int endJ=0;
                boolean found = false;
                for (int a = 0; a < r; a++) {
                    for (int b = 0; b < c; b++) {
                        if (field[a][b] == ch) {
                            endI = a;
                            endJ = b;
                            if (!found) {
                                found = true;
                                startI = a;
                                startJ = b;
                            }
                        }
                    }
                }
                boolean poss2 = true;
                for (int a = 0; a < r; a++) {
                    for (int b = 0; b < c; b++) {
                        if (startI <= a && a <= endI && startJ <= b && b <= endJ) {
                            if (field[a][b] != ch) poss2 = false;
                        } else {
                            if (field[a][b] == ch) poss2 = false;
                        }
                    }
                }
                if (!poss2) {
                    return false;
                }
            }
            return true;
        }
        if (field[i][j] == '?') {
            for (char ch: set) {
                field[i][j] = ch;
                int newJ = (j+1)%c;
                int newI = i;
                if (newJ == 0) {
                    newI++;
                }
                if (solve(field, r, c, set, newI, newJ)) {
                    return true;
                }
            }
            field[i][j] = '?';
        } else {
            int newJ = (j+1)%c;
            int newI = i;
            if (newJ == 0) {
                newI++;
            }
            return solve(field, r, c, set, newI, newJ);
        }
        return false;
    }
 }
