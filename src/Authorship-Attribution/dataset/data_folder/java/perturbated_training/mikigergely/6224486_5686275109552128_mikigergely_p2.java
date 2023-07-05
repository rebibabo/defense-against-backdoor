package qualification.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P2
 {
    private static void calculate( int D, int[] P, BufferedWriter bw, int n ) throws Exception
    {
        int initMax = 0;
        for ( int p : P )
            initMax = Math.max( p, initMax );
        
        int minMinutes = initMax;
        
        for ( int i = 1; i < initMax; i++ )
        {
            int iMax = i;
            for ( int j = 0; j < D; j++ )
                iMax += ( ( P[j] / i ) - ( P[j] % i == 0 ? 1 : 0 ) );
            
            minMinutes = Math.min( minMinutes, iMax );
        }
        
        bw.append( "Case #"+n+": " + minMinutes + "\n" );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/qualification/P2/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/qualification/P2/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            int D = Integer.parseInt( br.readLine() );
            int[] P = new int[D];
            
            String[] data = br.readLine().split( " " );
            for ( int j = 0; j < D; j++ )
                P[j] = Integer.parseInt( data[j] );
            
            calculate( D, P, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
