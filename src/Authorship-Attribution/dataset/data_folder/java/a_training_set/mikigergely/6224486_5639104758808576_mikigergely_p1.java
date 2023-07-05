package qualification.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P1
 {
    private static void calculate( int Smax, int[] shynesses, BufferedWriter bw, int n ) throws Exception
    {
        int additional = 0;
        int standing = 0;
        
        for ( int i = 0; i <= Smax; i++ )
        {
            if ( standing < i )
            {
                int newAdditional = ( i - standing );
                additional += newAdditional;
                standing += newAdditional;
            }
            
            
            standing += shynesses[i];
        }
        
        bw.append( "Case #"+n+": " + additional + "\n" );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/qualification/P1/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/qualification/P1/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int Smax = Integer.parseInt( data[0] );
            String shynessesString = data[1];
            
            int[] shynesses = new int[shynessesString.length()];
            for ( int j = 0; j < shynessesString.length(); j++ )
                shynesses[j] = shynessesString.charAt( j ) - '0';
            
            calculate( Smax, shynesses, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
