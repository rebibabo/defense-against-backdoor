import java.io.*;
 import java.util.Collections;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class B
 {
    static String pancakes;
    static boolean [] faceUp;
 
    static void flip(int pancakesToFlip)
    {
        for (int i = 0; i < pancakesToFlip; i++)
        {
            faceUp[i]= !faceUp[i];
        }
    }
    
    static int lastFaceDownPos()
    {
        int pos = faceUp.length-1;
        while (pos >= 0 && faceUp[pos])
            pos--;
        return pos+1;
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "B-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            pancakes = in.next();
            faceUp = new boolean[pancakes.length()];
            for (int i = 0; i < pancakes.length(); i++)
            {
                if (pancakes.charAt(i)== '+')
                    faceUp[i] = true;
            }
            
            
            
            
            
            int noOfFlips = 0;
            int flipPos = lastFaceDownPos();
            while (flipPos > 0)
            {
                flip(flipPos);
                noOfFlips++;
                flipPos = lastFaceDownPos();
            }
            System.out.printf("Case #%d: %d%n", caseNo, noOfFlips);
            out.printf("Case #%d: %d%n", caseNo, noOfFlips);
        }
        in.close();
        out.close();
 
    }
 
 }
