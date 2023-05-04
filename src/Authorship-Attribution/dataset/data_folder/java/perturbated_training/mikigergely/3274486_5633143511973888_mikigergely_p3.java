package round1c.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.Arrays;
 
 public class P3
 {
    private static void calculate( int N, int K, double U, double[] P, BufferedWriter bw, int n ) throws Exception
    {
        Arrays.sort( P );
        while ( U > 0 )
        {
            int i = 1;
            while ( i < N )
                if ( Math.abs( P[i] - P[0] ) > 0.0000001 )
                    break;
                else
                    i++;
            
            if ( i == N )
            {
                for ( int j = 0; j < N; j++ )
                    P[j] = Math.min( P[j] + U / N, 1 );
                break;
            }
            else
            {
                double diff = P[i] - P[0];
                double toShare = Math.min( diff * i, U );
                for ( int j = 0; j < i; j++ )
                    P[j] = Math.min( P[j] + toShare / N, 1 );
            }
        }
        
        double chance = 1;
        for ( int i = 0; i < N; i++ )
            chance *= P[i];
        
        bw.append( String.format( "Case #"+n+": %.4f\n", chance ).replace( ',', '.' ) );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/" + P3.class.getPackage().getName().replace( '.', '/' ) + "/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        File outputFile = new File( "src/" + P3.class.getPackage().getName().replace( '.', '/' ) + "/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            int N = Integer.parseInt( data[0] );
            int K = Integer.parseInt( data[1] );
            
            double U = Double.parseDouble( br.readLine() );
            String[] sP = br.readLine().split( " " );
            double[] P = new double[N];
            for ( int j = 0; j < N; j++)
                P[j] = Double.parseDouble( sP[j] );
            
            calculate( N, K, U, P, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
