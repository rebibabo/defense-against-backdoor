package qualification.p4;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P4
 {
    private static void calculate( int X, int R, int C, BufferedWriter bw, int n ) throws Exception
    {
        String winner = null;
        
        if ( R*C % X != 0 )
            winner = "RICHARD";
        else
        {
            if ( X == 1 )
                winner = "GABRIEL";
            else if ( X == 2 )
                winner = "GABRIEL";
            else if ( X == 3 )
                winner = ( R == 1 || C == 1 ) ? "RICHARD" : "GABRIEL";
            else if ( X == 4 )
                winner = ( R <= 2 || C <= 2 ) ? "RICHARD" : "GABRIEL";
        }
        
        bw.append( "Case #"+n+": " + winner + "\n" );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/qualification/P4/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/qualification/P4/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int X = Integer.parseInt( data[0] );
            int R = Integer.parseInt( data[1] );
            int C = Integer.parseInt( data[2] );
            
            calculate( X, R, C, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
