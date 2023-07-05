package chris.atkins.standingovation;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 
 @RunWith(JUnit4.class)
 public class StandingOvationTest {
 
    private List<String> inputLines;
    private List<String> expectedOutputLines;
    private final StandingOvation standingOvation = new StandingOvation();
 
 
    @Before
    public void setup() {
        setupInput();
        setupExpectedOutput();
    }
 
 
    @Test
    public void sampleCase() throws Exception {
        final List<String> result = this.standingOvation.solveAllOvationProblems(this.inputLines);
        assertThat(result, equalTo(this.expectedOutputLines));
    }
 
 
    private void setupInput() {
        this.inputLines = new LinkedList<>();
        this.inputLines.add("4");
        this.inputLines.add("4 11111");
        this.inputLines.add("1 09");
        this.inputLines.add("5 110011");
        this.inputLines.add("0 1");
    }
 
 
    private void setupExpectedOutput() {
        this.expectedOutputLines = new LinkedList<>();
        this.expectedOutputLines.add("Case #1: 0");
        this.expectedOutputLines.add("Case #2: 1");
        this.expectedOutputLines.add("Case #3: 2");
        this.expectedOutputLines.add("Case #4: 0");
    }
 }
