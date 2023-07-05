package chris.atkins.standingovation;
 
 import java.io.File;
 import java.util.LinkedList;
 import java.util.List;
 
 import org.apache.commons.io.FileUtils;
 
 
 public class StandingOvation {
 
    private static final String outputPath = "C:/Users/Chris/Documents/standingOvationOutput.txt";
    private final InputCreator inputCreator = new InputCreator();
    private final StandingOvationSolver solver = new StandingOvationSolver();
 
 
    public static void main(final String[] args) {
        final String filepath = args[0];
        final StandingOvation standingOvation = new StandingOvation();
 
        try {
            standingOvation.solve(filepath);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
 
 
    private void solve(final String filepath) throws Exception {
        final List<String> inputLines = FileUtils.readLines(new File(filepath));
        final List<String> outputLines = solveAllOvationProblems(inputLines);
        FileUtils.writeLines(new File(outputPath), outputLines, "\n");
    }
 
 
    List<String> solveAllOvationProblems(final List<String> inputStrings) {
        final List<Input> inputs = this.inputCreator.create(inputStrings);
        final List<Output> outputs = this.solver.solve(inputs);
        final List<String> outputLines = buildOutputLines(outputs);
        return outputLines;
    }
 
 
    private List<String> buildOutputLines(final List<Output> outputs) {
        final List<String> outputLines = new LinkedList<>();
        for (final Output output : outputs) {
            outputLines.add(OutputTranslator.translateToLine(output));
        }
        return outputLines;
    }
 }
