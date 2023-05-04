package qualification.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P3
 {
    private static void calculate( int N, int J, BufferedWriter bw, int n ) throws Exception
    {
        bw.append( "Case #1:\n" );
        
        int count = 0;
        int inner = 0;
        while ( count < J )
        {
            long num = (1 << N-1) + 1 + (2*inner);
            String binaryNum = Long.toString( num, 2 );
            
            long[] numbers = new long[11];
            for ( int radix = 2; radix <= 10; radix++ )
                numbers[radix] = Long.parseLong( binaryNum, radix );
            
            int divisors[] = new int[11];
            for ( int d = 3; d <= 1000; d++ )
            {
                for ( int radix = 2; radix <= 10; radix++ )
                {
                    if ( divisors[radix] != 0 )
                        continue;
                    if ( numbers[radix] % d == 0 )
                        divisors[radix] = d;
                }
                
                boolean stillMore = false;
                for ( int radix = 2; radix <= 10; radix++ )
                    if ( divisors[radix] == 0 )
                        stillMore = true;
                
                if ( !stillMore )
                {
                    bw.append( binaryNum );
                    for ( int radix = 2; radix <= 10; radix++ )
                        bw.append( " " + divisors[radix] );
                    bw.append( "\n" );
                    count++;
                    break;
                }
            }
            
            inner++;
        }
        
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/qualification/p3/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/qualification/p3/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int N = Integer.parseInt( data[0] );
            int J = Integer.parseInt( data[1] );
            
            calculate( N, J, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
