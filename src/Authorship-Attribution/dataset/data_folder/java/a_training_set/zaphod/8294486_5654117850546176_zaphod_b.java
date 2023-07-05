import java.io.*;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class B
 {
    static int N, R, O, Y, G, B, V;
 
    public static void main(String[] args) throws IOException
    {
        String fileName = "B-small0";
        Scanner in = new Scanner(new File(fileName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
        int noOfCases = in.nextInt();
        for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
        {
            N = in.nextInt();
            R = in.nextInt();
            O = in.nextInt();
            Y = in.nextInt();
            G = in.nextInt();
            B = in.nextInt();
            V = in.nextInt();
 
            
            
            boolean possible = true;
            Unicorn[] unicorns = new Unicorn[3];
            unicorns[0] = new Unicorn('R', R);
            unicorns[1] = new Unicorn('Y', Y);
            unicorns[2] = new Unicorn('B', B);
            Arrays.sort(unicorns);
            StringBuilder answer = new StringBuilder();
            if (unicorns[0].frequency > unicorns[1].frequency
                    + unicorns[2].frequency)
                possible = false;
            else
            {
                while (unicorns[2].frequency > 0 && unicorns[0].frequency > unicorns[1].frequency)
                {
                    answer.append(unicorns[0].colour);
                    unicorns[0].frequency--;
                    answer.append(unicorns[1].colour);
                    unicorns[1].frequency--;
                    if (unicorns[0].frequency > unicorns[1].frequency)
                    {
                        answer.append(unicorns[0].colour);
                        unicorns[0].frequency--;
                        answer.append(unicorns[2].colour);
                        unicorns[2].frequency--;
                    }
                }
                
                while (unicorns[2].frequency > 0 )
                {
                    answer.append(unicorns[0].colour);
                    unicorns[0].frequency--;
                    answer.append(unicorns[1].colour);
                    unicorns[1].frequency--;
                    answer.append(unicorns[2].colour);
                    unicorns[2].frequency--;
 
                }
                while (unicorns[1].frequency > 0)
                {
                    answer.append(unicorns[0].colour);
                    answer.append(unicorns[1].colour);
                    unicorns[0].frequency--;
                    unicorns[1].frequency--;
                }
            }
 
            if (possible)
            {
                System.out.printf("Case #%d: %s%n", caseNo, answer.toString());
                out.printf("Case #%d: %s%n", caseNo, answer.toString());
            }
            else
            {
                System.out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
                out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
            }
 
        }
        in.close();
        out.close();
 
    }
 
 }
 
 class Unicorn implements Comparable<Unicorn>
 {
    char colour;
    int frequency;
 
    public Unicorn(char colour, int frequency)
    {
        this.colour = colour;
        this.frequency = frequency;
    }
 
    @Override
    public int compareTo(Unicorn other)
    {
        return other.frequency - this.frequency;
    }
 }
