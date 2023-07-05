package chris.atkins.firstproblem;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Solver {
 
    private Input in;
 
 
    public List<Output> solve(final List<Input> inputs) {
        final List<Output> outputList = new ArrayList<>(inputs.size());
        for (final Input input : inputs) {
            outputList.add(solveSingleProblem(input));
        }
        return outputList;
    }
 
 
    Output solveSingleProblem(final Input input) {
        initialize(input);
        final int minimumGuaranteedScore = solve();
        return new Output(input.caseNumber, minimumGuaranteedScore);
    }
 
 
    private int solve() {
        final int missesDueToRows = (this.in.rows - 1) * findNumberToCoverRow();
        final int rowWithHit = solveHitCase();
        return rowWithHit + missesDueToRows;
    }
 
 
    private int solveHitCase() {
        final int numberUntilFirstHit = findNumberToCoverRow();
        final int numberAfterFirstHit = findNumberAfterFirstHitWithEmptySides(this.in.shipWidth - 1, calculateEmptyRight());
        return numberUntilFirstHit + numberAfterFirstHit;
    }
 
 
    private int calculateEmptyRight() {
        return this.in.columns % this.in.shipWidth;
    }
 
 
    private int findNumberToCoverRow() {
        return this.in.columns / this.in.shipWidth;
    }
 
 
    private int findNumberAfterFirstHitWithEmptySides(final int left, final int right) {
        final int emptyColumns = Math.min(left, right);
        return emptyColumns == 0 ? this.in.shipWidth - 1 : this.in.shipWidth;
    }
 
 
    private void initialize(final Input input) {
        this.in = input;
    }
 }
