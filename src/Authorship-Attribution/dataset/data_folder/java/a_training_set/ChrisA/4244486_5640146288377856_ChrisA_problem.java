package chris.atkins.firstproblem;
 
 import java.io.File;
 import java.util.LinkedList;
 import java.util.List;
 
 import org.apache.commons.io.FileUtils;
 
 
 public class Problem {
 
    private static final String outputPath = "C:/Users/Chris/Documents/GoogleCode/battleshipOutput.txt";
    private final InputCreator inputCreator = new InputCreator();
    private final Solver solver = new Solver();
 
 
    public static void main(final String[] args) {
        final String filepath = args[0];
        final Problem problem = new Problem();
 
        try {
            problem.solve(filepath);
            System.out.println("Done.");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
 
 
    private void solve(final String filepath) throws Exception {
        final List<String> inputLines = FileUtils.readLines(new File(filepath));
        final List<String> outputLines = solveAllProblems(inputLines);
        FileUtils.writeLines(new File(outputPath), outputLines, "\n");
    }
 
 
    List<String> solveAllProblems(final List<String> inputLines) {
        final List<Input> inputs = this.inputCreator.create(inputLines);
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
