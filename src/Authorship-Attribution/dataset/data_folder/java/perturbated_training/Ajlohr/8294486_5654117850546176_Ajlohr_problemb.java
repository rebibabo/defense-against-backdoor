package Round1B;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProblemB {
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new FileInputStream("B-small-attempt2.in"));
    
        PrintStream out = new PrintStream(new FileOutputStream("out1.txt"));
        long t = sc.nextLong();
        sc.nextLine();
        LOOP2:
        for(int curcase=1;curcase<=t;curcase++)
        {
            int N =sc.nextInt();
            int[] ROYGBV = new int[6];
            for(int i=0;i<6;i++)
            {
                ROYGBV[i] = sc.nextInt();
            }
            int[] RYB = new int[3];
            if((ROYGBV[0]==ROYGBV[3])&&(ROYGBV[0]+ROYGBV[3]==N))
            {
                out.print("Case #"+curcase+": ");
                for(int i=0;i<ROYGBV[0];i++)
                {
                    out.print("RG");
                }
                out.println();
                continue LOOP2;
            }
            if((ROYGBV[0]-1 < ROYGBV[3])&&(ROYGBV[3]>0))
            {
            out.println("Case #"+curcase+": IMPOSSIBLE");
            continue LOOP2;
            }
            if((ROYGBV[2]==ROYGBV[5])&&(ROYGBV[2]+ROYGBV[5]==N))
            {
                out.print("Case #"+curcase+": ");
                for(int i=0;i<ROYGBV[2];i++)
                {
                    out.print("YV");
                }
                out.println();
                continue LOOP2;
            }
            if((ROYGBV[2]-1 < ROYGBV[5])&&(ROYGBV[5]>0))
            {
            out.println("Case #"+curcase+": IMPOSSIBLE");
            continue LOOP2;
            }
            if((ROYGBV[4]==ROYGBV[1])&&(ROYGBV[4]+ROYGBV[1]==N))
            {
                out.print("Case #"+curcase+": ");
                for(int i=0;i<ROYGBV[4];i++)
                {
                    out.print("BO");
                }
                out.println();
                continue LOOP2;
            }
            if((ROYGBV[4]-1 < ROYGBV[1])&&(ROYGBV[1]>0))
            {
            out.println("Case #"+curcase+": IMPOSSIBLE");
            continue LOOP2;
            }
            RYB[0] = ROYGBV[0]-ROYGBV[3];
            RYB[1] = ROYGBV[2]-ROYGBV[5];
            RYB[2] = ROYGBV[4]-ROYGBV[1];
            char[] prims=  {'R','Y','B'};
            char[] comps=  {'G','V','O'};
            boolean[] notfirstdone = new boolean[3];
            char[] assignment = new char[N];
                
            LOOP:
            for(int i=0;i<N;){
                
                int[] order = ord(RYB);
                if(i==0){
                for(int j=2;j>=0;j--)
                {
                    if(RYB[order[j]]>0)
                    {
                    assignment[0] = prims[order[j]];
                    i++;
                    RYB[order[j]]--;
                    continue LOOP;
                    }
                }
                }
                for(int j=0;j<3;j++)
                {
                    assert(RYB[0]+RYB[1]+RYB[2]==6);
                if((RYB[order[j]]>0)&&(assignment[(N+i-1)%N]!=prims[order[j]])&&(assignment[(N+i+1)%N]!=prims[order[j]]))
                {
                    assignment[i] = prims[order[j]];
                    RYB[order[j]]--;
                    i++;
                    if(!notfirstdone[order[j]])
                    {
                        for(int k=0;k<ROYGBV[(order[j]*2+3)%6];k+=2)
                        {
                            assignment[i+k] = comps[order[j]];
                            assignment[i+k+1]= prims[order[j]];
                        }
                        i+=2*ROYGBV[(order[j]*2+3)%6];
                        notfirstdone[order[j]]=true;
                    }
                    continue LOOP;
                }
                }
                if(i==N)
                    break LOOP;
                System.out.println(curcase+"failed on "+ROYGBV[0]+" "+ROYGBV[2]+" "+ROYGBV[4]);
                if(curcase==98){
                    System.out.println(new String(assignment));
                    System.out.println(RYB[0]+" "+RYB[1]+" "+RYB[2]);
                }
                out.println("Case #"+curcase+": IMPOSSIBLE");
                continue LOOP2;
            }
            assert(RYB[0] == 0);
            assert(RYB[1] == 0);
            assert(RYB[2] == 0);
            out.println("Case #"+curcase+": "+new String(assignment));
        }
        
    }
    public static boolean tryassign(int n,int[] RYB,int[] assignment)
    {
        System.out.println(n);
        if((n>1)&&(assignment[n-1]==assignment[n]))
            return false;
        if(n==assignment.length-1)
        {
            if(assignment[0]!=assignment[n])
            return true;
            else
            return false;
        }
        for(int j=0;j<3;j++)
        {
            if(RYB[j]>0)
            {
            assignment[n] = j;
            RYB[j]--;
            if(tryassign(n+1,RYB,assignment))
            {
                return true;
            }
            RYB[j]++;
            }
        }
        return false;
    }
    
    public static int[] ord(int[] RYB)
    {
        int[] order = new int[3];
        if(RYB[0]>=Math.max(RYB[1],RYB[2]))
        {
            order[0] = 0;
            if(RYB[1]>=RYB[2])
            {
                order[1] = 1;
                order[2] = 2;
            }else
            {
                order[1] = 2;
                order[2] = 1;
            }
            return order;
        }
        if(RYB[1]>=Math.max(RYB[0],RYB[2]))
        {
            order[0] = 1;
            if(RYB[0]>=RYB[2])
            {
                order[1] = 0;
                order[2] = 2;
            }else
            {
                order[1] = 2;
                order[2] = 0;
            }
            return order;
        }
        if(RYB[2]>=Math.max(RYB[1],RYB[0]))
        {
            order[0] = 2;
            if(RYB[1]>=RYB[0])
            {
                order[1] = 1;
                order[2] = 0;
            }else
            {
                order[1] = 0;
                order[2] = 1;
            }
        }
        return order;
    }
 }
