package qualification.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.math.BigInteger;
 
 public class P1
 {
 	private static void calculate( BigInteger N, BufferedWriter bw, int n ) throws Exception
 	{
 		if ( N.compareTo( BigInteger.ZERO ) == 0 )
 		{
 			bw.append( "Case #"+n+": INSOMNIA\n" );
 			bw.flush();
 			return;
 		}
 		
 		boolean[] digits = new boolean[10];
 		
 		BigInteger count = BigInteger.ZERO;
 		while( true )
 		{
 			count = count.add( N );
 			for ( char c : count.toString().toCharArray() )
 				digits[c - '0'] = true;
 			
 			boolean allDigits = true;
 			for ( boolean digit : digits )
 				allDigits &= digit;
 			
 			if ( allDigits )
 			{
 				bw.append( "Case #"+n+": " +  count + "\n" );
 				bw.flush();
 				return;
 			}
 		}
 	}
 	
 	public static void main( String[] args ) throws Exception
 	{
 		File inputFile = new File( "src/qualification/p1/input.txt" );
 		FileReader fr = new FileReader( inputFile );
 		BufferedReader br = new BufferedReader( fr );
 		
 		int numOfTestCases = Integer.parseInt( br.readLine() );
 		
 		File outputFile = new File( "src/qualification/p1/output.txt" );
 		outputFile.delete();
 		outputFile.createNewFile();
 		FileWriter fw = new FileWriter( outputFile );
 		BufferedWriter bw = new BufferedWriter( fw );
 		
 		for ( int i = 0; i < numOfTestCases; i++ )
 		{
 			String data = br.readLine();
 			
 			BigInteger N = new BigInteger( data );
 			
 			calculate( N, bw, i+1 );
 		}
 		
 		bw.flush();
 		fw.flush();
 		
 		bw.close();
 		fw.close();
 	}
 }
