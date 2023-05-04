package qualification.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P2
 {
    private static void calculate( boolean[] pencakes, BufferedWriter bw, int n ) throws Exception
    {
        int rounds = 0;
        int bottom = pencakes.length - 1;
        
        while ( true )
        {
            for ( ; bottom >= 0 && pencakes[bottom]; bottom-- );
            if ( bottom < 0 )
                break;
            
            if ( pencakes[0] )
            {
                rounds++;
                for ( int i = 0; pencakes[i]; i++ )
                    pencakes[i] = false;
            }
            
            rounds++;
            for ( int i = 0; i <= bottom / 2; i++ )
            {
                boolean b = pencakes[i];
                pencakes[i] = !pencakes[bottom - i];
                pencakes[bottom-i] = !b;
            }
        }
        
        bw.append( "Case #"+n+": " + rounds + "\n" );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/qualification/p2/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/qualification/p2/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String data = br.readLine();
            
            boolean[] pencakes = new boolean[data.length()];
            for ( int j = 0; j < data.length(); j++ )
                pencakes[j] = data.charAt( j ) == '+';
            
            calculate( pencakes, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
