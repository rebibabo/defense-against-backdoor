package chris.atkins.infinitehouseofpancakes;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Solver {
 
 	private int[] numberOfPlatesWithPancakesCountAsIndex = new int[1000];
 	private int maxCount = 0;
 	private int minutesElapsed = 0;
 	private int bestTiming;
 
 
 	public List<Output> solve(final List<Input> inputs) {
 		final List<Output> outputList = new ArrayList<>(inputs.size());
 		for (final Input input : inputs) {
 			outputList.add(solveSingleProblem(input));
 		}
 		return outputList;
 	}
 
 
 	Output solveSingleProblem(final Input input) {
 		initialize(input);
 		while ((shouldKeepProcessing())) {
 			movePancakesAroundOnPlates();
 		}
 
 		return new Output(input.caseNumber, (this.bestTiming));
 	}
 
 
 	private boolean shouldKeepProcessing() {
 		if (this.maxCount < 4) {
 			return false;
 		}
 		return (this.numberOfPlatesWithPancakesCountAsIndex[this.maxCount] < this.maxCount);
 	}
 
 
 	private void movePancakesAroundOnPlates() {
 		this.numberOfPlatesWithPancakesCountAsIndex[this.maxCount]--;
 		final int countOne = this.maxCount / 2;
 		final int countTwo = this.maxCount - countOne;
 		this.numberOfPlatesWithPancakesCountAsIndex[countOne]++;
 		this.numberOfPlatesWithPancakesCountAsIndex[countTwo]++;
 		this.minutesElapsed++;
 		this.maxCount = findNewMaxCount();
 		if ((this.minutesElapsed + this.maxCount) < this.bestTiming) {
 			this.bestTiming = this.minutesElapsed + this.maxCount;
 		}
 	}
 
 
 	private int findNewMaxCount() {
 		for (int i = this.maxCount; i >= 0; i--) {
 			if (this.numberOfPlatesWithPancakesCountAsIndex[i] != 0) {
 				return i;
 			}
 		}
 		return 0;
 	}
 
 
 	private void initialize(final Input input) {
 		this.numberOfPlatesWithPancakesCountAsIndex = new int[1000];
 		this.maxCount = 0;
 		this.minutesElapsed = 0;
 
 		for (int i = 0; i < input.plates.length; i++) {
 			final int plateCount = input.plates[i];
 			if (plateCount > this.maxCount) {
 				this.maxCount = plateCount;
 			}
 			this.numberOfPlatesWithPancakesCountAsIndex[plateCount]++;
 		}
 		this.bestTiming = this.maxCount;
 	}
 }
