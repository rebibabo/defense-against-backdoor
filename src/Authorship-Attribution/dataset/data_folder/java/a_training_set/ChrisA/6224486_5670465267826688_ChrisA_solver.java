package chris.atkins.dijkstra;
 
 import static chris.atkins.dijkstra.Var.K;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Solver {
 
    int totalLetters;
    Var firstLetter;
    Var secondLetter;
    Var thirdLetter;
    Var[] letters;
    Var[] multipliedStraight;
    Var[] dividedByI;
    Var[] dividedByJ;
 
    int repeatTimes;
    long loop;
 
 
    public List<Output> solve(final List<Input> inputs) {
        final List<Output> outputList = new ArrayList<>(inputs.size());
        for (final Input input : inputs) {
            outputList.add(solveSingleProblem(input));
            System.out.println("Problem: " + input.caseNumber);
        }
        return outputList;
    }
 
 
    Output solveSingleProblem(final Input input) {
        initialize(input);
        final int lastIndex = this.totalLetters - 1;
        if (!this.dividedByJ[lastIndex].equals(K)) {
            return new Output(input.caseNumber, false);
        }
 
        final int firstI = findFirstIIndex();
        if (firstI == -1) {
            return new Output(input.caseNumber, false);
        }
 
        final int firstJAfterI = findFirstJAfter(firstI);
        if (firstJAfterI == -1) {
            return new Output(input.caseNumber, false);
        }
        return new Output(input.caseNumber, true);
    }
 
 
    private int findFirstIIndex() {
        for (int i = 0; i < this.multipliedStraight.length - 2; i++) {
            if (this.multipliedStraight[i].equals(Var.I)) {
                return i;
            }
        }
        return -1;
    }
 
 
    private int findFirstJAfter(final int firstI) {
        for (int i = firstI + 1; i < this.dividedByI.length - 1; i++) {
            if (this.dividedByI[i].equals(Var.J)) {
                return i;
            }
        }
        return -1;
    }
 
 
    private int getIndex(final long index) {
        return (int) (index % this.letters.length);
    }
 
 
    private void initialize(final Input input) {
        this.totalLetters = input.letters.length * input.numberOfTimesToRepeat;
        this.letters = input.letters;
        this.repeatTimes = input.numberOfTimesToRepeat;
 
        this.multipliedStraight = new Var[this.totalLetters];
        this.dividedByI = new Var[this.totalLetters];
        this.dividedByJ = new Var[this.totalLetters];
        Var currentVar = Var.ONE;
        for (int i = 0; i < this.totalLetters; i++) {
            currentVar = currentVar.times(input.letters[getIndex(i)]);
            this.multipliedStraight[i] = currentVar;
            this.dividedByI[i] = divideByI(currentVar);
            this.dividedByJ[i] = divideByJ(this.dividedByI[i]);
        }
 
        
    }
 
 
    private Var divideByI(final Var var) {
        switch (var) {
        case ONE:
            return Var.NEGATIVE_I;
        case I:
            return Var.ONE;
        case J:
            return Var.NEGATIVE_K;
        case K:
            return Var.J;
        case NEGATIVE_ONE:
            return Var.I;
        case NEGATIVE_I:
            return Var.NEGATIVE_ONE;
        case NEGATIVE_J:
            return Var.K;
        case NEGATIVE_K:
            return Var.NEGATIVE_J;
        }
        throw new RuntimeException("??");
    }
 
 
    private Var divideByJ(final Var var) {
        switch (var) {
        case ONE:
            return Var.NEGATIVE_J;
        case I:
            return Var.K;
        case J:
            return Var.ONE;
        case K:
            return Var.NEGATIVE_I;
        case NEGATIVE_ONE:
            return Var.J;
        case NEGATIVE_I:
            return Var.NEGATIVE_K;
        case NEGATIVE_J:
            return Var.NEGATIVE_ONE;
        case NEGATIVE_K:
            return Var.I;
        }
        throw new RuntimeException("??");
    }
 }
