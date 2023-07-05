package round1a.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P3
 {
    private static int maxSize = 0;
    
    private static void calculate( int N, int[] bffs, BufferedWriter bw, int n ) throws Exception
    {
        maxSize = 0;
        
        for ( int i = 0; i < N; i++ )
        {
            boolean[] in = new boolean[N];
            in[i] = true;
            tryAndBuild( i, -1, i, in, 1, bffs );
        }
        
        bw.append( "Case #" + n + ": " + maxSize + "\n" );
        bw.flush();
    }
    
    private static void tryAndBuild( int actual, int previous, int first, boolean[] in, int size, int[] bffs )
    {
        if ( actual < first )
            return;
        int next = bffs[actual];
        if ( in[next] )
        {
            if ( next == first )
                maxSize = Math.max( maxSize, size );
            if ( next == previous )
            {
                maxSize = Math.max( maxSize, size );
                for ( int i = 0; i < in.length; i++ )
                {
                    if ( in[i] )
                        continue;
                    if ( bffs[i] == actual )
                    {
                        boolean newIn[] = new boolean[in.length];
                        System.arraycopy( in, 0, newIn, 0, in.length );
                        newIn[ i ] = true;
                        
                        tryAndBuild( i, actual, first, in, size + 1, bffs );
                    }
                }
            }
            return;
        }
        
        boolean newIn[] = new boolean[in.length];
        System.arraycopy( in, 0, newIn, 0, in.length );
        newIn[ next ] = true;
        
        tryAndBuild( next, actual, first, newIn, size + 1, bffs );
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round1a/p3/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round1a/p3/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            int N = Integer.parseInt( br.readLine() );
            
            String[] data = br.readLine().split( " " );
            int[] bffs = new int[N];
            for ( int j = 0; j < N; j++ )
                bffs[j] = Integer.parseInt( data[j] ) - 1;
            
            calculate( N, bffs, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
