package round2.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.math.BigDecimal;
 import java.math.RoundingMode;
 
 public class P2Small
 {
    private static void calculate( int N, BigDecimal V, BigDecimal X, BigDecimal[] ri, BigDecimal[] ci, BufferedWriter bw, int n ) throws IOException
    {
        String solution = null;
        if ( N == 1 )
        {
            if ( !ci[0].equals( X ) )
                solution = "IMPOSSIBLE";
            else
                solution = V.divide( ri[0], 20, RoundingMode.HALF_UP ).toPlainString();
        }
        if ( N == 2 )
        {
            BigDecimal minTime = null;
            if ( ci[0].equals( X ) )
            {
                BigDecimal minTimeCandidate = V.divide( ri[0], 20, RoundingMode.HALF_UP );
                if ( minTime == null || minTime.compareTo( minTimeCandidate ) > 0 )
                    minTime = minTimeCandidate;
            }
            if ( ci[1].equals( X ) )
            {
                BigDecimal minTimeCandidate = V.divide( ri[1], 20, RoundingMode.HALF_UP );
                if ( minTime == null || minTime.compareTo( minTimeCandidate ) > 0 )
                    minTime = minTimeCandidate;
            }
            if ( ci[0].equals( X ) && ci[1].equals( X ) )
            {
                BigDecimal minTimeCandidate = V.divide( ri[1].add(ri[0]), 20, RoundingMode.HALF_UP );
                if ( minTime == null || minTime.compareTo( minTimeCandidate ) > 0 )
                    minTime = minTimeCandidate;
            }
            if ( ci[0].compareTo( X ) < 0 && ci[1].compareTo( X ) > 0 || ci[0].compareTo( X ) > 0 && ci[1].compareTo( X ) < 0 )
            {
                
                BigDecimal minTimeY = ( ( V.multiply( X ) ).subtract( ci[0].multiply( V ) ).divide( ri[1].multiply( ci[1].subtract( ci[0] ) ), 20, RoundingMode.HALF_UP ) );
                BigDecimal minTimeX =  ( V.subtract( ri[1].multiply( minTimeY ) ) ).divide( ri[0], 20, RoundingMode.HALF_UP );
                
                BigDecimal minTimeCandidate = minTimeX.max( minTimeY );
                if ( minTime == null || minTime.compareTo( minTimeCandidate ) > 0 )
                    minTime = minTimeCandidate;
            }
            
            if ( minTime == null )
                solution = "IMPOSSIBLE";
            else
                solution = minTime.toPlainString();
        }
        
        String s = "Case #" + n + ": " + solution + "" + "\n";
        bw.append( s );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/round2/P2/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/round2/P2/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] nvx = br.readLine().split( " " );
            int N = Integer.parseInt( nvx[0] );
            BigDecimal V = new BigDecimal( nvx[1] );
            BigDecimal X = new BigDecimal( nvx[2] );
            
            BigDecimal[] ri = new BigDecimal[N];
            BigDecimal[] ci = new BigDecimal[N];
            
            for ( int j = 0; j < N; j++ )
            {
                String[] rc = br.readLine().split( " " );
                ri[j] = new BigDecimal( rc[0] );
                ci[j] = new BigDecimal( rc[1] );
            }
            
            calculate( N, V, X, ri, ci, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
