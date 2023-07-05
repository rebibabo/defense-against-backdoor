package round2.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Set;
 
 public class P3Small
 {
    private static int minCommon;
    
    private static void calculate( int N, String[] sentences, BufferedWriter bw, int n ) throws IOException
    {
        minCommon = Integer.MAX_VALUE;
        
        Set<String> englishWords = new HashSet<String>();
        Set<String> frenchWords = new HashSet<String>();
        
        String englishSentence = sentences[0];
        englishWords.addAll( Arrays.asList( englishSentence.split( " " ) ) );
        
        String frenchSentence = sentences[1];
        frenchWords.addAll( Arrays.asList( frenchSentence.split( " " ) ) );
        
        solve( englishWords, frenchWords, sentences, 2 );
        
        String s = "Case #" + n + ": " + minCommon + "" + "\n";
        bw.append( s );
        bw.flush();
    }
    
    private static void solve( Set<String> englishWords, Set<String> frenchWords, String[] sentences, int i )
    {
        Set<String> common = new HashSet<String>( englishWords );
        common.retainAll( frenchWords );
        int commonNum = common.size();
        if ( commonNum > minCommon )
            return;
        
        if ( i == sentences.length )
        {
            if ( commonNum < minCommon )
                minCommon = common.size();
            return;
        }
        
        {
            Set<String> newEnglishWords = new HashSet<String>( englishWords );
            newEnglishWords.addAll( Arrays.asList( sentences[i].split( " " ) ) );
            
            solve( newEnglishWords, frenchWords, sentences, i+1 );
        }
        {
            Set<String> newFrenchWords  = new HashSet<String>( frenchWords );
            newFrenchWords.addAll( Arrays.asList( sentences[i].split( " " ) ) );
            
            solve( englishWords, newFrenchWords, sentences, i+1 );
        }
    }
 
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/round2/P3/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/round2/P3/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            int N = Integer.parseInt( br.readLine() );
            
            String[] sentences = new String[N];
            for ( int j = 0; j < N; j++ )
                sentences[j] = br.readLine();
            
            calculate( N, sentences, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
