package chris.atkins.mushroommonster;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Solver {
 
    public List<Output> solve(final List<Input> inputs) {
        final List<Output> outputList = new ArrayList<>(inputs.size());
        for (final Input input : inputs) {
            outputList.add(solveSingleProblem(input));
        }
        return outputList;
    }
 
 
    Output solveSingleProblem(final Input input) {
        int biggestDecrease = 0;
        int lastRound = input.roundValues[0];
        final int[] numberEatenByRound = new int[input.numberOfRounds];
 
        for (int i = 1; i < input.roundValues.length; i++) {
            final int decrease = lastRound - input.roundValues[i];
 
            biggestDecrease = max(decrease, biggestDecrease);
            numberEatenByRound[i] = max(0, decrease);
 
            lastRound = input.roundValues[i];
        }
 
        final int totalEatenByConstantValue = calculateByConstant(biggestDecrease, input.roundValues);
        final int totalEatenByAnyNumber = sum(numberEatenByRound);
        return new Output(input.caseNumber, totalEatenByAnyNumber, totalEatenByConstantValue);
    }
 
 
    private int calculateByConstant(final int biggestDecrease, final int[] roundValues) {
        int sum = 0;
        for (int i = 1; i < roundValues.length; i++) {
            sum += min(roundValues[i - 1], biggestDecrease);
        }
        return sum;
    }
 
 
    private int min(final int i, final int j) {
        return i < j ? i : j;
    }
 
 
    private int max(final int i, final int j) {
        return i > j ? i : j;
    }
 
 
    private int sum(final int[] numberEatenByRound) {
        int sum = 0;
        for (final int i : numberEatenByRound) {
            sum += i;
        }
        return sum;
    }
 }
