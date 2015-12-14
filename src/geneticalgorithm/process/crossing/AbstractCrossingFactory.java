package geneticalgorithm.process.crossing;

import geneticalgorithm.data.Gene;

public abstract class AbstractCrossingFactory {
	public abstract AbstractCrossing newInstance(Gene gene1,Gene  gene2);
}
