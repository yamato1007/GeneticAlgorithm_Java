package geneticalgorithm.process.crossing;

import geneticalgorithm.data.Gene;

public class UniformCrossingFactory extends AbstractCrossingFactory {

	@Override
	public AbstractCrossing newInstance(Gene gene1,Gene  gene2) {
		return new UniformCrossing(gene1, gene2);
	}

}
