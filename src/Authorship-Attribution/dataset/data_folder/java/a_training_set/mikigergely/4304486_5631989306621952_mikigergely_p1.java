package round1a.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P1
 {
    private static void calculate( String data, BufferedWriter bw, int n ) throws Exception
    {
        String result = null;
        
        for ( char c : data.toCharArray() )
        {
            if ( result == null )
                result = "" + c;
            else
            {
                char first = result.charAt( 0 );
                if ( c >= first )
                    result = c + result;
                else
                    result = result + c;
            }
        }
        
        bw.append( "Case #" + n + ": " + result + "\n" );
        
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round1a/p1/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round1a/p1/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String data = br.readLine();
            
            calculate( data, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
