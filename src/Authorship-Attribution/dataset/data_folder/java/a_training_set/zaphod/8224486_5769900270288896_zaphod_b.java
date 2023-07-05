import java.io.*;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class B
 {
    static int R, C, N;
    static int unhappiness;
    static int [][] grid;
    public static void main(String[] args) throws IOException
    {
        String fileName = "B-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            R = in.nextInt();
            C = in.nextInt();
            N = in.nextInt();
 
            
            if ((R <= 1 && C <=1)|| N <= Math.ceil(R*C/2.0))
                unhappiness = 0;
            else
            {
                
                
                grid = new int [R][C];
                int base = 0;
                for (int row = 0; row < grid.length; row++)
                {
                    for (int col = 0; col < grid[0].length; col++)
                        if ((row + col)% 2==1)
                            grid[row][col]= -1;
                        else
                        {
                            grid[row][col]= 0;
                            base++;
                        }
                            
                }
                ArrayList<Integer> unhappy = new ArrayList<Integer>();
                
                for (int row = 0; row < grid.length; row++)
                {
                    for (int col = 0; col < grid[0].length; col++)
                        if (grid[row][col]== -1)
                        {
                            int unhappyNext = 4;
                            if (row == 0)
                                unhappyNext--;
                                if (row == grid.length-1)
                                    unhappyNext--;
                                if (col == 0)
                                    unhappyNext--;
                                    if (col == grid[0].length-1)
                                        unhappyNext--;
                                    unhappy.add(unhappyNext);
                        }
                }
                
                Collections.sort(unhappy);
                unhappiness = 0;
            
            
            
                for (int i = 0; i < unhappy.size() && base < N; i++)
                {
                    unhappiness += unhappy.get(i);
                    base++;
                }
                int firstUnhappiness = unhappiness;
                
                
                
                base = 0;
                for (int row = 0; row < grid.length; row++)
                {
                    for (int col = 0; col < grid[0].length; col++)
                        if ((row + col)% 2==0)
                            grid[row][col]= -1;
                        else
                        {
                            grid[row][col]= 0;
                            base++;
                        }
                            
                }
                unhappy = new ArrayList<Integer>();
                
                for (int row = 0; row < grid.length; row++)
                {
                    for (int col = 0; col < grid[0].length; col++)
                        if (grid[row][col]== -1)
                        {
                            int unhappyNext = 4;
                            if (row == 0)
                                unhappyNext--;
                                if (row == grid.length-1)
                                    unhappyNext--;
                                if (col == 0)
                                    unhappyNext--;
                                    if (col == grid[0].length-1)
                                        unhappyNext--;
                                    unhappy.add(unhappyNext);
                        }
                }
                
                Collections.sort(unhappy);
                unhappiness = 0;
            
            
            
                for (int i = 0; i < unhappy.size() && base < N; i++)
                {
                    unhappiness += unhappy.get(i);
                    base++;
                }
                
                unhappiness = Math.min(unhappiness, firstUnhappiness);
            }
            
 
            System.out.printf("Case #%d: %d%n", caseNo, unhappiness);
            out.printf("Case #%d: %d%n", caseNo, unhappiness);
        }
        in.close();
        out.close();
 
    }
 
 }
 
