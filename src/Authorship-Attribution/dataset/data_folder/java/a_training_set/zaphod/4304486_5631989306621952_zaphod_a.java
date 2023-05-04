import java.io.*;
 import java.util.Scanner;
 
 public class A
 {
 
    static int N;
    static String S;
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            S = in.next();
            StringBuilder winWord = new StringBuilder(S.length());
            winWord.append(S.charAt(0));
            for (int i = 1; i < S.length(); i++)
            {
                char nextLetter = S.charAt(i);
                if (nextLetter < winWord.charAt(0))
                    winWord.append(nextLetter);
                else
                    winWord.insert(0, nextLetter);
            }
                        
            System.out.printf("Case #%d: %s%n", caseNo, winWord);
            out.printf("Case #%d: %s%n", caseNo, winWord);
        }
        in.close();
        out.close();
    }
 }
