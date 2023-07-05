package chris.atkins.dijkstra;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Before;
 import org.junit.Test;
 
 
 public class DijkstraTest {
 
    private List<String> inputLines;
    private List<String> expectedOutputLines;
    private final Dijkstra dijkstra = new Dijkstra();
 
 
    @Before
    public void setup() {
        setupInput();
        setupExpectedOutput();
    }
 
 
    @Test
    public void sampleCase() throws Exception {
        final List<String> result = this.dijkstra.solveAllProblems(this.inputLines);
        assertThat(result, equalTo(this.expectedOutputLines));
    }
 
 
    private void setupInput() {
        this.inputLines = new LinkedList<>();
        this.inputLines.add("5");
        this.inputLines.add("2 1");
        this.inputLines.add("ik");
        this.inputLines.add("3 1");
        this.inputLines.add("ijk");
        this.inputLines.add("3 1");
        this.inputLines.add("kji");
        this.inputLines.add("2 6");
        this.inputLines.add("ji");
        this.inputLines.add("1 10000");
        this.inputLines.add("i");
    }
 
 
    private void setupExpectedOutput() {
        this.expectedOutputLines = new LinkedList<>();
        this.expectedOutputLines.add("Case #1: NO");
        this.expectedOutputLines.add("Case #2: YES");
        this.expectedOutputLines.add("Case #3: NO");
        this.expectedOutputLines.add("Case #4: YES");
        this.expectedOutputLines.add("Case #5: NO");
    }
 }
