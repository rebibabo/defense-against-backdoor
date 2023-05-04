package Qualification;
 
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProbC {
 	public static void main(String[] args)
 	{
 		Scanner sc = new Scanner(System.in);
 		long t = sc.nextLong();
 		sc.nextLine();
 		for(int curcase=1;curcase<=t;curcase++)
 		{
 			int N = sc.nextInt();
 			int L = sc.nextInt();
 			sc.nextLine();
 			boolean[] stalls = new boolean[N+2];
 			stalls[0] = true;
 			stalls[N+1] = true;
 			int bestpos=0;
 			int bestmin=0;
 			int bestmax=0;
 			for(int i=0;i<L;i++)
 			{
 				bestmin=0;
 				bestmax=0;
 				for(int pos = 1;pos<N+1;pos++)
 				{
 					if(stalls[pos])
 						continue;
 					int left = Left(stalls,pos);
 					int right = Right(stalls,pos);
 					if(Math.min(left,right)>bestmin)
 					{
 						bestpos = pos;
 						bestmin = Math.min(left,right);
 						bestmax = Math.max(left, right);
 					}
 					if(Math.min(left,right)==bestmin)
 					{
 						if(Math.max(left,right)>bestmax)
 						{
 							bestpos = pos;
 							bestmin = Math.min(left,right);
 							bestmax = Math.max(left, right);
 						}						
 					}
 				}
 				stalls[bestpos] = true;
 				
 			}
 			
 			System.out.println("Case #"+curcase+": "+bestmax+" "+bestmin);
 		}
 		
 	}
 	public static int Left(boolean[] b,int pos)
 	{
 		int ret = 0;
 		while(!b[pos-ret]){ret++;}
 		return ret-1;
 	}
 	public static int Right(boolean[] b,int pos)
 	{
 		int ret = 0;
 		while(!b[pos+ret]){ret++;}
 		return ret-1;
 	}
 	
 }
