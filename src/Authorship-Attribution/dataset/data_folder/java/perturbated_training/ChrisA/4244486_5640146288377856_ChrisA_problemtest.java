package chris.atkins.firstproblem;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Before;
 import org.junit.Test;
 
 
 public class ProblemTest {
 
    private List<String> inputLines;
    private List<String> expectedOutputLines;
    private final Problem problem = new Problem();
 
 
    @Before
    public void setup() {
        setupInput();
        setupExpectedOutput();
    }
 
 
    @Test
    public void sampleCase() throws Exception {
        final List<String> result = this.problem.solveAllProblems(this.inputLines);
        assertThat(result, equalTo(this.expectedOutputLines));
    }
 
 
    private void setupInput() {
        this.inputLines = new LinkedList<>();
        this.inputLines.add("3");
        this.inputLines.add("1 4 2");
        this.inputLines.add("1 7 7");
        this.inputLines.add("2 5 1");
    }
 
 
    private void setupExpectedOutput() {
        this.expectedOutputLines = new LinkedList<>();
        this.expectedOutputLines.add("Case #1: 3");
        this.expectedOutputLines.add("Case #2: 7");
        this.expectedOutputLines.add("Case #3: 10");
    }
 }
