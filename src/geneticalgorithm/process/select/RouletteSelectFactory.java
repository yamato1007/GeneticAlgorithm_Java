package geneticalgorithm.process.select;

import java.util.List;

import geneticalgorithm.data.Gene;
import geneticalgorithm.process.calceval.AbstractCalcEvalFactory;
import geneticalgorithm.userimplements.GeneIndividualFactory;

public class RouletteSelectFactory extends AbstractSelectFactory {

	@Override
	public AbstractSelect newInstance(AbstractCalcEvalFactory calcEvalFactory,GeneIndividualFactory geneIndivFactory, List<Gene> genes) {
		return new RouletteSelect(calcEvalFactory, geneIndivFactory, genes);
	}

}
