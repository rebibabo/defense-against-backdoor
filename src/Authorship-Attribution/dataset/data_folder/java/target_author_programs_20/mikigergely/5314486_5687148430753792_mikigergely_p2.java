package round2.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P2
 {
 	private static void calculate( int N, int C, int M, int[][] T, BufferedWriter bw, int n ) throws Exception
 	{
 		int[][] tickets = new int[N][2];
 		for ( int i = 0; i < M; i++ )
 			tickets[T[i][0]-1][T[i][1]-1]++;
 
 		int ticketNum1 = 0;
 		int ticketNum2 = 0;
 		for ( int i = 0; i < N; i++ )
 		{
 			int t1 = tickets[i][0];
 			int t2 = tickets[i][1];
 			
 			ticketNum1 += t1;
 			ticketNum2 += t2;
 		}
 		
 		BipartiteMatching bm = new BipartiteMatching();
 		bm.n = ticketNum1;
 		bm.m = ticketNum2;
 		bm.graph = new boolean[ticketNum2][ticketNum1];
 		bm.seen = new boolean[ticketNum1];
 		bm.matchL = new int[ticketNum2];
 		bm.matchR = new int[ticketNum1];
 		
 		for ( int i = 0; i < ticketNum1; i++ )
 			for ( int j = 0; j < ticketNum2; j++ )
 				bm.graph[j][i] = true;
 		
 		int t1Run = 0;
 		int t2Run = 0;
 		for ( int i = 0; i < N; i++ )
 		{
 			int t1 = tickets[i][0];
 			int t2 = tickets[i][1];
 			
 			for ( int j = t1Run; j < t1Run + t1; j++ )
 				for ( int k = t2Run; k < t2Run + t2; k++ )
 					bm.graph[k][j] = false;
 			
 			t1Run += t1;
 			t2Run += t2;
 		}
 		
 		int pairs = bm.maximumMatching();
 		
 		int rides = pairs;
 		ticketNum1 -= rides;
 		ticketNum2 -= rides;
 		
 		int common = Math.min( ticketNum1, ticketNum2 );
 		int total = Math.max( ticketNum1, ticketNum2 );
 		rides += total;
 		
 		int promotions = common; 
 		
 		
 		int unmatched11 = 0;
 		for ( int i = 0; i < tickets[0][0]; i++ )
 			if ( bm.matchR[i] == -1 )
 				unmatched11++;
 		int unmatched12 = 0;
 		for ( int i = 0; i < tickets[0][1]; i++ )
 			if ( bm.matchL[i] == -1 )
 				unmatched12++;
 		if ( unmatched11 > 0 && unmatched12 > 0 )
 		{
 			int unmatchedCommon1 = Math.min( unmatched11, unmatched12 );
 			promotions -= unmatchedCommon1;
 			rides += unmatchedCommon1;
 		}
 		
 		bw.append( "Case #"+n+": " + rides + " " + promotions + "\n" );
 		bw.flush();
 	}
 	
 	public static void main( String[] args ) throws Exception
 	{
 		File inputFile = new File( "src/" + P2.class.getPackage().getName().replace( ".", "/" ) + "/input.txt" );
 		FileReader fr = new FileReader( inputFile );
 		BufferedReader br = new BufferedReader( fr );
 		
 		File outputFile = new File( "src/" + P2.class.getPackage().getName().replace( ".", "/" ) + "/output.txt" );
 		outputFile.delete();
 		outputFile.createNewFile();
 		FileWriter fw = new FileWriter( outputFile );
 		BufferedWriter bw = new BufferedWriter( fw );
 		
 		int numOfTestCases = Integer.parseInt( br.readLine() );
 		for ( int i = 0; i < numOfTestCases; i++ )
 		{
 			String[] data = br.readLine().split( " " );
 			
 			int N = Integer.parseInt( data[0] );
 			int C = Integer.parseInt( data[1] );
 			int M = Integer.parseInt( data[2] );
 			
 			int[][] T = new int[M][2];
 			for ( int j = 0; j < M; j++ )
 			{
 				data = br.readLine().split( " " );
 				T[j][0] = Integer.parseInt( data[0] );
 				T[j][1] = Integer.parseInt( data[1] );
 			}
 			
 			calculate( N, C, M, T, bw, i+1 );
 		}
 		
 		bw.flush();
 		fw.flush();
 		
 		br.close();
 		bw.close();
 		fw.close();
 	}
 }
