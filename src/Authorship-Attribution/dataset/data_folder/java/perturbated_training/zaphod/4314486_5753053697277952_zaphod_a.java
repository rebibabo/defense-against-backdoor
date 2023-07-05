import java.io.*;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class A
 {
    static int noOfParties;
    static PriorityQueue<Party> parties;
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "A-small1";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            noOfParties = in.nextInt();
            parties = new PriorityQueue<Party>();
            char next = 'A';
            int total = 0;
            for (int i = 0; i < noOfParties; i++)
            {
                int count = in.nextInt();
                parties.add(new Party(next, count));
                next++;
                total += count;
            }
 
            System.out.printf("Case #%d:", caseNo);
            out.printf("Case #%d:", caseNo);
        
            while (!parties.isEmpty())
            {
                System.out.print(" ");
                out.print(" ");
                Party nextParty = parties.remove();
                System.out.print(nextParty.name);
                out.print(nextParty.name);
                nextParty.count--;
                total--;
                if (nextParty.count > 0)
                    parties.add(nextParty);
 
        
                if (!parties.isEmpty())
                {
                    nextParty = parties.remove();
                    nextParty.count--;
                    total--;
 
                    Party partyLeft = parties.peek();
                    if (partyLeft != null && partyLeft.count >= nextParty.count && partyLeft.count * 2 > total)
                    {
                        nextParty.count++;
                        total++;
                        parties.add(nextParty);
                    }
                    else
                    {
                        if (nextParty.count > 0)
                            parties.add(nextParty);
                        System.out.print(nextParty.name);
                        out.print(nextParty.name);
                    }
                }
 
            }
            System.out.println();
            out.println();
 
        }
        in.close();
        out.close();
 
    }
 
 }
 
 class Party implements Comparable<Party>
 {
    char name;
    int count;
 
    public Party(char name, int count)
    {
        this.name = name;
        this.count = count;
    }
 
    @Override
    public int compareTo(Party other)
    {
        return other.count - this.count;
    }
 
    public String toString()
    {
        return "" + name + ":" + count;
    }
 }
