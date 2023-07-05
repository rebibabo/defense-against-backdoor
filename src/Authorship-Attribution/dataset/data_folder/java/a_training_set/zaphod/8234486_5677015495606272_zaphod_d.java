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
 
            int R = in.nextInt();
            int C = in.nextInt();
            
            
            
            int noOfDrums = 1;
            if (R % 3 == 0)
                noOfDrums++;
 
            System.out.printf("Case #%d: %s%n", caseNo,     noOfDrums);
            out.printf("Case #%d: %s%n", caseNo, noOfDrums);
    
 
        }
        in.close();
        out.close();
    }
 }
