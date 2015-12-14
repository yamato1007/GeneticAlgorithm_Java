package geneticalgorithm.process.mutation;

import geneticalgorithm.data.Gene;

public class MutationFactory extends AbstractMutationFactory {

	@Override
	public AbstractMutation newInstance(Gene gene) {
		return new Mutation(gene);
	}

}
