package geneticalgorithm.process.calceval;

import java.util.List;

import geneticalgorithm.data.Gene;
import geneticalgorithm.userimplements.GeneIndividualFactory;

public abstract class AbstractCalcEvalFactory {
	public abstract AbstractCalcEval newInstance(GeneIndividualFactory factory,List<Gene> genes);
}
