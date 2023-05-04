import java.io.*;
 import java.util.Scanner;
 
 public class D
 {
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "D-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            int X = in.nextInt();
            int R = in.nextInt();
            int C = in.nextInt();
            int totalBlocks = R * C;
 
            
            String winner = "XXX";
            
            if (totalBlocks % X != 0 || totalBlocks < X)
                winner = "RICHARD";
            
            else if (X == 1 || X == 2)
                winner = "GABRIEL";
            else if (X == 3)
            {
                
                
                
                
                if (R == 1 || C == 1)
                    winner = "RICHARD";
                else if ((R == 3 && C == 2) || (R == 2 && C == 3))
                    winner = "GABRIEL";
                else if ((R == 3 && C == 3))
                    winner = "GABRIEL";
                else if ((R == 3 && C == 4) || (R == 4 && C == 3))
                    winner = "GABRIEL";
            }
            else if (X == 4)
            {
                
                
                if (R == 1 || C == 1)
                    winner = "RICHARD";
                
                else if (R == 2 && C == 2)
                    winner = "RICHARD";
                
                
                else if ((R == 3 && C == 4) || (R == 4 && C == 3))
                    winner = "GABRIEL";
                
                else if ((R == 2 && C == 4) || (R == 4 && C == 2))
                    winner = "RICHARD";
                
                else if (R == 4 && C == 4)
                    winner = "GABRIEL";
            }
 
            System.out.printf("Case #%d: %s%n", caseNo, winner);
            out.printf("Case #%d: %s%n", caseNo, winner);
        }
        in.close();
        out.close();
 
    }
 
 }
