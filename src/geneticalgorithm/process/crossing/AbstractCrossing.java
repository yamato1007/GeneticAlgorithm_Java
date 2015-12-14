package geneticalgorithm.process.crossing;

import geneticalgorithm.data.Gene;

public abstract class AbstractCrossing {
	protected final Gene gene1;
	protected final Gene gene2;
	private Gene childGene1;
	private Gene childGene2;

	public AbstractCrossing(Gene gene1,Gene  gene2){
		this.gene1 = gene1;
		this.gene2 = gene2;
	}

	public abstract AbstractCrossing cross();

	public final Gene getChild1(){
		return this.childGene1;
	}
	public final Gene getChild2(){
		return this.childGene2;
	}

	public final Gene[] getChilds(){
		Gene[] childGenes = new Gene[2];
		childGenes[0] = childGene1;
		childGenes[1] = childGene2;
		return childGenes;
	}

	protected final void setChildGene(Gene gene1,Gene gene2) {
		this.childGene1 = gene1;
		this.childGene2 = gene2;
	}
}
