package qualification.p2;
 
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P2
 {
    private static void calculate( Long T, BufferedWriter bw, int n ) throws Exception
    {
        String s = Long.toString( T );
        char[] lastTidyDigits = new char[s.length()];
        
        char last = '0';
        boolean nines = false;
        for ( int i = 0; i < s.length(); i++ )
        {
            char c = s.charAt( i );
            
            if ( nines )
                lastTidyDigits[i] = '9';
            else
            {
                if ( c >= last )
                {
                    lastTidyDigits[i] = c;
                    last = c;
                }
                else
                {
                    lastTidyDigits[i] = '9';
                    lastTidyDigits[i-1]--;
                    
                    for ( int j = i - 1; j > 0; j-- )
                    {
                        if ( lastTidyDigits[j] >= lastTidyDigits[j - 1] )
                            break;
                        else
                        {
                            lastTidyDigits[j] = '9';
                            lastTidyDigits[j-1]--;
                        }
                    }
                    nines = true;
                }
            }
        }
        
        Long lastTidy = Long.parseLong( new String( lastTidyDigits ) );
        
        bw.append( "Case #"+n+": " + lastTidy + "\n" );
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
            Long T = Long.parseLong( br.readLine() );
            
            calculate( T, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
