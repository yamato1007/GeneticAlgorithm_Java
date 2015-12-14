package geneticalgorithm;

@FunctionalInterface
public interface GAContinueCondition {
	boolean isContinue(int generation, int score);
}
