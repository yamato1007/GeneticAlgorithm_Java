package geneticalgorithm.process.mutation;

import geneticalgorithm.data.Gene;

public abstract class AbstractMutation {
	protected final Gene gene;
	private Gene mutantGene;
	
	public AbstractMutation(Gene gene){
		this.gene = gene;
	}
	
	public abstract AbstractMutation mutate(float pm);
	
	public Gene getMutant(){
		return mutantGene;
	}
	
	protected void setMutant(Gene gene){
		this.mutantGene = gene; 
	}
}
