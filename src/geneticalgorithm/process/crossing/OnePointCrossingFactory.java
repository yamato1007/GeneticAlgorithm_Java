package geneticalgorithm.process.crossing;

import geneticalgorithm.data.Gene;

public class OnePointCrossingFactory extends AbstractCrossingFactory {

	@Override
	public AbstractCrossing newInstance(Gene gene1, Gene gene2) {
		return new OnePointCrossing(gene1, gene2);
	}

}
