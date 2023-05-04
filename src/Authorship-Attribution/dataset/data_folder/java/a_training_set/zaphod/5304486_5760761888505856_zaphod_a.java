import java.io.*;
 import java.util.Scanner;
 
 public class A
 {
    static int R, C;
    static char[][] grid;
 
    static char firstCh(char[] row)
    {
        for (int i = 0; i < row.length; i++)
            if (row[i] != '?')
                return row[i];
        return ' ';
    }
 
    static char firstColCh(char[][] grid, int col)
    {
        for (int row = 0; row < grid.length; row++)
            if (grid[row][col] != '?')
                return grid[row][col];
        return ' ';
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            R = in.nextInt();
            C = in.nextInt();
            grid = new char[R][C];
            boolean[] allQuestions = new boolean[R];
            for (int row = 0; row < R; row++)
            {
                grid[row] = in.next().toCharArray();
                allQuestions[row] = true;
                for (int col = 0; col < C; col++)
                    if (grid[row][col] != '?')
                        allQuestions[row] = false;
            }
 
            
            for (int row = 0; row < R; row++)
            {
                if (!allQuestions[row])
                {
                    char fillCh = firstCh(grid[row]);
                    for (int col = 0; col < C; col++)
                    {
                        if (grid[row][col] == '?')
                            grid[row][col] = fillCh;
                        else
                            fillCh = grid[row][col];
                    }
                }
            }
            
            
            for (int col = 0; col < C; col++)
            {
                char fillCh = firstColCh(grid, col);
                for (int row = 0; row < R; row++)
                {
                    if (grid[row][col] == '?')
                        grid[row][col] = fillCh;
                    else
                        fillCh = grid[row][col];
                }
            }
 
            System.out.printf("Case #%d:%n", caseNo);
            out.printf("Case #%d:%n", caseNo);
            for (int row = 0; row < R; row++)
            {
                for (int col = 0; col < C; col++)
                {
                    System.out.print(grid[row][col]);
                    out.print(grid[row][col]);
                }
                System.out.println();
                out.println();
            }
        }
        in.close();
        out.close();
    }
 }
