package chris.atkins.mushroommonster;
 
 public class OutputTranslator {
 
    public static String translateToLine(final Output output) {
        return String.format("Case #%d: %d %d", output.caseNumber, output.numberEatenForAnyAtOnce, output.numberEatenForConstantPace);
    }
 }
