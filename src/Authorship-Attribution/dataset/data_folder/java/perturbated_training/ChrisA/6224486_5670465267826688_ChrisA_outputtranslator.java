package chris.atkins.dijkstra;
 
 public class OutputTranslator {
 
    public static String translateToLine(final Output output) {
        final String answer = output.correctish ? "YES" : "NO";
        return String.format("Case #%d: %s", output.caseNumber, answer);
    }
 }
