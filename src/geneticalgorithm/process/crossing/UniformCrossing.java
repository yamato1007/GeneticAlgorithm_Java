package geneticalgorithm.process.crossing;

import java.util.Random;

import geneticalgorithm.data.Gene;

public class UniformCrossing extends AbstractCrossing {

	public UniformCrossing(Gene gene1, Gene gene2) {
		super(gene1, gene2);
	}

	@Override
	public AbstractCrossing cross() {
		int size = this.gene1.size();
		Gene childGene1 = new Gene(size);
		Gene childGene2 = new Gene(size);
		
		Random rand = new Random();
		for(int i = 0;i<size;i++){
			if(rand.nextBoolean()){
				childGene1.setGeneIndex(i,this.gene1.getGeneIndex(i));
				childGene2.setGeneIndex(i,this.gene2.getGeneIndex(i));
			}else{
				childGene1.setGeneIndex(i,this.gene2.getGeneIndex(i));
				childGene2.setGeneIndex(i,this.gene1.getGeneIndex(i));
			}
		}
		setChildGene(childGene1, childGene2);
		return this;
	}

}
