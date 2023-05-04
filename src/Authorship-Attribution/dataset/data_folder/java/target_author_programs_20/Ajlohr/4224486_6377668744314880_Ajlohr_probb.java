import java.awt.geom.Line2D;
 import java.awt.geom.Point2D;
 import java.util.Scanner;
 
 
 public class ProbB {
 	public static void main(String[] args)
 	{
 
 	Scanner sc = new Scanner(System.in);
 	int numcases = sc.nextInt();
 	for(int cas  = 1;cas <= numcases;cas++ )
 	{
 		System.out.println("Case #"+cas+": ");
 		int numtrees =sc.nextInt();
 		Point2D.Double[] trees= new Point2D.Double[numtrees];
 		for(int i=0;i<numtrees;i++)
 		{
 			long posx = sc.nextLong();
 			long posy = sc.nextLong();
 			trees[i] = new Point2D.Double(posx,posy);
 		}
 		for(int i=0;i<numtrees;i++)
 		{
 		int minbad=Integer.MAX_VALUE;
 		for(int j=0;j<numtrees;j++)
 		{
 			if (j==i)
 				continue;
 			Line2D ell = new Line2D.Double(trees[j], trees[i]);
 			int negcount = 0;
 			int poscount = 0;
 			for(int k=0;k<numtrees;k++)
 			{
 				if((k==i)||(k==j))
 					continue;
 				if(ell.ptLineDistSq(trees[k])>0){
 				if((ell.relativeCCW(trees[k])>0))
 					poscount++;
 				if((ell.relativeCCW(trees[k])<0))
 					negcount++;			
 				}
 				
 			}
 			if(poscount<minbad)
 				minbad=poscount;
 			if(negcount<minbad)
 				minbad=negcount;			
 			
 		}
 		if(numtrees==1)
 			System.out.println(0);
 		else
 		System.out.println(minbad);
 		}
 		
 		
 }}}
