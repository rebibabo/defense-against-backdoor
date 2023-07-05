import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("C-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new File("C_small1.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            char[][] field = new char[r][c];
            for (int i = 0; i < r; i++) {
                String row = sc.next();
                for (int j = 0; j < c; j++) {
                    field[i][j] = row.charAt(j);
                    if (field[i][j] == '|' || field[i][j] == '-') {
                        field[i][j] = 'x';
                    }
                }
            }
            char[][] result = null;
            if (allHittable(field, r, c)) {
                result = recurse(r, c, field, 0, 0);
            }
            String sol = "POSSIBLE";
            if (result == null) {
                sol = "IMPOSSIBLE";
                System.out.println("Case #" + task + ": " + sol);
                out.println("Case #" + task + ": " + sol);
            } else {
                System.out.println("Case #" + task + ": " + sol);
                out.println("Case #" + task + ": " + sol);
                printField(result, r, c, out);
            }
        }
 
        out.close();
        sc.close();
    }
 
    private void printField(char[][] field, int r, int c, PrintWriter out) {
        for (int i = 0; i < r; i++) {
            String s = "";
            for (int j = 0; j < c; j++) {
                s += field[i][j];
            }
            System.out.println(s);
            out.println(s);
        }
    }
 
    private char[][] recurse(int r, int c, char[][] field, int i, int j) {
        if (i == r) {
            boolean found = true;
            outerloop: for (int a = 0; a < r; a++) {
                for (int b = 0; b < c; b++) {
                    if (field[a][b] == '.' && !hitted(field, a, b, r, c)) {
                        found = false;
                        break outerloop;
                    }
                }
            }
            if (found) {
                return field;
            } else {
                return null;
            }
        } else if (j == c) {
            return recurse(r, c, field, i + 1, 0);
        }
        if (field[i][j] == 'x') {
            if (notHittingHorizontal(field, i, j, r, c)) {
                field[i][j] = '-';
                char[][] result = recurse(r, c, field, i, j + 1);
                if (result == null) {
                    field[i][j] = 'x';
                } else {
                    return result;
                }
            }
            if (notHittingVertical(field, i, j, r, c)) {
                field[i][j] = '|';
                char[][] result = recurse(r, c, field, i, j + 1);
                if (result == null) {
                    field[i][j] = 'x';
                } else {
                    return result;
                }
            }
            return null;
        } else {
            return recurse(r, c, field, i, j + 1);
        }
    }
 
    private boolean allHittable(char[][] field, int r, int c) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (field[i][j] == '.') {
                    if (!hittable(field, i, j, r, c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
 
    private boolean hittable(char[][] field, int i, int j, int r, int c) {
        int pos = j + 1;
        while (pos < c) {
            if (field[i][pos] == 'x') {
                return true;
            } else if (field[i][pos] == '#') {
                break;
            }
            pos++;
        }
 
        pos = j - 1;
        while (pos >= 0) {
            if (field[i][pos] == 'x') {
                return true;
            } else if (field[i][pos] == '#') {
                break;
            }
            pos--;
        }
 
        pos = i + 1;
        while (pos < r) {
            if (field[pos][j] == 'x') {
                return true;
            } else if (field[pos][j] == '#') {
                break;
            }
            pos++;
        }
 
        pos = i - 1;
        while (pos >= 0) {
            if (field[pos][j] == 'x') {
                return true;
            } else if (field[pos][j] == '#') {
                break;
            }
            pos--;
        }
 
        return false;
    }
 
    private boolean hitted(char[][] field, int i, int j, int r, int c) {
        int pos = j + 1;
        while (pos < c) {
            if (field[i][pos] == '-') {
                return true;
            } else if (field[i][pos] == '#') {
                break;
            }
            pos++;
        }
 
        pos = j - 1;
        while (pos >= 0) {
            if (field[i][pos] == '-') {
                return true;
            } else if (field[i][pos] == '#') {
                break;
            }
            pos--;
        }
 
        pos = i + 1;
        while (pos < r) {
            if (field[pos][j] == '|') {
                return true;
            } else if (field[pos][j] == '#') {
                break;
            }
            pos++;
        }
 
        pos = i - 1;
        while (pos >= 0) {
            if (field[pos][j] == '|') {
                return true;
            } else if (field[pos][j] == '#') {
                break;
            }
            pos--;
        }
 
        return false;
    }
 
    private boolean notHittingHorizontal(char[][] field, int i, int j, int r, int c) {
        int pos = j + 1;
        while (pos < c) {
            if (field[i][pos] == 'x') {
                return false;
            } else if (field[i][pos] == '#') {
                break;
            }
            pos++;
        }
        pos = j - 1;
        while (pos >= 0) {
            if (field[i][pos] == '-' || field[i][pos] == '|') {
                return false;
            } else if (field[i][pos] == '#') {
                break;
            }
            pos--;
        }
        return true;
    }
 
    private boolean notHittingVertical(char[][] field, int i, int j, int r, int c) {
        int pos = i + 1;
        while (pos < r) {
            if (field[pos][j] == 'x') {
                return false;
            } else if (field[pos][j] == '#') {
                break;
            }
            pos++;
        }
        pos = i - 1;
        while (pos >= 0) {
            if (field[pos][j] == '|' || field[pos][j] == '-') {
                return false;
            } else if (field[pos][j] == '#') {
                break;
            }
            pos--;
        }
        return true;
    }
 }
