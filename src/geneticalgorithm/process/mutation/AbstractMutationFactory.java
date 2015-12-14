package geneticalgorithm.process.mutation;

import geneticalgorithm.data.Gene;

public abstract class AbstractMutationFactory {
	public abstract AbstractMutation newInstance(Gene gene);
}
