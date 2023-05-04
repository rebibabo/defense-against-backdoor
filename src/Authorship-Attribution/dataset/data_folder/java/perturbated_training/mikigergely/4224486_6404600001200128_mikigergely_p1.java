package round1a.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P1
 {
    private static void calculate( int N, int[] m, BufferedWriter bw, int n ) throws Exception
    {
        int minEat1 = 0;
        int minEat2 = 0;
        int maxEat = 0;
        for ( int i = 1; i < N; i++ )
            if ( m[i-1] > m[i] )
            {
                int eat = m[i-1] - m[i];
                minEat1 += eat;
                maxEat = Math.max( maxEat, eat );
            }
        
        for ( int i = 0; i < N-1; i++ )
            minEat2 += Math.min( maxEat, m[i] );
        
        bw.append( "Case #"+n+": " + minEat1 + " " + minEat2 + "\n" );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/round1a/P1/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/round1a/P1/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            int N = Integer.parseInt( br.readLine() );
            
            int[] m = new int[N];
            
            String[] data = br.readLine().split( " " );
            
            for ( int j = 0; j < N; j++ )
                m[j] = Integer.parseInt( data[j] );
            
            calculate( N, m, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
