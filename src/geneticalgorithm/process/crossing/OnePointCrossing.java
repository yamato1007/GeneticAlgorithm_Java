package geneticalgorithm.process.crossing;

import java.util.Random;

import geneticalgorithm.data.Gene;

public class OnePointCrossing extends AbstractCrossing {

	public OnePointCrossing(Gene gene1, Gene gene2) {
		super(gene1, gene2);
	}

	@Override
	public AbstractCrossing cross() {
		//遺伝子のサイズを取得
		int size = this.gene1.size();
		//一転交差の場所をランダム生成
		Random random = new Random();
		Integer crossingPoint = random.nextInt(size-2)+1;
		//子のインスタンスを生成
		Gene childGene1 = new Gene(size);
		Gene childGene2 = new Gene(size);
		//交叉
		for(int i = 0;i<size;i++){
			if(i < crossingPoint){
				childGene1.setGeneIndex(i,this.gene1.getGeneIndex(i));
				childGene2.setGeneIndex(i,this.gene2.getGeneIndex(i));
			}else{
				childGene1.setGeneIndex(i,this.gene2.getGeneIndex(i));
				childGene2.setGeneIndex(i,this.gene1.getGeneIndex(i));
			}
		}
		//値をセット
		this.setChildGene(childGene1, childGene2);
		//レシーバを返す
		return this;
	}

}
