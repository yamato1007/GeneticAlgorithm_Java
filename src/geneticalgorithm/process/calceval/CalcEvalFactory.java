package geneticalgorithm.process.calceval;

import java.util.List;

import geneticalgorithm.data.Gene;
import geneticalgorithm.userimplements.GeneIndividualFactory;

public class CalcEvalFactory extends AbstractCalcEvalFactory {

	@Override
	public AbstractCalcEval newInstance(GeneIndividualFactory factory,List<Gene> genes) {
		return new CalcEval(factory, genes);
	}

}
