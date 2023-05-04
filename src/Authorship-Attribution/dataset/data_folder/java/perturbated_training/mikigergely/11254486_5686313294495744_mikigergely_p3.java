package round1b.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 
 public class P3
 {
    private static int maxFake;
    
    private static void calculate( List<String> topics, BufferedWriter bw, int n ) throws Exception
    {
        maxFake = 0;
        check( topics, new boolean[topics.size()], 0 );
        
        bw.append( "Case #" + n + ": " + maxFake + "\n" );
        bw.flush();
    }
    
    private static void check(List<String> topics, boolean[] real, int pos )
    {
        if ( pos == topics.size() )
            checkIfPossible( topics, real );
        else
        {
            boolean[] realCopy1 = new boolean[ topics.size() ];
            System.arraycopy( real, 0, realCopy1, 0, pos );
            check( topics, realCopy1, pos + 1 );
            
            boolean[] realCopy2 = new boolean[ topics.size() ];
            System.arraycopy( real, 0, realCopy2, 0, pos );
            realCopy2[pos] = true;
            check( topics, realCopy2, pos + 1 );
        }
    }
 
    private static void checkIfPossible( List<String> topics, boolean[] real )
    {
        Set<String> first = new HashSet<>();
        Set<String> second = new HashSet<>();
        for ( int i = 0; i < topics.size(); i++ )
            if ( real[i] )
            {
                String topic = topics.get( i );
                String[] topicWords = topic.split( " " );
                first.add( topicWords[0] );
                second.add( topicWords[1] );
            }
        
        boolean possible = true;
        int fakes = 0;
        for ( int i = 0; i < topics.size(); i++ )
            if ( !real[i] )
            {
                fakes++;
                String topic = topics.get( i );
                String[] topicWords = topic.split( " " );
                possible &= first.contains( topicWords[0] );
                possible &= second.contains( topicWords[1] );
            }
        
        if ( possible )
            maxFake = Math.max( maxFake, fakes );
    }
 
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round1b/p3/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round1b/p3/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            int numTopics = Integer.parseInt( br.readLine() );
            List<String> topics = new ArrayList<>();
            for ( int j = 0; j < numTopics; j++ )
                topics.add( br.readLine() );
            
            calculate( topics, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
