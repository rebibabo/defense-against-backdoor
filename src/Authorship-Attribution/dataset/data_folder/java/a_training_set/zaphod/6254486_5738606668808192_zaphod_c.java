import java.io.*;
 import java.math.BigInteger;
 import java.util.Scanner;
 
 public class C
 {
 
    
    static long findDivisor(long number)
    {
        
        if (number == 2 || number == 3)
            return -1;
        if (number % 2 == 0)
            return 2;
        if (number % 3 == 0)
            return 3;
 
        
        
        int increment = 4;
        double sqrt = Math.sqrt(number);
        
        for (long divisor = 5; divisor <= sqrt; divisor += increment)
        {
            if (number % divisor == 0)
                return divisor;
        }
        return -1;
    }
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "C-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
 
            int N = in.nextInt();
            int J = in.nextInt();
 
            System.out.printf("Case #%d:%n", caseNo);
            out.printf("Case #%d:%n", caseNo);
            String jamcoin = "100000";
            long numberInBaseTwo = Long.parseLong(jamcoin, 2);
            
            for (int coin = 1; coin <= J; coin++)
            {
        
                long[] divisors = null;
                boolean isGood = false;
                while (!isGood)
                {
                    isGood = true;
                    numberInBaseTwo++;
                    jamcoin = Long.toBinaryString(numberInBaseTwo);
                    if (jamcoin.length() != N ||
                            jamcoin.charAt(0) != '1' ||
                            jamcoin.charAt(jamcoin.length() - 1) != '1')
                        isGood = false;
                }
                isGood = false;
                while (!isGood)
                {
                    System.out.println(numberInBaseTwo + " " +Long.toBinaryString(numberInBaseTwo));
                    divisors = new long[11];
                    isGood = true;
                    for (int base = 2; base <= 10 && isGood; base++)
                    {
                        long numberInBase = Long.parseLong(jamcoin, base);
                        divisors[base] = findDivisor(numberInBase);
                        if (divisors[base] == -1)
                            isGood = false;
                    
                    }
                    if (!isGood)
                    {
                        int tryCount = 0;
                        while (!isGood && tryCount < 20)
                        {
                            tryCount++;
                            isGood = true;
                            numberInBaseTwo++;
                            jamcoin = Long.toBinaryString(numberInBaseTwo);
                            if (jamcoin.length() != N ||
                                    jamcoin.charAt(0) != '1' ||
                                    jamcoin.charAt(jamcoin.length() - 1) != '1')
                                isGood = false;
                        }
                         isGood = false;
                    }
                }
                System.out.print(jamcoin);
                out.print(jamcoin);
                for (int base = 2; base <= 10; base++)
                {
                    System.out.print(" " + divisors[base]);
                    out.print(" " +divisors[base]);
                }
                System.out.println();
                out.println();
            }
        }
        in.close();
        out.close();
 
    }
 }
