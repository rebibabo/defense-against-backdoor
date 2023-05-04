package chris.atkins.mushroommonster;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.apache.commons.lang.math.RandomUtils;
 import org.junit.Before;
 import org.junit.Test;
 
 
 public class SolverTest {
 
    private Solver solver;
    private int caseNumber;
 
 
    @Before
    public void setup() {
        this.caseNumber = RandomUtils.nextInt(100);
        this.solver = new Solver();
    }
 
 
    @Test
    public void firstSampleCase() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 10, 5, 15, 5 }, 4));
        assertThat(result, equalTo(new Output(this.caseNumber, 15, 25)));
    }
 
 
    @Test
    public void secondSampleCase() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 100, 100 }, 2));
        assertThat(result, equalTo(new Output(this.caseNumber, 0, 0)));
    }
 
 
    @Test
    public void thirdSampleCase() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 81, 81, 81, 81, 81, 81, 81, 0 }, 8));
        assertThat(result, equalTo(new Output(this.caseNumber, 81, 567)));
    }
 
 
    @Test
    public void fourthSampleCase() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 23, 90, 40, 0, 100, 9 }, 6));
        assertThat(result, equalTo(new Output(this.caseNumber, 181, 244)));
    }
 
 }
