package qualification.p1;
 
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P1
 {
    private static void calculate( boolean[] pencakes, int K, BufferedWriter bw, int n ) throws Exception
    {
        int flipNum = getFlipNum( pencakes, 0, K );
        bw.append( "Case #"+n+": " + ( flipNum == -1 ? "IMPOSSIBLE" : flipNum ) + "\n" );
        bw.flush();
    }
    
    private static int getFlipNum( boolean[] pencakes, int start, int K ) {
        if ( start > pencakes.length - K ) {
            for ( int i = start; i < pencakes.length; i++ )
                if ( !pencakes[i] )
                    return -1;
            
            return 0;
        }
        
        if ( pencakes[start] )
            return getFlipNum( pencakes, start + 1, K );
        else {
            for ( int i = 0; i < K; i++ )
                pencakes[ start + i ] = !pencakes[ start + i ];
            int flipNum = getFlipNum( pencakes, start+1, K );
            return flipNum == -1 ? -1 : flipNum + 1;
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
            String[] data = br.readLine().split(" ");
            
            String sPencakes = data[0];
            boolean[] pencakes = new boolean[sPencakes.length()];
            for ( int j = 0; j < sPencakes.length(); j++ )
                pencakes[j] = '+' == sPencakes.charAt( j );
            int K = Integer.parseInt( data[1] );
            
            calculate( pencakes, K, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
