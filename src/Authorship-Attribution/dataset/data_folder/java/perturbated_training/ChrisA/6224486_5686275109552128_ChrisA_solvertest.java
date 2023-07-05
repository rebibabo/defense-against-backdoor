package chris.atkins.infinitehouseofpancakes;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.apache.commons.lang.math.RandomUtils;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 
 @RunWith(JUnit4.class)
 public class SolverTest {
 
    private Solver solver;
    private int caseNumber;
 
 
    @Before
    public void setup() {
        this.caseNumber = RandomUtils.nextInt(100);
        this.solver = new Solver();
    }
 
 
    @Test
    public void onePlateOf1() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 1 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 1)));
    }
 
 
    @Test
    public void onePlateOf2() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 2 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 2)));
    }
 
 
    @Test
    public void onePlateOf3() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 3 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 3)));
    }
 
 
    @Test
    public void onePlateOf4() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 4 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 3)));
    }
 
 
    @Test
    public void onePlateOf5() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 5 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void onePlateOf6() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 6 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void onePlateOf7() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 7 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 5)));
    }
 
 
    @Test
    public void onePlateOf8() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 8 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 5)));
    }
 
 
    @Test
    public void onePlateOf9() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 9 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 6)));
    }
 
 
    @Test
    public void ten1s() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 1)));
    }
 
 
    @Test
    public void twoPlatesOf2() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 2, 2 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 2)));
    }
 
 
    @Test
    public void onePlatesOf2_OneOf3() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 2, 3 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 3)));
    }
 
 
    @Test
    public void onePlatesOf4() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 4 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 3)));
    }
 
 
    @Test
    public void onePlatesOf5() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 5 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void onePlatesOf6() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 6 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void onePlatesOf16() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 16 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 7)));
    }
 
 
    @Test
    public void onePlatesOf15_oneOf16() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 16, 15 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 10)));
    }
 
 
    @Test
    public void five8s() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 8, 8, 8, 8, 8 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 8)));
    }
 
 
    @Test
    public void one5_three8s() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 8, 8, 8, 5 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 8)));
    }
 
 
    @Test
    public void one4_three8s() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 8, 8, 8, 4 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 7)));
    }
 
 
    @Test
    public void three9s() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 9, 9, 9 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 8)));
    }
 
 
    @Test
    public void failingCase1() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 3, 6, 6, 6, 9, 9 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 8)));
 
    }
 
 
    @Test
    public void failingCase2() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new int[] { 5, 5, 5, 5, 9, 9 }));
        assertThat(result, equalTo(new Output(this.caseNumber, 7)));
    }
 }
