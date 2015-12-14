package geneticalgorithm.process.select;

import java.util.List;

import geneticalgorithm.data.Gene;
import geneticalgorithm.process.calceval.AbstractCalcEvalFactory;
import geneticalgorithm.userimplements.GeneIndividualFactory;

public abstract class AbstractSelect {
	//protected final Class<AbstractCalcEval> calcEvalClass;
	protected final AbstractCalcEvalFactory calcEvalFactory;
	protected final GeneIndividualFactory geneIndivFactory;
	protected final List<Gene> genes;
	private Gene selectedGene;
	
	public AbstractSelect(AbstractCalcEvalFactory calcEvalFactory,GeneIndividualFactory geneIndivFactory, List<Gene> genes) {
		this.calcEvalFactory = calcEvalFactory;
		this.geneIndivFactory = geneIndivFactory;
		this.genes = genes;
	}
	
	public abstract AbstractSelect select();
	
	public Gene getSelectedGene() {
		return this.selectedGene;
	}
	
	protected void setSelectedGene(Gene gene) {
		this.selectedGene = gene;
	}
}
