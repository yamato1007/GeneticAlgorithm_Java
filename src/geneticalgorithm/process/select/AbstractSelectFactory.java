package geneticalgorithm.process.select;

import java.util.List;

import geneticalgorithm.data.Gene;
import geneticalgorithm.process.calceval.AbstractCalcEvalFactory;
import geneticalgorithm.userimplements.GeneIndividualFactory;

public abstract class AbstractSelectFactory {
	public abstract AbstractSelect newInstance(AbstractCalcEvalFactory calcEvalFactory,GeneIndividualFactory geneIndivFactory, List<Gene> genes);
}
