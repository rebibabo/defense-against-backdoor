package chris.atkins.standingovation;
 
 public class OutputTranslator {
 
    public static String translateToLine(final Output output) {
        return String.format("Case #%d: %s", output.caseNumber, output.numberToAdd);
    }
 }
