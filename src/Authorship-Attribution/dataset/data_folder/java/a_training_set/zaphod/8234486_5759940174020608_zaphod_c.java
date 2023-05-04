import java.io.*;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class C
 {
 
     static int min;
     static HashSet<String> [] newWords;
     
     static void tryAll(HashSet<String> eSet, HashSet<String> fSet, int nextSet)
     {
        HashSet<String> both = new HashSet<String>(eSet);
            both.retainAll(fSet);
        if (nextSet == newWords.length)
        {
            min = Math.min(both.size(), min);
            return;
        }
        
        if (both.size() > min)
            return;
    
        HashSet<String> eSetCopy = new HashSet<String>(eSet);
        eSetCopy.addAll(newWords[nextSet]);
        HashSet<String> fSetCopy = new HashSet<String>(fSet);
        fSetCopy.addAll(newWords[nextSet]);
        tryAll(eSetCopy, fSet, nextSet + 1);
        tryAll(eSet, fSetCopy, nextSet + 1);
     }
    
    public static void main(String[] args) throws IOException
    {
        String fileName = "C-small1";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            min = Integer.MAX_VALUE;
            int N = in.nextInt();
            in.nextLine();
            String english = in.nextLine();
            HashSet<String> eSet = new HashSet<String>();
            Scanner parse = new Scanner(english);
            while (parse.hasNext())
                eSet.add(parse.next());
            String french = in.nextLine();
            HashSet<String> fSet = new HashSet<String>();
            parse = new Scanner(french);
            while (parse.hasNext())
                fSet.add(parse.next());
 
 
 
            newWords = new HashSet[N-2];
            for (int i = 0; i < N-2;i++)
            {
                String line = in.nextLine();
                newWords[i] = new HashSet<String>();
                 parse = new Scanner(line);
                while (parse.hasNext())
                    newWords[i].add(parse.next());
            }
            
            tryAll(eSet, fSet, 0);
 
            System.out.printf("Case #%d: %s%n", caseNo, min);
            out.printf("Case #%d: %s%n", caseNo, min);
 
        }
        in.close();
        out.close();
    }
 }
