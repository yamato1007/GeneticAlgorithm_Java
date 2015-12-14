package geneticalgorithm.process.mutation;

import java.util.Random;

import geneticalgorithm.data.Gene;

public class Mutation extends AbstractMutation {

	public Mutation(Gene gene) {
		super(gene);
	}

	@Override
	public AbstractMutation mutate(float pm) {
		Gene mutant = new Gene(gene.getGene());
		
		Random rand = new Random();
		//pmの確率で遺伝子を反転
		for(int i=0;i<mutant.size();i++){
			//exclusive or　で反転している
			mutant.setGeneIndex(i,mutant.getGeneIndex(i) ^ (rand.nextFloat() < pm));
		}
		this.setMutant(mutant);
		return this;
	}

}
