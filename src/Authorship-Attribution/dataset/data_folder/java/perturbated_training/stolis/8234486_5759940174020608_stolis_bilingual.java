package year2015.round2;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class Bilingual {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("C-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             System.err.println(t);
             int N = in.nextInt();
             in.nextLine();
             String engSentence = in.nextLine();
             String frSentence = in.nextLine();
             Map<String,Integer> baseMap = new HashMap<String,Integer>();
             StringTokenizer tokenizer = new StringTokenizer(engSentence);
             while (tokenizer.hasMoreTokens()) {
                 baseMap.put(tokenizer.nextToken(), 1);
             }
             tokenizer = new StringTokenizer(frSentence);
             while (tokenizer.hasMoreElements()) {
                 String word = tokenizer.nextToken();
                 Integer i = baseMap.get(word);
                 if (i == null) {
                     i = 0;
                 }
                 i |= 2;
                 baseMap.put(word, i);
             }
             String[] sentences = new String[N-2];
             for (int n=0; n<sentences.length; n++) {
                 sentences[n] = in.nextLine();
             }
             int limit = 1 << N-2;
             int min = Integer.MAX_VALUE;
             for (int mask=0; mask<limit; mask++) {
                 Map<String,Integer> map = new HashMap<String,Integer>(baseMap);
                 for (int bit=0; bit<sentences.length; bit++) {
                     boolean eng = ((mask&(1 << bit)) == 0);
                     tokenizer = new StringTokenizer(sentences[bit]);
                     while (tokenizer.hasMoreTokens()) {
                         String word = tokenizer.nextToken();
                         Integer i = map.get(word);
                         if (i == null) {
                             i = 0;
                         }
                         if (eng) {
                             i |= 1;
                         }  else {
                             i |= 2;
                         }
                         map.put(word, i);
                     }
                 }
                 int count = 0;
                 for (int value : map.values()) {
                     if (value == 3) {
                         count++;
                     }
                 }
                 min = Math.min(min, count);
             }
             
             out.println("Case #"+(t+1)+": "+min);
         }
 
         out.close();
     }
     
 }
