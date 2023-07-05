import java.io.*;
 import java.util.Scanner;
 
 public class C
 {
    static Quat[] targets = { Quat.I, Quat.J, Quat.K };
    static String str;
    static int[][][] memo = new int[3][8][10001];
 
    
    public static boolean good(int nextTarget, Quat start,
            int startIndex)
    {
        
        
        int prevResult = memo[nextTarget][start.index()][startIndex];
        
        if (prevResult != 0)
        {
            return prevResult == 1;
        }
        
        
        if (start.equals(targets[nextTarget]))
        {
            
            
            if (nextTarget == 2 && startIndex >= str.length())
                return true;
            if (nextTarget < 2)
            {
                if (good(nextTarget + 1, Quat.ONE, startIndex))
                {
                    memo[nextTarget+1][Quat.ONE.index()][startIndex] = 1;
                    return true;
                }
                else
                {
                    memo[nextTarget+1][Quat.ONE.index()][startIndex] = -11;
 
                }
            }
        }
        if (startIndex >= str.length())
        {
            memo[nextTarget][start.index()][startIndex] = -1;
            return false;
        }
        start = start.multiply(str.charAt(startIndex));
 
        boolean found = good(nextTarget, start, startIndex + 1);
 
        
        if (found)
        {
            memo[nextTarget][start.index()][startIndex+1] = 1;
            return true;
        }
        else
        {
            memo[nextTarget][start.index()][startIndex+1] = -1;
            return false;
        }
 
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "C-small2";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
        
        int noOfCases = in.nextInt();
        
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            for (int i = 0; i < memo.length; i++)
                for (int j = 0; j < memo[i].length; j++)
                    for (int k = 0; k < memo[i][j].length; k++)
                        memo[i][j][k] = 0;
            int L = in.nextInt();
            int X = in.nextInt();
            String base = in.next();
            StringBuilder build = new StringBuilder();
            for (int i = 0; i < X; i++)
                build.append(base);
 
            str = build.toString();
            
            if (good(0, Quat.ONE, 0))
            {
                System.out.printf("Case #%d: YES%n", caseNo);
                out.printf("Case #%d: YES%n", caseNo);
            }
            else
            {
                System.out.printf("Case #%d: NO%n", caseNo);
                out.printf("Case #%d: NO%n", caseNo);
            }
 
        }
        in.close();
        out.close();
 
    }
 }
 
 class Quat
 {
    final static Quat I = new Quat('i', 1);
    final static Quat J = new Quat('j', 1);
    final static Quat K = new Quat('k', 1);
    final static Quat ONE = new Quat('1', 1);
    char digit;
    int sign;
 
    public Quat(char digit, int sign)
    {
        this.digit = digit;
        this.sign = sign;
    }
 
    public Quat multiply(char digit)
    {
        if (this.digit == '1')
            return new Quat(digit, this.sign);
        if (this.digit == digit)
            return new Quat('1', -this.sign);
        if (this.digit == 'i')
        {
            if (digit == 'j')
                return new Quat('k', this.sign);
            else
                return new Quat('j', -this.sign);
        }
        if (this.digit == 'j')
        {
            if (digit == 'i')
                return new Quat('k', -this.sign);
            else
                return new Quat('i', this.sign);
        }
        if (digit == 'i')
            return new Quat('j', this.sign);
        else
            return new Quat('i', -this.sign);
 
    }
 
    public String toString()
    {
        return String.format("%c%c", sign < 0 ? '1' : ' ', digit);
    }
 
    public boolean equals(Quat other)
    {
        return digit == other.digit && sign == other.sign;
    }
 
    public int index()
    {
        int index = "1ijk".indexOf(digit);
        if (sign == 1)
            return index;
        return index + 4;
    }
 
 }
