import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
    }
    
    public Main() throws IOException {
        
        Scanner sc = new Scanner(new File("A-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new File("A-small-attempt1.out"));
        
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            
            char[][] array = new char[r][c];
            for (int i = 0; i < r; i++) {
                String s = sc.next();
                for (int j = 0; j < c; j++) {
                    array[i][j] = s.charAt(j);
                }
            }
            
            int sol = 0;
            
            boolean[] done1 = new boolean[c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    char x = array[i][j];
                    if (x == '^' && !done1[j]) sol++;
                    if (x != '.') done1[j] = true;
                }
            }
            
            boolean[] done2 = new boolean[c];
            for (int i = r-1; i >= 0; i--) {
                for (int j = 0; j < c; j++) {
                    char x = array[i][j];
                    if (x == 'v' && !done2[j]) sol++;
                    if (x != '.') done2[j] = true;
                }
            }
            
            boolean[] done3 = new boolean[r];
            for (int j = 0; j < c; j++) {
                for (int i = 0; i < r; i++) {
                    char x = array[i][j];
                    if (x == '<' && !done3[i]) sol++;
                    if (x != '.') done3[i] = true;
                }
            }
            
            boolean[] done4 = new boolean[r];
            for (int j = c-1; j >= 0; j--) {
                for (int i = 0; i < r; i++) {
                    char x = array[i][j];
                    if (x == '>' && !done4[i]) sol++;
                    if (x != '.') done4[i] = true;
                }
            }
            
            int[] rowSum = new int[r];
            int[] colSum = new int[c];
            for (int i = 0; i < r; i++) {
                rowSum[i] = 0;
                for (int j = 0; j < c; j++) {
                    char x = array[i][j];
                    if (x != '.') rowSum[i]++;
                }
            }
            for (int j = 0; j < c; j++) {
                colSum[j] = 0;
                for (int i = 0; i < r; i++) {
                    char x = array[i][j];
                    if (x != '.') colSum[j]++;
                }
            }
            
            boolean problem = false;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    char x = array[i][j];
                    if (x != '.' && colSum[j] == 1 && rowSum[i] == 1) problem = true;
                }
            }
            
            String sol1 = ""+sol;
            if (problem) sol1 = "IMPOSSIBLE";
            
            String solution = "Case #" + task + ": " + sol1;
            System.out.println(solution);
            out.println(solution);
        }
        
        out.close();
        sc.close();
    }
 }