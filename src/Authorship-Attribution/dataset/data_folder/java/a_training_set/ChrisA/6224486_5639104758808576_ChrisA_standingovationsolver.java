package chris.atkins.standingovation;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class StandingOvationSolver {
 
    public List<Output> solve(final List<Input> inputs) {
        final List<Output> outputList = new ArrayList<>(inputs.size());
        for (final Input input : inputs) {
            outputList.add(solveSingleProblem(input));
        }
        return outputList;
    }
 
 
    Output solveSingleProblem(final Input input) {
 
        int additions = 0;
        int totalStanding = input.audience[0];
 
        for (int needsToStand = 1; needsToStand < input.audience.length; needsToStand++) {
            if (notEnoughPeopleAreStanding(totalStanding, needsToStand)) {
                final int peopleToAdd = needsToStand - totalStanding;
                additions += peopleToAdd;
                totalStanding += peopleToAdd;
            }
            totalStanding += input.audience[needsToStand];
        }
 
        return new Output(input.caseNumber, additions);
    }
 
 
    private boolean notEnoughPeopleAreStanding(final int sumOfStanding, final int needsToStand) {
        return sumOfStanding < needsToStand;
    }
 }
