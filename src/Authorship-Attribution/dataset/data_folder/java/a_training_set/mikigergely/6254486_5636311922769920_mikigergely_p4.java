package qualification.p4;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P4
 {
    private static void calculate( int K, int C, int S, BufferedWriter bw, int n ) throws Exception
    {
        bw.append( "Case #"+n+":" );
        if ( S == K )
        {
            for ( int i = 1; i <= K; i++ )
                bw.append( " " + i );
        }
        else
            bw.append( " IMPOSSIBLE" );
        bw.append( "\n" );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/qualification/p4/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/qualification/p4/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int K = Integer.parseInt( data[0] );
            int C = Integer.parseInt( data[1] );
            int S = Integer.parseInt( data[2] );
            
            calculate( K, C, S, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
