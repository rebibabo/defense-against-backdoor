package chris.atkins.standingovation;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.apache.commons.lang.math.RandomUtils;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 
 @RunWith(JUnit4.class)
 public class StandingOvationSolverTest {
 
    private final StandingOvationSolver solver = new StandingOvationSolver();
    private int caseNumber;
 
 
    @Before
    public void setup() {
        this.caseNumber = RandomUtils.nextInt(100);
    }
 
 
    @Test
    public void oneOne() throws Exception {
        final Input input = new Input(this.caseNumber, new int[] { 1 });
        final Output result = this.solver.solveSingleProblem(input);
        assertThat(result, equalTo(new Output(this.caseNumber, 0)));
    }
 
 
    @Test
    public void allOnes() throws Exception {
        final Input input = new Input(this.caseNumber, new int[] { 1, 1, 1, 1, 1, 1, 1, 1 });
        final Output result = this.solver.solveSingleProblem(input);
        assertThat(result, equalTo(new Output(this.caseNumber, 0)));
    }
 
 
    @Test
    public void firstZeroThenOnes() throws Exception {
        final Input input = new Input(this.caseNumber, new int[] { 0, 1, 1, 1 });
        final Output result = this.solver.solveSingleProblem(input);
        assertThat(result, equalTo(new Output(this.caseNumber, 1)));
    }
 
 
    @Test
    public void sampleLine3() throws Exception {
        final Input input = new Input(this.caseNumber, new int[] { 0, 9 });
        final Output result = this.solver.solveSingleProblem(input);
        assertThat(result, equalTo(new Output(this.caseNumber, 1)));
    }
 
 
    @Test
    public void sampleLine4() throws Exception {
        final Input input = new Input(this.caseNumber, new int[] { 1, 1, 0, 0, 1, 1 });
        final Output result = this.solver.solveSingleProblem(input);
        assertThat(result, equalTo(new Output(this.caseNumber, 2)));
    }
 
 
    @Test
    public void allZerosThenOne() throws Exception {
        final Input input = new Input(this.caseNumber, new int[] { 0, 0, 0, 0, 0, 1 });
        final Output result = this.solver.solveSingleProblem(input);
        assertThat(result, equalTo(new Output(this.caseNumber, 5)));
    }
 }
